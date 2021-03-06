package common.models

import common.domain.LineItem
import play.api.libs.json.Json
import scala.collection.JavaConverters._
import io.sphere.sdk.orders.{Order => SphereOrder}

case class Order(id: String, userId: String, status: String, lineItems: List[LineItem])

object Order {

  def fromSphereOrder(sphereOrder: SphereOrder): Option[Order] = {
    try {
      Some(Order(
        sphereOrder.getId(),
        sphereOrder.getCustomerId.get(),
        sphereOrder.getOrderState.name(),
        sphereOrder.getLineItems.asScala.toList.flatMap(LineItem.fromSphereLineItem)
      ))
    } catch { case e: Exception => None }
  }

  implicit def jsonFormat = Json.format[Order]

}
