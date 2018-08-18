name := "handySergeantSkillLambda"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.amazon.alexa" % "ask-sdk" % "2.4.1"
)

lazy val commonSettings = Seq(
  organization := "org.crystaltreecode",
  version := "1.0",
  scalaVersion := "2.12",
  test in assembly := {}
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