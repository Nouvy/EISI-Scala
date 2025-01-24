ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.4"

val sparkVersion = "3.4.0"

lazy val root = (project in file("."))
  .settings(
    name := "Test",
    libraryDependencies ++= Seq(
      "mysql" % "mysql-connector-java" % "8.0.33",
      "org.apache.spark" %% "spark-core" % sparkVersion cross CrossVersion.for3Use2_13,
      "org.apache.spark" %% "spark-sql" % sparkVersion cross CrossVersion.for3Use2_13
    )
  )