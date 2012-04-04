<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version = "2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

 <xsl:template match = "messages">
   <html>
     <body><xsl:value-of select = "message"/></body>
   </html>
 </xsl:template>
</xsl:stylesheet>
