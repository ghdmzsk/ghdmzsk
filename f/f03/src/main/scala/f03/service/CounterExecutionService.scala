package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import zio.stream.ZStream

import scala.concurrent.ExecutionContext

trait CounterExecutionService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def executePlan(count: Int): CTask[Int]
}

class CounterExecutionServiceImpl(db: SlickDB, planExecute: PlanExecute) extends CounterExecutionService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  protected def firstCounterPlan(count: Int): CStream[CountPlanRow] = {
    val action = CountPlan.filter(_.counterResultId.isEmpty).take(count).to[List].result
    val collM  = db.run(action)
    ZStream.fromIterableM(collM)
  }

  protected def executeCountPlan(plan: CountPlanRow, countSetRow: CountSetRow): CTask[Int] = {
    val dbio =
      CountSet.filter(s => s.countSet === countSetRow.countSet && s.isLimited === countSetRow.isLimited).take(1).to[List].result.headOption
    val action = for (exists <- db.run(dbio)) yield exists match {
      case Some(s) => DBIO.successful(s)
      case _ =>
        CountSet returning CountSet.map(_.id) into ((model, id) => model.copy(id = id)) += countSetRow
    }
    def updatePlan(set: DBIO[CountSetRow])(implicit ec: ExecutionContext) = for {
      row   <- set
      count <- CountPlan.filter(_.id === plan.id).map(_.counterResultId).update(Option(row.id))
    } yield count

    for {
      a1 <- action
      a2 <- db.run(implicit ec => updatePlan(a1).transactionally)
    } yield a2
  }

  override def executePlan(count: Int): CTask[Int] = {
    val setNeedToInsert = firstCounterPlan(count).mapMPar(5)(row => for (set <- planExecute.countNumberToString(row)) yield (row, set))
    def filterInsert(list: List[(CountPlanRow, CountSetRow)]) =
      ZStream.fromIterable(list).mapM { case (plan, set) => executeCountPlan(plan, set) }
    val needCountDBIO = CountPlan.filter(_.counterResultId.isEmpty).map(_.id).size.result

    for {
      chunk     <- setNeedToInsert.runCollect
      _         <- filterInsert(chunk.to(List)).runCollect
      needCount <- db.run(needCountDBIO)
    } yield needCount
  }
}
