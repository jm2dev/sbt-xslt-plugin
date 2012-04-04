package com.jm2dev.sbt.plugin.xslt

import org.scalatest.{GivenWhenThen, Spec}
import org.scalatest.matchers.ShouldMatchers
import javax.xml.transform.stream.StreamSource
import java.io.File
import net.sf.saxon.s9api._

class TransformationTests extends Spec
  with GivenWhenThen
  with ShouldMatchers
{
  describe("XSLT transformation with Saxon HE") {
    it("should transform xml file using xslt file") {
      given("a valid xml and xsl files")
      val booksXml = new File("src/test/resources/libros.xml")

      when("xml file is transformed using xslt file")
      transform(booksXml)

      then("a valid xml file is obtained")
      val actualOutput = new File("target/xslt-plugin/libros.xml")
      actualOutput.canRead should be(true)
      actualOutput.getTotalSpace should be > (0L)
    }

    it("should transform files under given directory") {
      given("valid xml files location")
      val dataSource = new File("src/test/resources")

      when("I transform them with XSLT file")

      val listOfFiles = getListOfFiles(dataSource)
      listOfFiles.map( (file: File) => transform(file) )

      then("valid xml files are obtained")
      listOfFiles.map( (file: File) => {
        val actualOutput = new File("target/xslt-plugin/" + file.getName)
        actualOutput.canRead should be(true)
        actualOutput.getTotalSpace should be > (0L)
      } )

    }
  }

  private def getListOfFiles(directory: File):Array[File] = {
    return (directory).listFiles.filter(_.isFile).map(_.getAbsoluteFile)
  }

  private val booksXsl = new File("src/test/xslt/libro.xsl")
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
  }

  private def outputFile(name: String):File = {
    return new File("target/xslt-plugin/" + name)
  }
}
