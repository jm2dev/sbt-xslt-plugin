organization := "com.jm2dev"

name := "SBT XSLT plugin example"

version := "1.0"
                           
scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
  "net.sf.saxon" % "Saxon-HE" % "9.4"
)

resolvers ++= Seq(
          "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

