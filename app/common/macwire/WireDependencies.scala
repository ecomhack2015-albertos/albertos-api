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

  // Controllers
  lazy val foodsController = wire[FoodsController]

  // Migrations
  lazy val productTypeMigration = wire[ProductTypeMigrations]

  // Common
  lazy val productTypes: ProductTypes =
    if (Play.current.mode == Mode.Test) MockProductTypes
    else wire[SphereProductTypes]
}
