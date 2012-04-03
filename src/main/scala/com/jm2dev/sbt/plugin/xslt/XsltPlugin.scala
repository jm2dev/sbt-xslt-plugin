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
        transformCommand("main.xml", "main.xsl")
        state
    }

  private val dataSource = "src/main/xml/"
  private val xslSource = "src/main/xslt/"
  private val XslFile = xslSource + "main.xsl"
  private val dataTarget = "target/xslt-plugin/"

  private def transformCommand(xmlFileName: String, xslFileName: String, outputFormat: String = "xml", indentFormat: String = "yes") {
    val proc = new Processor(false);
    val comp = proc.newXsltCompiler();
    val exp = comp.compile(new StreamSource(new File(XslFile)));
    val source = proc.newDocumentBuilder().build(new StreamSource(new File(dataSource + xmlFileName)));
    var out = proc.newSerializer(new File("%s%s.%s".format(dataTarget, xmlFileName, outputFormat)));
    out.setOutputProperty(Serializer.Property.METHOD, outputFormat);
    out.setOutputProperty(Serializer.Property.INDENT, indentFormat);
    var trans: XsltTransformer = exp.load();
    trans.setInitialContextNode(source);
    trans.setDestination(out);
    trans.transform();

    println("Transformed!")
  }
}