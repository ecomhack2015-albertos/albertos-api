package services

import common.domain.OrderDraft
import common.models.Order
import common.sphere.SphereClient
import io.sphere.sdk.orders.commands.OrderImportCommand
import scala.concurrent.ExecutionContext.Implicits.global

class OrdersService(sphereClient: SphereClient) {

//  def getOrderById() = ???

  def createOrder(orderDraft: OrderDraft) = {
    val orderImportCommand = OrderImportCommand.of(orderDraft.toImportDraft)
    sphereClient.execute(orderImportCommand) map Order.fromSphereOrder
  }

//  def updateOrderStatus() = ???
}
