name := """play-scala"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.7" // only supports Scala 2.11

fork in run := true

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.7"
)

resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)

val scalazVersion = "7.2.9"
lazy val commonLibraryDependencies = libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,

  "org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scalaz" %% "scalaz-effect" % scalazVersion,

  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

// projects
lazy val akkaBatch = (project in file("akka-batch"))
  .settings(commonSettings: _*)
  .settings(commonLibraryDependencies: _*)
  .settings(libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.3.11",
    "com.typesafe.akka" %% "akka-testkit" % "2.3.11" % "test",
    "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  ))

lazy val metalApi = (project in file("metal-api"))
  .settings(commonSettings: _*)
  .settings(commonLibraryDependencies: _*)
  .settings(libraryDependencies ++= Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
    "joda-time" % "joda-time" % "2.8",
    "org.joda" % "joda-convert" % "1.7",
    "net.ruippeixotog" %% "scala-scraper" % "1.2.0"
  ))
  .enablePlugins(PlayScala)

lazy val root = (project in file("."))
  .aggregate(
    akkaBatch,
    metalApi
  )
  .enablePlugins(PlayScala)
