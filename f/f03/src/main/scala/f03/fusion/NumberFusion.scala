package f03.fusion

import f06.endpoint.NumberEndpoint
import f03.service.CountPlanService
import f03.views.{CountPlanReview, HelperView, IndexView, ReSortCountExecutionPage}
import sttp.model.StatusCode
import sttp.tapir.ztapir._
import zio._
import zio.logging._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class NumberFusion(
  indexView: IndexView,
  helperView: HelperView,
  countPlanReview: CountPlanReview,
  countPlanService: CountPlanService,
  numberEndpoint: NumberEndpoint,
  reSortCountExecutionView: ReSortCountExecutionPage
) {

  type AppEnv = f03.mainapp.MainApp.AppEnv

  val pageHelper               = numberEndpoint.pageHelper.zServerLogic(_ => ZIO.succeed(helperView.view))
  val index                    = numberEndpoint.index.zServerLogic(_ => ZIO.succeed(indexView.view))
  val countPlanReviewPage      = numberEndpoint.countPlanReviewPage.zServerLogic(_ => ZIO.succeed(countPlanReview.view))
  val reSortCountExecutionPage = numberEndpoint.reSortCountExecutionPage.zServerLogic(_ => ZIO.succeed(reSortCountExecutionView.view))

  val deleteAllCountPlan =
    numberEndpoint.deleteAllCountPlan.zServerLogic { _ =>
      val action = countPlanService.deleteAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("删除所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle)
    }

  val resetAllCountPlan =
    numberEndpoint.resetAllCountPlan.zServerLogic { _ =>
      val action = countPlanService.resetAll()
      def errorHandle(e: Throwable) =
        for (_ <- Logging.throwable("重置所有 CountPlan 发生异常", e))
          yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

      action.flatMapError(errorHandle)
    }

  val insertAllCountPlan = numberEndpoint.insertAllCountPlan.zServerLogic { _ =>
    val action = countPlanService.insertAllDistinct()
    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("提交所有 CountPlan 发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    for (logging <- ZIO.identity[Logging]) yield {
      Runtime.default
        .unsafeRunToFuture(action.provideSome((s: ZEnv) => s ++ logging))
        .onComplete {
          case Success(value) =>
            println("11" * 100)
            println("提交 countPlan 完毕")
            println("22" * 100)
          case Failure(exception) => exception.printStackTrace()
        }(ExecutionContext.global)
      2L
    }
  }

  val countCountPlan = numberEndpoint.countCountPlan.zServerLogic { _ =>
    val action = countPlanService.count()
    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("统计 CountPlan 数量发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }

  /*val reSortCount = numberEndpoint.reSortCount.zServerLogic { _ =>
    val action = counterReSortedService.sortedPlan()
    def errorHandle(e: Throwable) =
      for (_ <- Logging.throwable("重排序发生异常", e))
        yield ((), StatusCode.InternalServerError, s"发生程序异常，调试信息：${e.getMessage}")

    action.flatMapError(errorHandle)
  }*/

  val routes = List(
    index.widen[AppEnv],
    countPlanReviewPage.widen[AppEnv],
    deleteAllCountPlan.widen[AppEnv],
    resetAllCountPlan.widen[AppEnv],
    insertAllCountPlan.widen[AppEnv],
    countCountPlan.widen[AppEnv],
    reSortCountExecutionPage.widen[AppEnv]
    // reSortCount.widen[AppEnv]
  )
  val lowLevelRoutes = List(pageHelper.widen[AppEnv])
  val docs = List(
    numberEndpoint.pageHelper,
    numberEndpoint.index,
    numberEndpoint.countPlanReviewPage,
    numberEndpoint.deleteAllCountPlan,
    numberEndpoint.resetAllCountPlan,
    numberEndpoint.insertAllCountPlan,
    numberEndpoint.countCountPlan,
    numberEndpoint.reSortCountExecutionPage,
    numberEndpoint.reSortCountExecutionPage
  )

}
