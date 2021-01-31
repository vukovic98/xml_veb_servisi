<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                    master-name="resenje-page">
                    <fo:region-body margin-top="0.75in"
                        margin-bottom="0.75in" margin-left="80pt" margin-right="80pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="resenje-page">
                <fo:flow flow-name="xsl-region-body">
            
            	<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="left" border="none">
						<xsl:value-of
							select="/resenje/osnovni_podaci/naslov"></xsl:value-of>
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						text-align-last="justify" border="none">
						Број: 
						<fo:inline  font-family="Times New Roman" font-size="10pt"
                            font-weight="bold" text-align="center">
                            <xsl:value-of
							select="/resenje/@broj"></xsl:value-of>
                        </fo:inline>
                        <fo:leader leader-pattern="space" />
                        <fo:inline font-family="Times New Roman" width="60%" text-align="right" border="none">
						Датум: 
						<fo:inline  font-family="Times New Roman" font-size="10pt"
                            font-weight="bold" text-align="center">
                            <xsl:value-of
							select="/resenje/osnovni_podaci/datum"></xsl:value-of>
                        </fo:inline>
                         године.
				</fo:inline>
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="left" border="none">
							<xsl:value-of select="/resenje/sadrzaj/uvod/organ"></xsl:value-of>, у поступку по
жалби коју је изјавио 
				<xsl:value-of select="/resenje/sadrzaj/uvod/podnosilac"></xsl:value-of>
				 против <xsl:value-of select="/resenje/sadrzaj/uvod/ustanova/naziv"></xsl:value-of>
				  због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног
значаја поднетом <xsl:value-of select="/resenje/sadrzaj/uvod/datum_zahteva"></xsl:value-of> године, на основу члана 35. став 1. тачка 5. Закона о слободном
приступу информацијама од јавног значаја („Службени гласник РС” бр. 120/04, 54/07, 104/09 и
36/10), а у вези са чланом 4. тачка 22. Закона о заштити података о личности („Службени гласник
РС” бр. 87/18), као и члана 23. и члана 24. став 1. Закона о слободном приступу информацијама од
јавног значаја и члана 170. став 1. тачка 1. Закона о општем управном поступку („Службени гласник
РС”, бр. 18/16 и 95/18), доноси

				</fo:block>
				<fo:block font-family="Times New Roman" width="60%" font-size="15pt" margin-top="10pt" margin-bottom="10pt" text-align="center" border="none">
						Р Е Ш Е Њ Е
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="left" border="none">
						<xsl:value-of select="/resenje/sadrzaj/doneto_resenje/tekst_resenja"></xsl:value-of>
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%" font-size="12pt" margin-top="10pt" margin-bottom="10pt" text-align="center" border="none">
						O б р а з л о ж е њ е
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="left" border="none">
						<xsl:value-of select="/resenje/sadrzaj/obrazlozenje/tekst_obrazlozenja"></xsl:value-of>
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="left" border="none">
						<xsl:text xml:space="preserve">		</xsl:text>Против овог решења није допуштена жалба већ се, у складу са Законом о управним
споровима, може покренути управни спор тужбом <xsl:value-of select="/resenje/sadrzaj/obrazlozenje/sud"></xsl:value-of>, у року од 30 дана од
дана пријема решења. Такса на тужбу износи <xsl:value-of select="/resenje/sadrzaj/obrazlozenje/taksa"></xsl:value-of>.
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="right" border="none">
						ПОВЕРЕНИК
				</fo:block>
				<fo:block font-family="Times New Roman" width="60%"
						 text-align="right" border="none">
						<xsl:value-of select="/resenje/poverenik"></xsl:value-of>
				</fo:block>
            </fo:flow>
            </fo:page-sequence>
            
            </fo:root>
            </xsl:template>
            </xsl:stylesheet>