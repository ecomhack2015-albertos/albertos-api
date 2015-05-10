package common.domain

import io.sphere.sdk.customers.Customer

case class User(id: String, version: Long, username: String, email: String)

object User {

  def fromCustomer(c: Customer): User = User(c.getId, c.getVersion, c.getFirstName, c.getEmail)

}
