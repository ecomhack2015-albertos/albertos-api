package services

import common.domain.User
import common.helpers.ImplicitConversions._
import common.sphere.SphereClient
import io.sphere.sdk.customers.queries.CustomerQuery
import io.sphere.sdk.customers.Customer
import io.sphere.sdk.queries.PagedQueryResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class UsersService(sphereClient: SphereClient) {

  def getUserByEmail(email: String): Future[Option[User]] = {
    val query = CustomerQuery.of().byEmail(email)

    sphereClient.execute(query) map {
      case (res: PagedQueryResult[Customer]) => res.head.asScala map {
        case (customer: Customer) => User.fromCustomer(customer)
      }
    }
  }

}


