import sbtassembly.AssemblyPlugin.autoImport.assemblyJarName

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.11.12"
ThisBuild / version := "0.1-SNAPSHOT"
ThisBuild / scalacOptions += "-deprecation"
ThisBuild / javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

lazy val deduplicator = (project in file(".")).aggregate(main)

lazy val main = (project in file("main"))
  .settings(
    name := "main"
    , assemblyJarName  in assembly := "deduplicator.jar"
    // skip running tests with assembly (https://github.com/sbt/sbt-assembly)
    , test in assembly := {}
    , libraryDependencies ++= Seq(
      deps.sparkSql % "provided",
      deps.log4j,
      deps.slf4jLog,
      deps.slf4jApi,
      deps.scalaTest % "test",
      deps.scalaMock % "test",
	    // this hadoop-aws is important for local testing
      deps.hadoop % "test" excludeAll(
        ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-databind"),
        ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-annotations"),
        ExclusionRule(organization = "com.fasterxml.jackson.core", name = "jackson-core")
      ),
    ),
    assemblyMergeStrategy in assembly := {
      case "log4j.properties" => MergeStrategy.first
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
