package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ProductsService
import scala.concurrent.ExecutionContext.Implicits.global

class ProductsController(service: ProductsService) extends Controller {

  def getProducts() = Action.async {
    service.getProducts() map {
      case products: List[Product] => Ok(Json.toJson(products))
    } recover {
      case e: Exception => InternalServerError(e.getMessage)
    }
  }

}
