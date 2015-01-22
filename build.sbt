name := """wordcloud"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

fork := false

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"
)

scalaJSSettings

