sbtPlugin := true

name := "sbt-xslt-plugin"

organization := "com.jm2dev"

version := "0.1.6"

description := "SBT plugin to perform XSL Transformations."

homepage := Some(new URL("http://github.com/jm2dev/sbt-xslt-plugin"))

scalaVersion := "2.9.1"

seq(scriptedSettings: _*)

seq(lsSettings: _*)

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "1.6.1" % "test",
    "net.sf.saxon" % "Saxon-HE" % "9.4"
    )

publishTo := Some(Resolver.url("sbt-plugin-releases", new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns))

publishMavenStyle := false

LsKeys.tags in LsKeys.lsync := Seq("plugin", "xslt", "xml", "transformation")
