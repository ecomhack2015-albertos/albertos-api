package common.macwire

import com.softwaremill.macwire.MacwireMacros._
import common.sphere.SphereClientFactory
import controllers.ProductsController
import services.ProductsService

trait WireDependencies {

  //Clients
  val sphereClient = SphereClientFactory()

  // Services
  lazy val productsService = wire[ProductsService]

  // Controllers
  lazy val productsController = wire[ProductsController]
}
