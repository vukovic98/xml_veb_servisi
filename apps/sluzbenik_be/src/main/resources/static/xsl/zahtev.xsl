<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">

	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="zahtev-page">
					<fo:region-body margin-top="0.75in"
						margin-bottom="0.75in" margin-left="80pt" margin-right="80pt" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="zahtev-page">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-family="Times New Roman" width="60%"
						margin-top="10pt" text-align="center" border="none"  text-decoration="underline">
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podaci_o_organu/naziv"></xsl:value-of>
						,
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podaci_o_organu/sediste"></xsl:value-of>
					</fo:block>
					<fo:block margin-top="0pt" margin-bottom="0pt"
						text-align="center" font-size="12pt" font-family="Times New Roman">
						назив и седиште
						органа коме се захтев упућује
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="14pt"
						font-weight="bold" text-align="center" margin-top="48pt">
						З А Х Т Е В
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="14pt"
						font-weight="bold" text-align="center">
						за приступ информацији од јавног
						значаја
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="12pt"
						text-align="justify" text-indent="4em" margin-top="28pt">
						На основу члана
						15. ст. 1. Закона о слободном приступу информацијама од јавног
						значаја („Службени гласник РС“, бр. 120/04, 54/07, 104/09 и
						36/10), од горе наведеног органа захтевам:*
					</fo:block>
					
						<xsl:for-each select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev">
							<fo:block text-indent="4em" font-size="12pt" font-family="Times New Roman" margin-top="12pt">
								<xsl:choose>
									<xsl:when
										test="@otkaceno = 'true'">
										<fo:inline font-size="12pt" font-family="MS Gothic">
											☑
										</fo:inline>
									</xsl:when>
									<xsl:otherwise>
										<fo:inline font-size="12pt" font-family="MS Gothic">
											☐
										</fo:inline>
									</xsl:otherwise>
								</xsl:choose>
								<xsl:value-of select="@opis_zahteva"/>
							</fo:block>
						</xsl:for-each>
						<xsl:for-each select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin">
							<fo:block text-indent="8em" font-size="12pt" font-family="Times New Roman">
								<xsl:choose>
									<xsl:when
										test="@otkaceno = 'true'">
										<fo:inline font-size="12pt" font-family="MS Gothic">
											☑
										</fo:inline>
									</xsl:when>
									<xsl:otherwise>
										<fo:inline font-size="12pt" font-family="MS Gothic">
											☐
										</fo:inline>
									</xsl:otherwise>
								</xsl:choose>
								<xsl:value-of select="@opis_nacina"/>
								<xsl:if test="@id = 'drugi'">
									<xsl:if test="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin = ''">
									  	<fo:inline font-size="12pt" font-family="MS Gothic">
											________________________________
										</fo:inline>
									</xsl:if>
									<xsl:if test="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin != ''">
									  	<fo:inline font-size="12pt" font-family="MS Gothic" text-decoration="underline">
											<xsl:value-of select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin"/>
										</fo:inline>
									</xsl:if>
								</xsl:if>
							</fo:block>
						</xsl:for-each>
					<fo:block font-family="Times New Roman" font-size="12pt"
						text-align="justify" text-indent="4em" margin-top="12pt">
						Овај захтев се
						односи на следеће
						информације:
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="12pt"
						text-align="justify" text-indent="4em" text-decoration="underline" margin-top="10pt">
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/sadrzaj/opis_trazene_informacije"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="9pt"
						text-align="justify">
						(навести што прецизнији опис информације која се тражи
						као и друге податке
						који олакшавају проналажење тражене
						информације)
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right" margin-top="27pt"  text-decoration="underline">
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/ime_i_prezime"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right">
						Тражилац информације/Име и презиме
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right" margin-top="10pt"  text-decoration="underline">
						<xsl:value-of
							select="concat(/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/ulica, ' ')"></xsl:value-of>
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/broj"></xsl:value-of>
						,
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/mesto"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right">
						адреса
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right" margin-top="10pt"  text-decoration="underline">
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/kontakt"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="right">
						други подаци за контакт
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="left" margin-top="-50pt"  text-decoration="underline">
						У
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/mesto_i_datum/mesto"></xsl:value-of>
						,
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="10pt"
						text-align="left" margin-top="20pt"  text-decoration="underline">
						дана
						<xsl:value-of
							select="/zahtev_za_pristup_informacijama/podnozje/mesto_i_datum/datum_zahteva" />. године
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="9pt"
						text-align="justify" margin-top="40pt">
						__________________________________________
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="9pt"
						text-align="justify">
						* У кућици означити која законска права на приступ
						информацијама желите да остварите.
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="9pt"
						text-align="justify">
						** У кућици означити начин достављања копије
						докумената.
					</fo:block>
					<fo:block font-family="Times New Roman" font-size="9pt"
						text-align="justify">
						*** Када захтевате други начин достављања обавезно
						уписати који начин достављања захтевате.
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>