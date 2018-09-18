name := "jobbrokersmokeworker"
organization := "com.ruimo"
version := "1.0-SNAPSHOT"
scalaVersion := "2.12.6"

resolvers += "ruimo.com" at "http://static.ruimo.com/release"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "com.ruimo" %% "jobbroker-client-scala" % "1.0-SNAPSHOT"
libraryDependencies += "com.googlecode.log4jdbc" % "log4jdbc" % "1.2"
libraryDependencies += "ch.qos.logback" % "logback-core" % "1.2.3"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "org.specs2" %% "specs2-core" % "4.3.3" % Test

enablePlugins(JavaAppPackaging)
