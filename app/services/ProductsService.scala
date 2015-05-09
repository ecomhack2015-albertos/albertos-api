package services

import common.sphere.SphereClient
import io.sphere.sdk.products.Product
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class ProductsService(sphereClient: SphereClient) {

  def getProducts(): Future[Seq[Product]] = {
    Future(Seq())
  }
}
