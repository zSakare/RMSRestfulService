<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:strip-space elements="*"/>

	<xsl:template match="/html/head">
	</xsl:template>

	<xsl:template match="/html/body/div/table">
	</xsl:template>

	<xsl:template match="/html/body/div/div/div/table/thead">
	</xsl:template>

	<xsl:template match="/html/body/div/div/div/table/tbody">
		<Root>
		<xsl:for-each select="tr">
			<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
			<Entry>
				<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				<PostCode><xsl:value-of select="th"/></PostCode>
				<xsl:for-each select="td">
					<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
					<xsl:variable name="counter" select="position()"/>
					<xsl:if test="$counter=1">
						<Total><xsl:value-of select="."/></Total>
					</xsl:if>
					<xsl:if test="$counter=2">
						<PassengerVehicles><xsl:value-of select="."/></PassengerVehicles>
					</xsl:if>
					<xsl:if test="$counter=3">
						<OffRoadVehicles><xsl:value-of select="."/></OffRoadVehicles>
					</xsl:if>
					<xsl:if test="$counter=4">
						<PeopleMovers><xsl:value-of select="."/></PeopleMovers>
					</xsl:if>
					<xsl:if test="$counter=5">
						<SmallBuses><xsl:value-of select="."/></SmallBuses>
					</xsl:if>
					<xsl:if test="$counter=6">
						<Buses><xsl:value-of select="."/></Buses>
					</xsl:if>
					<xsl:if test="$counter=7">
						<MobileHomes><xsl:value-of select="."/></MobileHomes>
					</xsl:if>
					<xsl:if test="$counter=8">
						<MotorCycles><xsl:value-of select="."/></MotorCycles>
					</xsl:if>
					<xsl:if test="$counter=9">
						<Scooters><xsl:value-of select="."/></Scooters>
					</xsl:if>
					<xsl:if test="$counter=10">
						<LightTrucks><xsl:value-of select="."/></LightTrucks>
					</xsl:if>
					<xsl:if test="$counter=11">
						<HeavyTrucks><xsl:value-of select="."/></HeavyTrucks>
					</xsl:if>
					<xsl:if test="$counter=12">
						<PrimeMovers><xsl:value-of select="."/></PrimeMovers>
					</xsl:if>
					<xsl:if test="$counter=13">
						<LightPlants><xsl:value-of select="."/></LightPlants>
					</xsl:if>
					<xsl:if test="$counter=14">
						<HeavyPlants><xsl:value-of select="."/></HeavyPlants>
					</xsl:if>
					<xsl:if test="$counter=15">
						<SmallTrailers><xsl:value-of select="."/></SmallTrailers>
					</xsl:if>
					<xsl:if test="$counter=16">
						<Trailers><xsl:value-of select="."/></Trailers>
					</xsl:if>
				</xsl:for-each>
			<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
			</Entry>
		</xsl:for-each>
	<xsl:text>&#xa;</xsl:text>
	</Root>
	</xsl:template>
</xsl:stylesheet>