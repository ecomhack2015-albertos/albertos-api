package services

import java.util.Optional

import common.domain.OrderDraft
import common.models.Order
import common.sphere.SphereClient
import io.sphere.sdk.orders.queries.OrderByIdFetch
import io.sphere.sdk.orders.{Order => SphereOrder}
import io.sphere.sdk.orders.OrderState
import io.sphere.sdk.orders.commands.updateactions.ChangeOrderState
import io.sphere.sdk.orders.commands.{OrderUpdateCommand, OrderImportCommand}
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import common.helpers.ImplicitConversions._

class OrdersService(sphereClient: SphereClient) {

  def getOrderById(id: String):Future[Option[SphereOrder]] = {
    sphereClient.execute(OrderByIdFetch.of(id)) map { o => o.asScala }
  }

  def createOrder(orderDraft: OrderDraft): Future[Option[Order]] = {
    val orderImportCommand = OrderImportCommand.of(orderDraft.toImportDraft)
    sphereClient.execute(orderImportCommand) map Order.fromSphereOrder
  }

  def updateOrderStatus(order: SphereOrder): Future[Option[Order]] = {
    val orderUpdateCommand = OrderUpdateCommand.of(order,
      List(ChangeOrderState.of(OrderState.COMPLETE)).asJava)
    sphereClient.execute(orderUpdateCommand) map Order.fromSphereOrder
  }
}
