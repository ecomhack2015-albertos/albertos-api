package controllers

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.UsersService

import scala.concurrent.ExecutionContext.Implicits.global

class UsersController(userService: UsersService) extends Controller {

  def getUserByMail(mail: String) = Action.async {
    userService.getUserByEmail(mail).map {
      case Some(user) => Ok(Json.toJson(user))
      case None => NotFound
    }
  }
}
