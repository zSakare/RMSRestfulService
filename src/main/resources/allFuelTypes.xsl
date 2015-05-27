<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:strip-space elements="*"/>
	<xsl:param name="sort" select="'ascending'"/>
	<xsl:template match="/Root">
		<Root>
			<xsl:for-each select="Entry">
				<xsl:sort select="FuelType" order="{$sort}"/>
				<xsl:if test="FuelType != 'TOTAL'">
					<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
					<FuelType><xsl:value-of select="FuelType"/></FuelType>
				</xsl:if>
			</xsl:for-each>
		<xsl:text>&#xa;</xsl:text>
		</Root>
	</xsl:template>

	<xsl:template match="/Root/Entry[position() > 1]">
	</xsl:template>
</xsl:stylesheet>