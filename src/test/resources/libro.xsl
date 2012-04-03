<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output indent="yes" encoding="utf-8" method="xml"/>
  <xsl:template name="main" match="/">
    <book>
      <id>
        <xsl:value-of select="/library/book/@id"/>
      </id>
      <title>
        <xsl:value-of select="/library/book/title"/>
      </title>
    </book>
  </xsl:template>
</xsl:stylesheet>
