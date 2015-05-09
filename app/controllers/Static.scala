package controllers

import play.api.mvc.{Action, Controller}

class Static extends Controller {

  def status = Action { Ok("Albertos-API up and running") }

}
