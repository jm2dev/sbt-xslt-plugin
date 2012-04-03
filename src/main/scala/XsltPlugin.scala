package com.jm2dev

import sbt._
import Keys._
import javax.xml.transform.stream.StreamSource
import net.sf.saxon.s9api._

object XsltPlugin extends Plugin {
  override lazy val settings = Seq(commands += myCommand)

  lazy val myCommand =
    Command.command("transform") { (state: State) =>
      customCommand
      state
    }

  private def customCommand {
    val proc:Processor = new Processor(false);
    val comp:XsltCompiler = proc.newXsltCompiler();
    val exp:XsltExecutable = comp.compile(new StreamSource(new File("src/main/xslt/main.xsl")));
    val source:XdmNode = proc.newDocumentBuilder().build(new StreamSource(new File("src/main/xml/main.xml")));
    var out:Serializer = proc.newSerializer(new File("main.html"));
    out.setOutputProperty(Serializer.Property.METHOD, "html");
    out.setOutputProperty(Serializer.Property.INDENT, "yes");
    var trans:XsltTransformer = exp.load();
    trans.setInitialContextNode(source);
    trans.setDestination(out);
    trans.transform();

    println("Transformed!")
  }
}