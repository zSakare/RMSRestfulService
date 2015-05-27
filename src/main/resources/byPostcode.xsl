<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:strip-space elements="*"/>
	<xsl:param name="sort" select="'ascending'"/>
	<xsl:param name="code"/>
	<xsl:template match="/Root">
		<Root>
			<xsl:for-each select="Entry">
				<xsl:sort select="PostCode" order="{$sort}"/>
				<xsl:if test="contains(PostCode, $code)">
					<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
					<Entry>
						<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
						<PostCode><xsl:value-of select="PostCode"/></PostCode>
						<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <Total><xsl:value-of select="Total"/></Total>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <PassengerVehicles><xsl:value-of select="PassengerVehicles"/></PassengerVehicles>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <OffRoadVehicles><xsl:value-of select="OffRoadVehicles"/></OffRoadVehicles>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <PeopleMovers><xsl:value-of select="PeopleMovers"/></PeopleMovers>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <SmallBuses><xsl:value-of select="SmallBuses"/></SmallBuses>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <Buses><xsl:value-of select="Buses"/></Buses>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <MobileHomes><xsl:value-of select="MobileHomes"/></MobileHomes>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <MotorCycles><xsl:value-of select="MotorCycles"/></MotorCycles>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <Scooters><xsl:value-of select="Scooters"/></Scooters>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <LightTrucks><xsl:value-of select="LightTrucks"/></LightTrucks>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <HeavyTrucks><xsl:value-of select="HeavyTrucks"/></HeavyTrucks>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <PrimeMovers><xsl:value-of select="PrimeMovers"/></PrimeMovers>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <LightPlants><xsl:value-of select="LightPlants"/></LightPlants>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <HeavyPlants><xsl:value-of select="HeavyPlants"/></HeavyPlants>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <SmallTrailers><xsl:value-of select="SmallTrailers"/></SmallTrailers>
				        <xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text><xsl:text>    </xsl:text>
				        <Trailers><xsl:value-of select="Trailers"/></Trailers>
					<xsl:text>&#xa;</xsl:text><xsl:text>    </xsl:text>
					</Entry>
				</xsl:if>
			</xsl:for-each>
		<xsl:text>&#xa;</xsl:text>
		</Root>
	</xsl:template>

	<xsl:template match="/Root/Entry[position() > 1]">
	</xsl:template>
</xsl:stylesheet>