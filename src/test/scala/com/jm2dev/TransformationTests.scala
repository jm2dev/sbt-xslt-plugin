package com.jm2dev

import org.scalatest.{GivenWhenThen, Spec}
import org.scalatest.matchers.ShouldMatchers
import javax.xml.transform.stream.StreamSource
import java.io.File
import net.sf.saxon.s9api._

class TransformationTests extends Spec
  with GivenWhenThen
  with ShouldMatchers
{
  describe("XSLT transformation with Saxon HE")
  {
    it("should transform xml file using xslt file")
    {
      given("a valid xml and xsl files")
      val booksXml = new File("src/test/resources/libros.xml")
      val booksXsl = new File("src/test/resources/libro.xsl")
      val outputXml = new File("target/xslt-plugin/libros.xml")

      when("xml file is transformed using xslt file")
      val proc:Processor = new Processor(false);
      val comp:XsltCompiler = proc.newXsltCompiler();
      val exp:XsltExecutable = comp.compile(new StreamSource(booksXsl));
      val source:XdmNode = proc.newDocumentBuilder().build(new StreamSource(booksXml));
      var out:Serializer = proc.newSerializer(outputXml);
      out.setOutputProperty(Serializer.Property.METHOD, "xml");
      out.setOutputProperty(Serializer.Property.INDENT, "yes");
      var trans:XsltTransformer = exp.load();
      trans.setInitialContextNode(source);
      trans.setDestination(out);
      trans.transform();

      then("a valid xml file is obtained")
      val actualOutput = new File("target/xslt-plugin/libros.xml")
      actualOutput.canRead should be(true)
      actualOutput.getTotalSpace should be > (0L)
    }
  }

}
