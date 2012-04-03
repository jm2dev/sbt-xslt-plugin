
libraryDependencies <+= (sbtVersion) { (version) =>
  "org.scala-tools.sbt" %% "scripted-plugin" % version
}

resolvers ++= Seq (
    "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")
