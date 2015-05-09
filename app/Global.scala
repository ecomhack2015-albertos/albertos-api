import java.util.concurrent.TimeUnit

import com.softwaremill.macwire.Macwire
import common.macwire.WireHelper
import migrations.ProductTypeMigrations
import play.api._
import scala.concurrent.Await
import scala.concurrent.duration.FiniteDuration

object Global extends GlobalSettings with Macwire {

  override def getControllerInstance[A](controllerClass: Class[A]): A = {
    WireHelper().lookupSingleOrThrow(controllerClass)
  }

  override def onStart(app: Application): Unit = {
    if (Play.current.mode != Mode.Test) migrations()
  }

  def migrations() = {
    Await.result(WireHelper().lookupSingleOrThrow(classOf[ProductTypeMigrations]).run(), new FiniteDuration(30, TimeUnit.SECONDS))
  }
}

