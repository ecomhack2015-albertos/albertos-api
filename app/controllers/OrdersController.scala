package controllers

import common.domain.{LineItem, OrderDraft}
import common.helpers.ControllerUtils
import common.models.Food
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
      case Some(order) => Ok(Json.toJson(order))
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

  def updateOrder(id: String) = Action.async {
    val food = Food("04e94ebc-acc3-464f-a7e6-1bab487f70bb", "Spaghetti Carbonara", 5)
    val orderDraft = OrderDraft("a29eda3c-5cc2-4afc-bd37-6d2723fc5551", List( LineItem(food, 2) ))

    Future(Ok(Json.obj("orderDraft" -> Json.toJson(orderDraft))))
  }

}
