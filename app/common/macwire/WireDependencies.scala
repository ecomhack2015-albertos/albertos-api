package common.macwire

import common.sphere.SphereClientFactory
import play.api.{Mode, Play}

trait WireDependencies {
  import com.softwaremill.macwire.MacwireMacros._

  //Clients
  val sphereClient = SphereClientFactory()
}
