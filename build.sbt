name := "handySergeantSkillLambda"

version := "0.1"

scalaVersion := "2.12.6"

// https://mvnrepository.com/artifact/com.amazon.alexa/ask-sdk
libraryDependencies += "com.amazon.alexa" % "ask-sdk" % "2.6.0"
// https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.6"
// https://mvnrepository.com/artifact/commons-logging/commons-logging
libraryDependencies += "commons-logging" % "commons-logging" % "1.2"
// https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.11.1"


libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies ++= Seq("junit" % "junit" % "4.12", "org.scalacheck" %% "scalacheck" % "1.13.4", "org.scalamock" %% "scalamock" % "4.1.0" % Test)
libraryDependencies ++= Seq(
  "com.gu" %% "scanamo" % "1.0.0-M6"
)

lazy val commonSettings = Seq(
  organization := "org.crystaltreecode",
  version := "1.0",
  scalaVersion := "2.12",
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).settings()

//for more jars that are part of project
//lazy val utils = (project in file("utils")).
//  settings(commonSettings: _*).
//  settings(
//    assemblyJarName in assembly := "utils.jar",
//    // more settings here ...
//  )
