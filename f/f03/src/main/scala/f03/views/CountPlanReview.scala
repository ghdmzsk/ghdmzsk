package f03.views

import scalatags.Text.all._
import scalatags.Text.{attrs => attr, styles => css}

class CountPlanReview(jsDependencies: JsDependencies) {

  private val pageTitle = tag("title")

  val view = "<!DOCTYPE html>" + html(
    head(
      meta(charset := "UTF-8"),
      pageTitle("计算计划统计"),
      jsDependencies.jquery,
      jsDependencies.main(),
      script(`type` := "text/javascript")("""
          |(function() {
          |  initCountPlanReviewPage();
          |})();
          |""".stripMargin)
    ),
    body(
      h2(textAlign.center)("计算计划统计"),
      div(textAlign.center)(
        div(id := "reviewButton")(paddingTop := 5, paddingBottom := 5)(button("统计"))
      ),
      div(paddingTop := 5)(
        div(id := "countMessage")(textAlign.center)("请开始统计"),
        div(id := "countInfo")(display.none)(
          table(marginLeft.auto, marginRight.auto)(
            tr(td("计算计划数量"), td(span(id := "countPlanAllCount"))),
            tr(td("已计算计算计划"), td(span(id := "finishedCountCount"))),
            tr(td("未计算计算计划"), td(span(id := "waitForCountCount"))),
            tr(td("结果集数量"), td(span(id := "countSetCount"))),
            tr(td("重排序后结果数量"), td(span(id := "reSortedCountSet")))
          )
        )
      )
    )
  )
}
