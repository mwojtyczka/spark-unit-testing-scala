import sbt._

object versions {
  val spark = "2.4.2"
  val scalaCheck = "1.14.0"
  val log4j = "1.2.17"
  val slf4j = "1.7.25"
}

object deps {
  val sparkSql = "org.apache.spark" %% "spark-sql" % versions.spark
  val scalaCheck = "org.scalacheck" %% "scalacheck" % versions.scalaCheck
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  val scalactic = "org.scalactic" %% "scalactic" % "3.0.8"
  val scalaMock = "org.scalamock" %% "scalamock" % "4.2.0"
  val slf4jLog = "org.slf4j" % "slf4j-log4j12" % versions.slf4j
  val slf4jApi = "org.slf4j" % "slf4j-api" % versions.slf4j
  val log4j = "log4j" % "log4j" % versions.log4j
  val hadoop = "org.apache.hadoop" % "hadoop-aws" % "2.8.1"
}
