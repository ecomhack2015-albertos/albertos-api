package services

import common.sphere.SphereClient
import io.sphere.sdk.products.Product
import io.sphere.sdk.products.queries.ProductQuery
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class ProductsService(sphereClient: SphereClient) {

  def getProducts(): Future[List[Product]] = {
    sphereClient.execute(ProductQuery.of()) map (res => res.getResults.asScala.toList)
  }
}
