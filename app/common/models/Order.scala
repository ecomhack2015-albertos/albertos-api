package common.models

import common.domain.LineItem
import scala.collection.JavaConverters._
import io.sphere.sdk.orders.{Order => SphereOrder}

case class Order(userId: String, status: String, lineItems: List[LineItem])

object Order {
  def fromSphereOrder(sphereOrder: SphereOrder): Option[Order] = {
    try {
      Some(Order(
        sphereOrder.getCustomerId.get(),
        sphereOrder.getOrderState.name(),
        sphereOrder.getLineItems.asScala.toList.flatMap(LineItem.fromSphereLineItem)
      ))
    } catch { case e: Exception => None }
  }
}
