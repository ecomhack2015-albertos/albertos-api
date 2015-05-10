package services

import common.domain.OrderDraft
import common.models.Order
import common.sphere.SphereClient
import io.sphere.sdk.orders.commands.OrderImportCommand
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class OrdersService(sphereClient: SphereClient) {

  def getOrderById(id: String):Future[Option[Order]] = ???

  def createOrder(orderDraft: OrderDraft): Future[Option[Order]] = {
    val orderImportCommand = OrderImportCommand.of(orderDraft.toImportDraft)
    sphereClient.execute(orderImportCommand) map Order.fromSphereOrder
  }

  def updateOrderStatus() = ???
}
