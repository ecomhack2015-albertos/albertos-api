package controllers

import common.domain.OrderDraft
import common.helpers.ControllerUtils
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import services.OrdersService

import scala.concurrent.Future

class OrdersController(service: OrdersService) extends Controller {

  def getOrderById(id: String) = Action.async {
    service.getOrderById(id) map {
      case Some(order) => Ok(Json.toJson(order))
      case None => NotFound
    }
  }

  def createOrder() = Action.async { implicit request =>
    request.body.asJson match {
      case Some(json) => json.asOpt[OrderDraft] match {
        case Some(orderDraft) => service.createOrder(orderDraft) map {
          case Some(order) => Created(Json.obj("order" -> Json.toJson(order)))
          case _ => BadRequest(Json.obj("error" -> "Unknown error"))
        }
        case None => Future(BadRequest(Json.obj("error" -> "Cannot parse json")))
      }
      case None => Future(BadRequest(Json.obj("error" -> "Missing body")))
    }
  }

  def updateOrder(id: String) = Action.async {
    Future(Ok(id))
  }

}
