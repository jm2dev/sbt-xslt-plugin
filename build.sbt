sbtPlugin := true

name := "sbt-xslt-plugin"

organization := "com.jm2dev"

version := "0.1.6"

scalaVersion := "2.9.1"

seq(scriptedSettings: _*)

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "net.sf.saxon" % "Saxon-HE" % "9.4"
    )

publishTo := Some(Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))

publishMavenStyle := false