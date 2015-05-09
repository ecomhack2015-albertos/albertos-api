package services

import common.models.Food
import common.sphere.SphereClient
import io.sphere.sdk.products.queries.ProductQuery
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

class FoodsService(sphereClient: SphereClient) {

  def getProducts(): Future[List[Food]] = {
    sphereClient.execute(ProductQuery.of()) map {
      res => res.getResults.asScala.toList.map(Food.fromProduct).flatten
    }
  }
}
