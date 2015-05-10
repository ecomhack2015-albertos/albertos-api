package common.domain

import io.sphere.sdk.customers.Customer
import play.api.libs.json.Json

case class User(id: String, version: Long, username: String, email: String)

object User {

  def fromCustomer(c: Customer): User = User(c.getId, c.getVersion, c.getFirstName, c.getEmail)

  implicit def jsonFormat = Json.format[User]
}
