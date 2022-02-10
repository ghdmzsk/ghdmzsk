package f03.service

import f03.mainapp.{MainApp, SlickDB}
import zio._
import zio.logging._
import f03.slick.model.Tables._
import f03.slick.model.Tables.profile.api._
import zio.stream.ZStream

import java.io.PrintWriter
import java.nio.file.{Files, Paths}
import scala.concurrent.ExecutionContext

trait CodegenService {
  type CTask[T] = RIO[MainApp.AppEnv, T]

  def codegen(): CTask[Unit]
}

class CodegenServiceImpl(db: SlickDB, dataCollection: DataCollection) extends CodegenService {

  type CStream[T] = ZStream[MainApp.AppEnv, Throwable, T]

  override def codegen(): CTask[Unit] = {
    val getIdAction = for {
      plan <- CountPlan
      set  <- CountSet if plan.counterResultId === set.id
    } yield (plan, set)
    val planAndSet = db.run(getIdAction.result)

    def printlnSet(set: Seq[CountSetRow]): CTask[Unit] = {
      val str1 = set.map(s =>
        s"val countSet${s.id}: CountSet = CountSet(index = ${s.id}, firstStart = ${s.firstStart}, secondStart = ${s.secondStart}, set = \"${s.countSet}\")"
      )
      val sumStr  = set.grouped(20).map(t => s"List(${t.map(r => s"b.countSet${r.id}").mkString(",")})").mkString(" ::: ")
      val strPre1 = s"  val sum: List[CountSet] = $sumStr"
      val str2 = "package f07" :: "trait CountSets {" :: str1
        .to(List)
        .map(s => "  " + s)
        .appendedAll(List("}", "", "object CountSets {", "object b extends CountSets", strPre1, "}"))
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07")
      val path1 = path.resolve("CountSets.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str2.foreach(p.println)))
      } yield s
    }

    def printlnPlan(plans: Seq[CountPlanRow], index: Int): CTask[Unit] = {
      val str1 = plans.map(p =>
        s"val plan${p.id} = CountPlan(index = ${p.id}, firstOuterName = \"${p.firstOuterName}\", firstOuterType = \"${p.firstOuterType}\", firstInnerName = \"${p.firstInnerName}\", firstInnerType = \"${p.firstInnerType}\", firstStart = ${p.firstStart}, secondOuterName = \"${p.secondOuterName}\", secondOuterType = \"${p.secondOuterType}\", secondInnerName = \"${p.secondInnerName}\", secondInnerType = \"${p.secondInnerType}\", secondStart = ${p.secondStart}, set = CountSets.b.countSet${p.counterResultId
          .getOrElse("未有值")})"
      )
      val str2 = s"  val sum: List[CountPlan] = List(${plans.map(s => s"plan${s.id}").mkString(",")})"
      val str3 = "package f07.codegen.impl" :: "import f07._" :: s"object CountPlans$index {" :: str1
        .to(List)
        .map(s => "  " + s)
        .appendedAll(List(str2, "}"))
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07", "codegen", "impl")
      val path1 = path.resolve(s"CountPlans$index.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str3.foreach(p.println)))
      } yield s
    }

    def printlnPlan1(indexSeq: List[Int]): CTask[Unit] = {
      val str1   = "package f07"
      val str1_1 = "import f07.codegen.impl._"
      val str2   = s"object CountPlans {"
      val str3 =
        s"  val sum: List[CountPlan] = ${indexSeq.grouped(20).grouped(20).map(s => s.map(t => t.map(u => s"CountPlans$u.sum").mkString("(", ":::", ")")).mkString("(", " ::: ", ")")).mkString(" ::: ")}"
      val str4  = List(str1, str1_1, str2, str3, "}")
      val path  = Paths.get("..", "f07", "src", "main", "codegen", "f07")
      val path1 = path.resolve("CountPlans.scala")
      for {
        _         <- blocking.effectBlocking(Files.createDirectories(path))
        printlner <- ZIO.effect(ZManaged.fromAutoCloseable(ZIO.effect(new PrintWriter(path1.toFile))))
        s         <- printlner.use(p => blocking.effectBlocking(str4.foreach(p.println)))
      } yield s
    }

    for {
      row <- planAndSet
      (plans, sets) = row.unzip
      s <- printlnSet(sets.distinctBy(_.countSet))
      groupedPlans = plans.grouped(100).to(List)
      zipCol       = groupedPlans.zipWithIndex
      actions      = zipCol.map(s => printlnPlan(s._1, s._2))
      r <- ZIO.collectAllPar(actions.to(List))
      t <- printlnPlan1(zipCol.map(_._2).to(List))
    } yield t
  }

}
