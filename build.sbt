name := """play-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7" // only supports Scala 2.11

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

fork in run := true

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7"
)

//lazy val metalApi = (project in file("metal-api")).
//  settings(
//    commonSettings//,
//    // other settings
//  )

//lazy val util = (project in file("util")).
//  settings(
//    commonSettings//,
//    // other settings
//  )

