package common.macwire

import common.sphere.SphereClientFactory
import controllers.ProductsController
import play.api.{Mode, Play}
import services.ProductsService

trait WireDependencies {
  import com.softwaremill.macwire.MacwireMacros._

  //Clients
  val sphereClient = SphereClientFactory()

  // Services
  lazy val productsService = wire[ProductsService]

  // Controllers
  lazy val productsController = wire[ProductsController]
}
