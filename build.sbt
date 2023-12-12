ThisBuild / organization := "com.example"
ThisBuild / version := "1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    dockerConfig:_*
  )
  .settings(
    name := "playapp",
    libraryDependencies ++= Seq(
      guice,
      "org.playframework"       %% "play-json"          % "3.0.1",
      "org.scalatestplus.play"  %% "scalatestplus-play" % "7.0.0"     % Test,
    )
  )

lazy val integration = project.in(file("integration"))
  .enablePlugins(PlayScala)
  .dependsOn(root)
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play"  %% "scalatestplus-play" % "7.0.0"     % Test,
    )
  )

lazy val features = project.in(file("features"))
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      "io.cucumber"             %% "cucumber-scala"           % "8.14.1"    % Test,
      "io.cucumber"              % "cucumber-core"            % "7.11.1"    % Test,
      "io.cucumber"              % "cucumber-jvm"             % "7.11.1"    % Test,
      "io.cucumber"              % "cucumber-junit"           % "7.11.1"    % Test,
      "org.scalatest"           %% "scalatest-flatspec"       % "3.2.17"    % Test,
      "org.scalatest"           %% "scalatest-shouldmatchers" % "3.2.17"    % Test,
      "org.scalatestplus"       %% "selenium-4-12"            % "3.2.17.0"  % Test,
    )
  )

lazy val performance = project.in(file("performance"))
  .enablePlugins(GatlingPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "io.gatling.highcharts"    % "gatling-charts-highcharts"  % "3.9.5" % Test,
      "io.gatling"               % "gatling-test-framework"     % "3.9.5" % Test,
    )
  )

val dockerConfig = {
  import com.typesafe.sbt.packager.docker.DockerPlugin.autoImport.dockerPermissionStrategy
  import com.typesafe.sbt.packager.docker.{DockerChmodType, DockerPermissionStrategy}

  import scala.collection.Seq
  Seq(
    dockerBaseImage := "openjdk:21",
    dockerChmodType := DockerChmodType.UserGroupWriteExecute,
    dockerPermissionStrategy := DockerPermissionStrategy.CopyChown,
    dockerExposedPorts ++= Seq(9000),
  )
}
