name := """metal-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "org.scalaz" %% "scalaz-core" % "7.2.9",
  "net.ruippeixotog" %% "scala-scraper" % "1.2.0"
)

