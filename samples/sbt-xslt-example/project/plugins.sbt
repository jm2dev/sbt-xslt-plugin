resolvers ++= Seq (
    "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
    "scalasbt" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"
)

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.0.0")

addSbtPlugin("com.jm2dev" %% "sbt-xslt-plugin" % "0.1.6")
