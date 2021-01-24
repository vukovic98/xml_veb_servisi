<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
	<xsl:template match="/">
		<html>
			<head>
				<style>
					body {
					background-color: grey;
					}
					.c1 {
					padding-left: 50pt;
					margin: 0 auto;
					margin-top: 20pt;
					margin-bottom: 20pt;
					background-color: white;
					padding-right: 50pt;
					padding-top: 60pt;
					padding-bottom: 60pt;
					box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
					width: 40%;
					position: sticky;
					top: 25pt;
					}

					.c6 {
					font-family: "Times New Roman";
					font-size: 14pt;
					text-align: center;
					margin-top:48pt;
					}

					.c8 {
					font-family: "Times New Roman";
					margin-top: 10pt;
					text-align:
					center;
					}

					.c9 {
					text-align: center;
					font-size: 12pt;
					font-family:
					"Times New Roman";
					margin-top:-10pt;
					}

					.c20 {
					font-family:"Times New Roman";
					font-size:12pt;
					text-align:justify;
					text-indent:4em;
					margin-top:28pt;
					}

					.c24 {
					text-align:right;
					font-size: 10pt;
					font-family: "Times New Roman";
					margin-top:-10pt;
					}

					.c25 {
					font-family:"Times New Roman";
					font-size:10pt;
					text-align:right;
					}

					.c27 {
					font-family:"Times New Roman";
					font-size:10pt;
					text-align:left;
					margin-top:-50pt;
					}

					.c29 {
					font-family:"Times New Roman";
					font-size:10pt;
					text-align:left;
					margin-top:20pt;
					}

					.c37 {
					font-family: "Times New Roman";
					font-size: 14pt;
					text-align: center;
					margin-top: -10pt;
					}
					.c38 {
					font-family: "Times New Roman";
					font-size:9pt;
					margin-top: -10pt;
					text-align:justify;
					}

					.c39
					{
					font-family: "Times New Roman";
					font-size: 9pt;
					margin-top: 40pt;
					text-align:justify;
					}

					.c40{
					margin-top:-10pt;
					font-family:"Times New Roman";
					font-size:9pt;
					text-align:justify;
					}

					.c41{
					margin-top:-10pt;
					font-family:"Times New Roman";
					font-size:12pt;
					text-align:justify;
					text-indent:4em;
					}

					.c42{
					font-family:"Times New Roman";
					font-size:12pt;
					text-align:justify;
					text-indent:4em;
					}

					.c43{
					font-family:"Times New Roman";
					font-size:12pt;
					text-indent:4em;
					margin-top:-10pt;
					}

					.c44{
					font-family:"Times New Roman";
					font-size:12pt;
					text-indent:8em;
					margin-top:-10pt;
					}

					.c45{
					font-family:"Times New Roman";
					font-size:12pt;
					padding-left: 60px;
					margin-top:12pt;
					}

					.c46{
					font-family:"Times New Roman";
					font-size:10pt;
					text-align:right;
					margin-top:27pt;
					}
				</style>
			</head>
			<body>
				<div class="c1">
					<p class="c8">
						<u><xsl:value-of select="/zahtev_za_pristup_informacijama/podaci_o_organu/naziv"></xsl:value-of>
  						,
  						<xsl:value-of  select="/zahtev_za_pristup_informacijama/podaci_o_organu/sediste"></xsl:value-of>
						</u>
					</p>
					<p class="c9">назив и седиште органа коме се захтев упућује</p>
					<p class="c6">
						<strong>З А Х Т Е В</strong>
					</p>
					<p class="c37">
						<strong>за приступ информацији од јавног значаја</strong>
					</p>
					<p class="c20">
						На основу члана 15. ст. 1. Закона о слободном приступу
						информацијама од јавног значаја („Службени гласник РС“, бр.
						120/04, 54/07, 104/09 и 36/10), од горе наведеног органа
						захтевам:*
					</p>
					<p class="c45">
					    <xsl:for-each select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev">
					            <xsl:choose>
					                <xsl:when
					                    test="@otkaceno = 'true'">
					                    <span font-size="12pt" font-family="MS Gothic">
					                        ☑
					                    </span>
					                </xsl:when>
					                <xsl:otherwise>
					                    <span font-size="12pt" font-family="MS Gothic">
					                        ☐
					                    </span>
					                </xsl:otherwise>
					            </xsl:choose>
					            <xsl:value-of select="@opis_zahteva"/><br></br>
					    </xsl:for-each>
					   </p>
					   <p style="padding-left: 130px;">
					    <xsl:for-each select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin">
					            <xsl:choose>
					                <xsl:when
					                    test="@otkaceno = 'true'">
					                    <span font-size="12pt" font-family="MS Gothic">
					                        ☑
					                    </span>
					                </xsl:when>
					                <xsl:otherwise>
					                    <span font-size="12pt" font-family="MS Gothic">
					                        ☐
					                    </span>
					                </xsl:otherwise>
					            </xsl:choose>
					            <xsl:value-of select="@opis_nacina"/>
					            <xsl:if test="@id = 'drugi'">
					                <xsl:if test="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin = ''">
					                    <span font-size="12pt" font-family="MS Gothic">
					                        ________________________________
					                    </span>
					                </xsl:if>
					                <xsl:if test="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin != ''">
					                <u><span font-size="12pt" font-family="MS Gothic">
					                        <xsl:value-of select="/zahtev_za_pristup_informacijama/sadrzaj/zahtevi/zahtev[4]/nacini_dostave/nacin[4]/drugi_nacin"/>
					                </span></u>
					                </xsl:if>
					            </xsl:if>
					        	<br></br>
					    </xsl:for-each>
					</p>
					<p class="c42">
						Овај захтев се
						односи на следеће информације:
					</p>
					<p class="c41">
						<u><xsl:value-of
						    select="/zahtev_za_pristup_informacijama/sadrzaj/opis_trazene_informacije"></xsl:value-of></u>
					</p>
					<p class="c40">(навести што прецизнији опис
						информације која се
						тражи
						као
						и друге податке који олакшавају
						проналажење тражене
						информације)</p>
					<p class="c46">
						<u><xsl:value-of
						    select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/ime_i_prezime"></xsl:value-of></u>
					</p>
					<p class="c24">Тражилац информације/Име и презиме</p>
					<p class="c25">
					    <u><xsl:value-of
					        select="concat(/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/ulica, ' ')"></xsl:value-of>
					    <xsl:value-of
					        select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/broj"></xsl:value-of>
					    ,
					    <xsl:value-of
					        select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/adresa/mesto"></xsl:value-of></u>
					</p>
					<p class="c24">адресa</p>
					<p class="c25">
						<u><xsl:value-of
						    select="/zahtev_za_pristup_informacijama/podnozje/informacije_o_traziocu/kontakt"></xsl:value-of></u>
					</p>
					<p class="c24">други подаци за контакт</p>
					<p class="c27">
						У
						<u><xsl:value-of
						    select="/zahtev_za_pristup_informacijama/podnozje/mesto_i_datum/mesto"></xsl:value-of></u>
						,

					</p>
					<p class="c29">
						дана
							<u><xsl:value-of
							    select="/zahtev_za_pristup_informacijama/podnozje/mesto_i_datum/datum_zahteva" /></u>
						. године
					</p>
					<p class="c39">__________________________________________</p>
					<p class="c38">* У кућици означити која законска права на приступ
						информацијама желите да остварите.</p>
					<p class="c38">** У кућици означити начин достављања копије
						докумената.</p>
					<p class="c38">*** Када захтевате други начин достављања обавезно
						уписати који начин достављања захтевате.</p>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>