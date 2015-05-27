<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:strip-space elements="*"/>
	<xsl:param name="sort" select="'ascending'"/>
	<xsl:template match="/Root/Entry[1]">
		<Root>
		<xsl:for-each select="*">
			<xsl:sort select="local-name()" order="{$sort}"/>
			<xsl:if test="((local-name()!='FuelType') and (local-name()!='Total'))">
				<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
				<Type><xsl:value-of select="local-name()"/></Type>
			</xsl:if>
		</xsl:for-each>
		<xsl:text>&#xa;</xsl:text>
		</Root>
	</xsl:template>

	<xsl:template match="/Root/Entry[position() > 1]">
	</xsl:template>
</xsl:stylesheet>