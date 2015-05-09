package common.models

import io.sphere.sdk.products.Product
import play.api.libs.json.Json

case class Food(id: String, Version: Long, name: String, price: Double)

object Food extends ModelUtils {
  implicit val personFormat = Json.format[Food]

  def fromProduct(product: Product): Option[Food] = {
    val variant = product.getMasterData.getStaged.getMasterVariant

    try {
      Option(Food(
        product.getId,
        product.getVersion,
        readStringAttribute(variant, "name"),
        readDoubleAttribute(variant, "price")
      ))
    } catch { case e: Exception => None } // Todo Try[Food]
  }
}
