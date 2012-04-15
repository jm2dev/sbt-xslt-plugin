
libraryDependencies <+= (sbtVersion) { (version) =>
  "org.scala-tools.sbt" %% "scripted-plugin" % version
}

resolvers ++= Seq (
  "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
  "less is" at "http://repo.lessis.me",
  "coda" at "http://repo.codahale.com"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")

addSbtPlugin("me.lessis" % "ls-sbt" % "0.1.1")
