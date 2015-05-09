package controllers

import common.models.Food
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.FoodsService
import scala.concurrent.ExecutionContext.Implicits.global

class FoodsController(service: FoodsService) extends Controller {

  def getProducts() = Action.async {
    service.getProducts() map {
      case foods: List[Food] => Ok(Json.obj("foods" -> Json.toJson(foods)))
    } recover {
      case e: Exception => InternalServerError(e.getMessage)
    }
  }

}
