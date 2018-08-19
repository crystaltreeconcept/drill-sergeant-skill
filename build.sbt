name := "handySergeantSkillLambda"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.amazon.alexa" % "ask-sdk" % "2.4.1"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies ++= Seq("junit" % "junit" % "4.12", "org.scalacheck" %% "scalacheck" % "1.13.4", "org.scalamock" %% "scalamock" % "4.1.0" % Test)

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
