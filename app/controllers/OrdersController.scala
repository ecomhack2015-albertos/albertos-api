package controllers

import play.api.mvc.{Action, Controller}
import services.OrdersService

import scala.concurrent.Future

class OrdersController(service: OrdersService) extends Controller {

  def updateOrder(id: String) = Action.async {
    Future(Ok)
  }

}
