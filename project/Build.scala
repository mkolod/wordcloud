import sbt._
import Keys._
import play.Play._
import play.Play.autoImport._
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import ScalaJSKeys._
import com.typesafe.sbt.packager.universal.UniversalKeys
import com.typesafe.sbteclipse.core.EclipsePlugin.EclipseKeys

// Inspired by https://github.com/vmunier/play-with-scalajs-example
object Dependencies {

  lazy val shared = Def.setting(Seq())

  lazy val playApp = Def.setting(shared.value ++ Seq(
    "org.webjars"     % "jquery" % "1.11.1",
    "org.twitter4j"   % "twitter4j-core" % "4.0.2",
    "org.twitter4j"   % "twitter4j-stream" % "4.0.2",
    "ws.securesocial" %% "securesocial" % "master-SNAPSHOT" withSources,
    "ws.securesocial" %% "securesocial" % "master-SNAPSHOT" classifier "assets",
    ws
  ))

  lazy val scalaJs =  Def.setting(shared.value ++ Seq(
    "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % Versions.scalaJsDom,
    "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % Versions.scalaJsJQuery,
    "org.scala-lang.modules.scalajs" %% "scalajs-jasmine-test-framework" % scalaJSVersion % "test"
  ))

}

object Versions {

  val app = "0.1"
  val scala = "2.11.2"
  val scalaJsDom = "0.6"
  val scalaJsJQuery = "0.6"
}

object WordCloudBuild extends Build with UniversalKeys {

  override def rootProject = Some(playApp)

  lazy val playStartCmd = "playStart"

  lazy val sharedSrcDir = "sharedScala"

  val scalajsOutputDir = Def.settingKey[File]("directory for JS files")

  lazy val playApp = project enablePlugins (play.PlayScala) settings(playAppSettings: _*) aggregate(scalaJs)

  lazy val scalaJs = project settings(scalaJsSettings: _*)

  lazy val sharedScala = Project(
    id = "sharedScala",
    base = file(sharedSrcDir)
  ) settings (sharedScalaSettings: _*)

  lazy val playAppSettings = Seq(
    name := "playApp",
    version := Versions.app,
    scalaVersion := Versions.scala,
    scalajsOutputDir := (classDirectory in Compile).value / "public" / "javascripts",
    compile in Compile <<= (compile in Compile) dependsOn (fastOptJS in (scalaJs, Compile)) dependsOn copySourceMapsTask,
    dist <<= dist dependsOn (fullOptJS in (scalaJs, Compile)),
    stage <<= stage dependsOn (fullOptJS in (scalaJs, Compile)),
    libraryDependencies ++= Dependencies.playApp.value,
    commands ++= Seq(playStartCommand, startCommand),
    resolvers ++= Seq(Resolver.sonatypeRepo("releases"), Resolver.sonatypeRepo("snapshots"))
  ) ++ sharedDirectorySettings

  lazy val scalaJsSettings = scalaJSSettings ++ Seq(
    name := "scalajs",
    version := Versions.app,
    scalaVersion := Versions.scala,
    persistLauncher := true,
    persistLauncher in Test := false,
    relativeSourceMaps := true,
    libraryDependencies ++= Dependencies.scalaJs.value,
    skip in ScalaJSKeys.packageJSDependencies := false,
    ScalaJSKeys.jsDependencies ++= Seq(
      "org.webjars" % "bootstrap" % "3.3.1" / "bootstrap.js",
      "org.webjars" % "d3js" % "3.5.3" / "d3.js",
      "org.webjars" % "d3-cloud" % "1.0.5" / "d3.layout.cloud.js"
    )
  ) ++ sharedDirectorySettings

    lazy val sharedScalaSettings = Seq(
      name := "shared-scala-example",
      libraryDependencies ++= Dependencies.shared.value
    )

  lazy val sharedDirectorySettings = Seq(
    unmanagedSourceDirectories in Compile += new File((file(".") / sharedSrcDir / "src" / "main" / "scala").getCanonicalPath),
    unmanagedSourceDirectories in Test += new File((file(".") / sharedSrcDir / "src" / "test" / "scala").getCanonicalPath),
    unmanagedResourceDirectories in Compile += file(".") / sharedSrcDir / "src" / "main" / "resources",
    unmanagedResourceDirectories in Test += file(".") / sharedSrcDir / "src" / "test" / "resources"
  )

  lazy val copySourceMapsTask = Def.task {
    val scalaFiles = (Seq(scalaJs.base) ** ("*.scala")).get
    for (scalaFile <- scalaFiles) {
      val target = new File((classDirectory in Compile).value, scalaFile.getPath)
      IO.copyFile(scalaFile, target)
    }
  }

  // The new 'start' command optimises the JS before calling 'playStart'
  lazy val startCommand = Command.args("start", "<port>") { (state: State, args: Seq[String]) =>
    Project.runTask(fullOptJS in (scalaJs, Compile), state)
    state.copy(remainingCommands = s"$playStartCmd ${args.mkString(" ")}" +: state.remainingCommands)
  }

  lazy val playStartCommand = Command.make(playStartCmd)(play.Play.playStartCommand.parser)
}