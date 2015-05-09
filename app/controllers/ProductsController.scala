package controllers

import play.api.mvc.{Action, Controller}
import services.ProductsService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProductsController(service: ProductsService) extends Controller {

  def getProducts() = Action.async {
    Future(Ok)
  }

}
