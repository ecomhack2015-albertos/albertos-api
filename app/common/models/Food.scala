package common.models

import play.api.libs.json.Json

case class Food(name: String, price: Double)

object Food {
  implicit val personFormat = Json.format[Food]
}
