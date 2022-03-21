import org.apache.logging.log4j.core.config.composite.MergeStrategy
import sun.security.tools.PathList
//
//ThisBuild / version := "0.1.0-SNAPSHOT"
//
//ThisBuild / scalaVersion := "2.13.8"
//
//javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
//
//lazy val root = (project in file(".")).
//  settings(
//    name := "digitalEnvoy",
//    version := "1.0",
//    scalaVersion := "2.11.4",
//    retrieveManaged := true,
//    libraryDependencies += "com.amazonaws" % "aws-lambda-java-core" % "1.0.0",
//    libraryDependencies += "com.amazonaws" % "aws-lambda-java-events" % "1.0.0"
//  )
//
//mergeStrategy in assembly <
//  {
//    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//    case x => MergeStrategy.first
//  }
name := "hello"

version := "1.0"

scalaVersion := "2.13.8"

//libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"
libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.11"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.11" % "test"