package common.models

import common.exceptions.ProductParseException
import io.sphere.sdk.products.Product
import play.api.libs.json.Json

case class Food(id: String, name: String, price: Double)

object Food extends ModelUtils {
  implicit val foodFormat = Json.format[Food]

  def fromProduct(product: Product): Option[Food] = {
    val variant = product.getMasterData.getStaged.getMasterVariant

    try {
      Some(Food(
        product.getId,
        readStringAttribute(variant, "name"),
        readMoneyAttribute(variant, "price").getNumber.doubleValue()
      ))
    } catch { case e: Exception => throw new ProductParseException(e.getMessage) } // Todo Try[Food]
  }
}
