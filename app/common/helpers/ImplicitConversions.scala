package common.helpers

import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results._

object ImplicitConversions {

  implicit class OptionConverter[T](x: java.util.Optional[T]) {
    def asScala: Option[T] = {
      if (x.isPresent) Some[T](x.get())
      else Option.empty[T]
    }
  }

  implicit class ExceptionToResultConverter(x: Throwable) {
    def asResult: Result = {
      x match {
        case _ => BadRequest(errorJson(x.getMessage))
      }
    }

    def errorJson(message: String) = Json.obj("error" -> message)
  }

}
