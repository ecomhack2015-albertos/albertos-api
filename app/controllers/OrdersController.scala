package controllers

import common.domain.{LineItem, OrderDraft}
import common.helpers.ControllerUtils
import common.models.Order
import io.sphere.sdk.orders.{Order => SphereOrder}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global
import services.OrdersService
import ControllerUtils.bindRequestJsonBody
import common.helpers.ImplicitConversions.ExceptionToResultConverter

import scala.concurrent.Future
import scala.util.{Failure, Success}

class OrdersController(service: OrdersService) extends Controller {

  def getOrderById(id: String) = Action.async {
    service.getOrderById(id) map {
      case Some(order) => Ok(Json.toJson(Order.fromSphereOrder(order)))
      case None => NotFound
    }
  }

  def createOrder() = Action.async { implicit request =>
    val orderDraftOption = bindRequestJsonBody(request.body)(Json.reads[OrderDraft])

    orderDraftOption match {
      case Success(orderDraft) => service.createOrder(orderDraft) map {
        order => Created(Json.obj("order" -> Json.toJson(order)))
      } recover {
        case e: Exception => e.asResult
      }
      case Failure(e) => Future(e.asResult)
    }
  }

  def updateOrderState(id: String) = Action.async {
    service.getOrderById(id) flatMap {
      case Some(sphereOrder) =>
        service.updateOrderStatus(sphereOrder) map {
          case Some(order) => Ok(Json.obj("order" -> Json.toJson(order)))
          case None => BadRequest
        }
      case None => Future(NotFound)
    }
  }

}
