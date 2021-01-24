<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                    master-name="zalba-cutanje-page">
                    <fo:region-body margin-top="0.75in"
                        margin-bottom="0.75in" margin-left="80pt" margin-right="80pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="zalba-cutanje-page">
                <fo:flow flow-name="xsl-region-body">
                   
                    <fo:block font-family="Times New Roman" font-size="10pt"
                        font-weight="bold" text-align="center" margin-top="48pt">
                        ЖАЛБА КАДА ОРГАН ВЛАСТИ
                        <fo:inline  font-family="Times New Roman" font-size="10pt"
                            font-weight="bold" text-align="center" margin-top="48pt" border-after-style="solid">
                            НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                        font-weight="bold" text-align="center">
                        ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt"
                        text-align="left" margin-top="28pt" font-weight="bold">
                        <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/naziv_primaoca"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt"
                        text-align="left">
                        Адреса за пошту:
                        <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/mesto"></xsl:value-of>,
                        <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/ulica"></xsl:value-of> бр.
                        <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/broj"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify">
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" margin-top="12pt" font-weight="bold">
                        Ж А Л Б У
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="center">
                        против
                    </fo:block>
                    <fo:block font-family="Times New Roman" width="60%"
                        margin-top="11pt" text-align="center" border-bottom-style="dotted">
                        <xsl:value-of select="/zalba_cutanje/sadrzaj/naziv_organa"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" font-weight="bold">
                        ( навести назив органа)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" margin-top="15pt">
                        због тога што орган власти: 
                    </fo:block>
                    <xsl:for-each select="/zalba_cutanje/sadrzaj/razlozi_zalbe/razlog">
                        <fo:block font-size="11pt" font-family="Times New Roman" margin-top="2pt" text-align="center">
                            <xsl:choose>
                                <xsl:when
                                    test="@id = 'r_1' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <fo:inline font-weight="bold" font-size="11pt" font-family="Times New Roman" text-align="center">
                                                <fo:inline font-weight="bold" font-size="11pt" font-family="Times New Roman" text-align="center" text-decoration="underline">није поступио</fo:inline> / није поступио у целости /  у законском року
                                            </fo:inline>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:when
                                    test="@id = 'r_2' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <fo:inline font-size="11pt" font-weight="bold" font-family="Times New Roman" text-align="center">
                                                није поступио / <fo:inline font-size="11pt" font-weight="bold" text-align="center" font-family="Times New Roman" text-decoration="underline">није поступио у целости</fo:inline> / у законском року
                                            </fo:inline>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:when
                                    test="@id = 'r_3' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <fo:inline font-size="11pt" font-weight="bold" font-family="Times New Roman" text-align="center">
                                                није поступио / није поступио у целости /<fo:inline font-weight="bold" text-align="center" font-size="11pt" font-family="Times New Roman" text-decoration="underline">у законском року</fo:inline>
                                            </fo:inline>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:otherwise>
                                <fo:inline font-size="11pt" font-weight="bold" font-family="Times New Roman" text-align="center">
                                                није поступио / није поступио у целости / у законском року
                                 </fo:inline>
                                </xsl:otherwise>
                            </xsl:choose>
                        </fo:block>
                    </xsl:for-each>
                    
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center">
                        (подвући  због чега се изјављује жалба)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" margin-top="10pt">
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана
                        <fo:inline>
                            <xsl:value-of select="/zalba_cutanje/sadrzaj/datum_zahteva"></xsl:value-of>
                        </fo:inline>
                        године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног 
                        значаја омогући увид- копија документа који садржи информације  о /у вези са :
                    </fo:block>
                    <fo:block font-family="Times New Roman" width="60%"
                        margin-top="11pt" text-align="center" border-bottom-style="dotted">
                        <xsl:value-of select="/zalba_cutanje/sadrzaj/podaci_o_zahtevu_i_informacijama"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center">
                        (навести податке о захтеву и информацији/ама)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" margin-top="28pt">
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" >
                        Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify">
                        <fo:inline font-family="Times New Roman" font-size="11pt"
                            text-align="justify" margin-top="28pt" font-weight="bold">Напомена:</fo:inline>
                       Код жалбе због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="10pt">
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/ime_i_prezime"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-bottom="3pt" >
                    	<fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        Подносилац жалбе / Име и презиме
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt" text-align="right" margin-top="3pt" >
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/potpis"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" >
                    <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        потпис
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="3pt">
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/mesto"></xsl:value-of>,
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/ulica"></xsl:value-of> бр.
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/broj"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" >
                    	<fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        адреса
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="3pt">
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/drugi_podaci_za_kontakt"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" >
                       <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        други подаци за контакт
                    </fo:block>
                    <fo:block text-align="left" margin-top="10pt" font-family="Times New Roman" font-size="11pt">
                        У <fo:inline  text-decoration="underline" ><xsl:value-of select="/zalba_cutanje/podnozje/mesto_i_datum/mesto"/></fo:inline>, 
                        <fo:inline text-decoration="underline"><xsl:value-of select="/zalba_cutanje/podnozje/mesto_i_datum/datum_zalbe"/></fo:inline> године.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>