package common.macwire

import com.softwaremill.macwire.MacwireMacros._
import common.sphere.{MockProductTypes, SphereProductTypes, ProductTypes, SphereClientFactory}
import controllers._
import migrations.ProductTypeMigrations
import play.api.{Mode, Play}
import services._

trait WireDependencies {

  //Clients
  val sphereClient = SphereClientFactory()

  // Services
  lazy val foodsService = wire[FoodsService]
  lazy val ordersService = wire[OrdersService]

  // Controllers
  lazy val staticController = wire[StaticController]
  lazy val foodsController = wire[FoodsController]
  lazy val ordersController = wire[OrdersController]

  // Migrations
  lazy val productTypeMigration = wire[ProductTypeMigrations]

  // Common
  lazy val productTypes: ProductTypes =
    if (Play.current.mode == Mode.Test) MockProductTypes
    else wire[SphereProductTypes]
}
