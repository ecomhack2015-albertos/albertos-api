package common.sphere

import common.helpers.Configloader
import io.sphere.sdk.attributes.AttributeDefinition
import io.sphere.sdk.producttypes.{ProductTypeBuilder, ProductType}
import io.sphere.sdk.producttypes.queries.ProductTypeQuery
import scala.collection.JavaConverters._
import scala.concurrent.Await
import scala.concurrent.duration._

trait ProductTypes {
  def food: ProductType
}

class SphereProductTypes(sphereClient: SphereClient) extends ProductTypes {
  lazy val food: ProductType = queryFoodType

  private def queryFoodType: ProductType = {
    val typeName = Configloader.getStringOpt("food.typeName").get
    Await.result(sphereClient.execute(ProductTypeQuery.of().byName(typeName)), 10 seconds).getResults.get(0)
  }
}

object MockProductTypes extends ProductTypes {
  val food: ProductType = ProductTypeBuilder.of("id", "name", "desc", List.empty[AttributeDefinition].asJava).build()
}