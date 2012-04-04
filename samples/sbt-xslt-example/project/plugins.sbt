resolvers ++= Seq (
    "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")

libraryDependencies ++= Seq(
    "net.sf.saxon" % "Saxon-HE" % "9.4"
)

addSbtPlugin("com.jm2dev" %% "sbt-xslt-plugin" % "0.1.5")
