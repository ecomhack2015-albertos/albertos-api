package common.macwire

import com.softwaremill.macwire.{MacwireMacros, Wired}

object Wirehelper {
  import MacwireMacros._
  lazy val wired = wiredInModule(new WireDependencies {})

  def apply(): Wired = wired
}
