package f03.mainapp

import distage._
import f03.fusion.{CounterFusion, NumberFusion}
import f06.reverseroutes.ReverseRoutes
import f03.service.{CountPlanService, CountPlanServiceImpl, DataCollection, DataCollectionImpl}
import f03.views.{CountPlanReview, CounterView, HelperView, IndexView, JsDependencies}
import f06.endpoint.{CounterEndpoint, NumberEndpoint}
import zio._

object MainApp {

  type CustomAppEnv = logging.Logging
  type AppEnv       = ZEnv with CustomAppEnv

  private val injector = Injector[RIO[AppEnv, *]]()
  private val plan     = injector.plan(NumberModule, Activation.empty, Roots(DIKey[AppRoutes[AppEnv]]))
  private val resource = injector.produce(plan)
  val routes           = resource.map(_.get[AppRoutes[AppEnv]])

  private object NumberModule extends ModuleDef {
    make[AppRoutes[AppEnv]].from[AppRoutesImpl]

    include(ViewModule)
    include(FusionModule)
    include(ConfigModule)
    include(ResourceModule)
    include(ServiceModule)
    include(EndpointModule)
  }

  private object ViewModule extends ModuleDef {
    make[IndexView]
    make[JsDependencies]
    make[HelperView]
    make[CountPlanReview]
    make[CounterView]
    make[ReverseRoutes]
  }

  private object FusionModule extends ModuleDef {
    make[NumberFusion]
    make[CounterFusion]
  }

  private object ConfigModule extends ModuleDef {
    make[AppConfig]
  }

  private object ResourceModule extends ModuleDef {
    make[AppResource].from[AppResourceImpl]
    make[SlickDB].fromResource { AppResource: AppResource =>
      AppResource.sqliteSlickManaged
    }
  }

  private object ServiceModule extends ModuleDef {
    make[DataCollection].from[DataCollectionImpl]
    make[CountPlanService].from[CountPlanServiceImpl]
  }

  private object EndpointModule extends ModuleDef {
    make[NumberEndpoint]
    make[CounterEndpoint]
  }

}
