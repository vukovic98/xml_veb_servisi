<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                    master-name="zalba-na-odluku-page">
                    <fo:region-body margin-top="0.75in"
                        margin-bottom="0.75in" margin-left="80pt" margin-right="80pt" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="zalba-na-odluku-page">
                <fo:flow flow-name="xsl-region-body">
                    
                    <fo:block font-family="Times New Roman" font-size="10pt"
                        font-weight="bold" text-align="center" margin-top="48pt">
                        ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ
                        <fo:inline  font-family="Times New Roman" font-size="10pt"
                            font-weight="bold" text-align="center" margin-top="48pt" border-after-style="solid">
                            ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ 
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt"
                        font-weight="bold" text-align="center">
                        ЗА ПРИСТУП ИНФОРМАЦИЈИ
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12pt"
                        text-align="left" margin-top="28pt" font-weight="bold">
                        Повереникy за информације од јавног значаја и заштиту података о личности
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="left">
                        Адреса за пошту:  Београд, Булевар краља Александрa бр. 15
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" margin-top="12pt" font-weight="bold">
                        Ж А Л Б А
                    </fo:block>
                    <fo:block font-family="Times New Roman" width="60%"
                        margin-top="11pt" text-align="center" border-bottom-style="dotted">
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_ime"> </xsl:value-of>
                        </fo:inline>
                        
                        
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_prezime"></xsl:value-of>
                        </fo:inline>
                        ,
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_naziv_zalbe"></xsl:value-of>
                        </fo:inline>
                        ,
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_adresa"></xsl:value-of> 
                        </fo:inline>
                        ,
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_sediste"></xsl:value-of>
                        </fo:inline>
                        
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" font-weight="bold">
                        (Име, презиме, односно назив, адреса и седиште жалиоца)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" margin-top="15pt">
                        против решења-закључка  
                    </fo:block>
                    <fo:block font-family="Times New Roman" width="60%"
                        margin-top="11pt" text-align="center" border-bottom-style="dotted">
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_organu"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="center" font-weight="bold">
                        (назив органа који је донео одлуку)
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" margin-top="10pt">
                        Број
                        <fo:inline>
                            <xsl:value-of select="/zalba_na_odluku/sadrzaj/broj_zalbe"></xsl:value-of>
                        </fo:inline>
                        од 
                        <fo:inline>
                            <xsl:value-of select="zalba_na_odluku/sadrzaj/godina_zalbe"></xsl:value-of>
                        </fo:inline>
                        године. 
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" margin-top="10pt">
                        Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми
                        са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-
                        упутио/ла дана 
                        <fo:inline>
                            <xsl:value-of select="zalba_na_odluku/sadrzaj/datum_odbijenog_zahteva"></xsl:value-of>
                        </fo:inline>
                        године и тако ми ускраћено-онемогућено остваривање уставног и 
                        законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у
                        целости, односно у делу којим
                        <fo:inline>
                            <xsl:value-of select="zalba_na_odluku/sadrzaj/odluka_organa_vlasti"></xsl:value-of>
                        </fo:inline>
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" margin-top="28pt">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" >
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о слободном приступу информацијама од јавног значаја.
                    </fo:block>               
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="10pt" margin-bottom="-7pt">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_ime"></xsl:value-of>
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_prezime"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-bottom="3pt" >
                        <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        Подносилац жалбе / Име и презиме
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="3pt"  margin-bottom="-7pt">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_adresa"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" >
                        <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        адреса
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="3pt"  margin-bottom="-7pt">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/drugi_podaci_za_kontakt"></xsl:value-of> 
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" >
                        <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        други подаци за контакт
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="10pt" text-align="right" margin-top="3pt"  margin-bottom="-7pt" >
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/potpis_zalioca"></xsl:value-of>
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt" text-align="right" margin-top="3pt" >
                        <fo:block font-size="11pt" text-align="right">.........................................................................</fo:block>
                        потпис
                    </fo:block>
                    <fo:block text-align="left" margin-top="10pt" font-family="Times New Roman" font-size="11pt">
                        У <fo:inline  text-decoration="underline" ><xsl:value-of select="/zalba_na_odluku/podnozje/mesto_zakljucka_zalbe"/></fo:inline>, дана 
                        <fo:inline text-decoration="underline"><xsl:value-of select="/zalba_na_odluku/podnozje/datum_zakljucka_zalbe"/></fo:inline> године.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify" font-weight="bold">
                        Напомена:
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify">
                        •   У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив
                            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у
                            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. 
                            Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити.
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="11pt"
                        text-align="justify">
                        •   Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу
                            као и копију одлуке органа која се оспорава жалбом.
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>