package com.jm2dev.sbt.plugin.xslt

import sbt._
import Keys._
import javax.xml.transform.stream.StreamSource
import net.sf.saxon.s9api._

object XsltPlugin extends Plugin {
  val xsltPluginDataSources = SettingKey[String]("xslt-plugin-data-sources", "Directory location for files to transform")
  val xsltPluginMasterFile = SettingKey[String]("xslt-plugin-master-file", "XSL Template to apply on data source files.")
  val xsltPluginTarget = SettingKey[String]("xslt-plugin-target", "Directory location for transformed files.")

  val xsltPluginSettings = Seq(
    xsltPluginDataSources := dataSource,
    xsltPluginMasterFile := XslFile,
    xsltPluginTarget := dataTarget
  )

  override lazy val settings = Seq(commands += transform)

  lazy val transform =
    Command.command("transform") {
      (state: State) =>
        transformCommand(new File(dataSource))
        state
    }

  private def transformCommand(dataSourceDirectory: File) {
    val listOfFiles = getListOfFiles(dataSourceDirectory)
    listOfFiles.map( (file: File) => transform(file) )
  }

  // default values
  private val dataSource = "src/main/xml"
  private val XslFile = "src/main/xslt/main.xsl"
  private val dataTarget = "target/xslt-plugin/"

  // set up transformer
  private val booksXsl = new File(XslFile)
  private val proc: Processor = new Processor(false);
  private val comp: XsltCompiler = proc.newXsltCompiler();
  private val exp: XsltExecutable = comp.compile(new StreamSource(booksXsl));

  private def transform(input: File) {
    val output = outputFile(input.getName)
    val source: XdmNode = proc.newDocumentBuilder().build(new StreamSource(input));
    var out: Serializer = proc.newSerializer(output);
    out.setOutputProperty(Serializer.Property.METHOD, "xml");
    out.setOutputProperty(Serializer.Property.INDENT, "yes");
    var trans: XsltTransformer = exp.load();
    trans.setInitialContextNode(source);
    trans.setDestination(out);
    trans.transform();

    println(input.getName + " Transformed!")
  }

  private def outputFile(name: String):File = {
    return new File(dataTarget + name)
  }

  private def getListOfFiles(directory: File):Array[File] = {
    return (directory).listFiles.filter(_.isFile).map(_.getAbsoluteFile)
  }
}