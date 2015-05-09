package services

import common.helpers.ImplicitConversions
import common.models.Cart
import common.sphere.SphereClient
import io.sphere.sdk.carts.{Cart => SphereCart}
import io.sphere.sdk.orders.commands.OrderFromCartCreateCommand

class OrdersService(sphereClient: SphereClient) {

//  def getOrderById() = ???

  def createOrder(cart: Cart) = {

    val createCommand = OrderFromCartCreateCommand.of(SphereCart.fromCart(cart))
  }

//  def updateOrderStatus() = ???
}
