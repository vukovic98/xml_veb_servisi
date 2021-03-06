<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
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
                    
                    .c2 {
                    font-family: "Times New Roman";
                    font-size: 10pt;
                    font-weight: bold;
                    text-align: center;
                    margin-top: 28pt;
                    }
                    .underline {
                    border-after-style: solid;
                    }
                    
                    .c3 {
                    font-family: "Times New Roman";
                    font-size: 10pt;
                    text-align: center;
                    }
                    
                    .c4 {
                    font-family: "Times New Roman";
                    font-size: 12pt;
                    text-align: left;
                    margin-top: 28pt;
                    }
                    .c5 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: left;
                    margin-top: -10pt; 
                    }
                    
                    .c6 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: justify;
                    }
                    
                    
                    .c7 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: center;
                    margin-top: 12pt;
                    }
                    
                    .c8 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: center;
                    margin-top: -7pt;
                    }
                    
                    .c9 {
                    font-family: "Times New Roman";
                    width: 100%;
                    margin-top: 11pt;
                    text-align: center;
                    border-bottom-style: dotted;
                    border-bottom-width: thin;
                    }
                    
                    .c10 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: center;
                    margin-top: 15pt;
                    }
                    
                    .c11 {
                    font-size: 11pt;
                    font-family: "Times New Roman";
                    margin-top: 2pt;
                    text-align: center;
                    }
                    
                    .c12 {
                    font-size: 11pt;
                    font-family: "Times New Roman";
                    text-align: center;
                    margin-top: -7pt;
                    }
                    
                    .c13 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: justify;
                    margin-top: 10pt;
                    }
                    
                    .c14 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: justify;
                    margin-top: 18pt;
                    }
                    
                    .c15 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: right;
                    margin-top: 10pt;
                    margin-bottom: -14pt;
                    }
                    .c16 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: right;
                    margin-top: -4pt;
                    }
                    
                    .dots {
                    font-size: 10pt;
                    text-align: right;
                    font-family: "Times New Roman";
                    margin-bottom: -5pt;
                    }
                    .c17 {
                    font-family: "Times New Roman";
                    font-size: 11pt;
                    text-align: left;
                    margin-top: 10pt;
                    }
                    
                    .dotted {
                    border-bottom-style: dotted;
                    border-bottom-width: thin;
                    }
                </style>
            </head>
            <body>
                <div class="c1">
                    <p class="c2">
                        <strong>ЖАЛБА  ПРОТИВ  ОДЛУКЕ ОРГАНА  ВЛАСТИ КОЈОМ ЈЕ</strong>
                    </p>
                    <p class="c3">
                        <strong>  <u> ОДБИЈЕН ИЛИ ОДБАЧЕН ЗАХТЕВ </u> ЗА ПРИСТУП ИНФОРМАЦИЈИ</strong>
                    </p>
                    <p class="c4">
                        <b>Поверенику за информације од јавног значаја и заштиту података о личности</b>
                    </p>
                    <p class="c5">
                        Адреса за пошту: Београд, Булевар краља Александрa бр. 15
                    </p>
                    <p class="c7">
                        <strong>Ж А Л Б А </strong>
                    </p>
                    <p class="c9">
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_ime"></xsl:value-of>
                        
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_prezime"></xsl:value-of>,
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_naziv_zalbe"></xsl:value-of>,
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_adresa"></xsl:value-of>,
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_zaliocu/zalioc_sediste"></xsl:value-of>
                    </p>
                    <p class="c8">
                        (Име, презиме, односно назив, адреса и седиште жалиоца)
                    </p>
                    <p class="c10">
                        против решења-закључка  
                    </p>
                    <p>
                        <xsl:value-of select="/zalba_na_odluku/osnovni_podaci/podaci_o_organu"></xsl:value-of>
                    </p>
                    <p class="c12">
                        (назив органа који је донео одлуку)
                    </p>
                    <p class="c13">
                        Број <span class="dotted"> <xsl:value-of select="/zalba_na_odluku/sadrzaj/broj_zalbe"></xsl:value-of></span>
                        од <span class="dotted"><xsl:value-of select="zalba_na_odluku/sadrzaj/godina_zalbe"></xsl:value-of></span> године. 
                    </p>
                    <p class="c9">
                        Наведеном одлуком органа власти (решењем, закључком, обавештењем у писаној форми
                        са елементима одлуке) , супротно закону, одбијен-одбачен је мој захтев који сам поднео/ла-
                        упутио/ла дана <span calss="dotted"><xsl:value-of select="zalba_na_odluku/sadrzaj/datum_odbijenog_zahteva"></xsl:value-of></span> године и тако ми ускраћено-онемогућено остваривање уставног и 
                        законског права на слободан приступ информацијама од јавног значаја. Oдлуку побијам у
                        целости, односно у делу којим <span calss="dotted"><xsl:value-of select="zalba_na_odluku/sadrzaj/odluka_organa_vlasti"></xsl:value-of></span>
                        јер није заснована на Закону о слободном приступу информацијама од јавног значаја.
                    </p>
                    <p class="c12">
                        На основу изнетих разлога, предлажем да Повереник уважи моју жалбу,  поништи
                        одлука првостепеног органа и омогући ми приступ траженој/им  информацији/ма.
                    </p>
                    <p class="c14">
                        Жалбу подносим благовремено, у законском року утврђеном у члану 22. ст. 1. Закона о
                        слободном приступу информацијама од јавног значаја
                    </p>  
                    <p class="c15">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_ime"></xsl:value-of>
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_prezime"></xsl:value-of>
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">
                        подносилац жалбе / име и презиме
                    </p>
                    <p class="c15">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/zalioc_adresa"></xsl:value-of>
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">адреса</p>
                    <p class="c15">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/drugi_podaci_za_kontakt"></xsl:value-of> 
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">други подаци за контакт</p>
                    <p class="c15">
                        <xsl:value-of select="/zalba_na_odluku/podnozje/podaci_o_zaliocu/potpis_zalioca"></xsl:value-of>
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">потпис</p>
                    <p class="c17">
                        У <span class="dotted"><xsl:value-of select="/zalba_na_odluku/podnozje/mesto_zakljucka_zalbe"/></span>, 
                        дана <span class="dotted"><xsl:value-of select="/zalba_na_odluku/podnozje/datum_zakljucka_zalbe"/></span> године.
                    </p>
                    <p class="c18">
                        <b>Напомена: </b>
                    </p>
                    <p class="c19">
                        •   У жалби се мора навести одлука која се побија (решење, закључак, обавештење), назив
                            органа који је одлуку донео, као и број и датум одлуке. Довољно је да жалилац наведе у
                            жалби у ком погледу је незадовољан одлуком, с тим да жалбу не мора посебно образложити. 
                            Ако жалбу изјављује на овом обрасцу, додатно образложење може  посебно приложити.
                    </p>
                    <p class="c20">
                        •   Уз жалбу обавезно приложити копију поднетог захтева и доказ о његовој предаји-упућивању органу
                            као и копију одлуке органа која се оспорава жалбом.
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>