name := """albertos-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "io.sphere.sdk.jvm" % "sphere-models" % "1.0.0-M13",
  "io.sphere.sdk.jvm" %% "sphere-scala-client" % "1.0.0-M13"
)

// Force Java 8 or higher
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value // run the previous initialization
  if (sys.props("java.specification.version") < "1.8") {
    sys.error("Java 8 is required for this project.")
  }
}