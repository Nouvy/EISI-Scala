ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.4"

lazy val root = (project in file("."))
  .settings(
    name := "Test",
    libraryDependencies ++= Seq(
      "mysql" % "mysql-connector-java" % "8.0.33"
    )
  )
