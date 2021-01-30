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
                        <strong>ЖАЛБА КАДА ОРГАН ВЛАСТИ <u>НИЈЕ ПОСТУПИО/ није поступио у целости/ ПО ЗАХТЕВУ</u></strong>
                    </p>
                    <p class="c3">
                        <strong> ТРАЖИОЦА У ЗАКОНСКОМ  РОКУ  (ЋУТАЊЕ УПРАВЕ)</strong>
                    </p>
                    <p class="c4">
                        <strong><xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/naziv_primaoca"/></strong>
                    </p>
                    <p class="c5">
                         Адреса за пошту:<span class="dotted">
                             <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/mesto"></xsl:value-of>,
                             <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/ulica"></xsl:value-of> бр.
                             <xsl:value-of select="/zalba_cutanje/zaglavlje/primalac_zalbe/adresa/broj"></xsl:value-of>
                         </span>
                    </p>
                    <p class="c6">
                        У складу са чланом 22. Закона о слободном приступу информацијама од јавног значаја подносим:
                    </p>
                    <p class="c7">
                        <strong> Ж А Л Б У</strong>
                    </p>
                    <p class="c8">
                        против
                    </p>
                    <p class="c9">
                        <xsl:value-of select="/zalba_cutanje/sadrzaj/naziv_organa"></xsl:value-of>
                    </p>
                    <p class="c8">
                        (навести назив органа)
                    </p>
                    <p class="c10">
                        због тога што орган власти: 
                    </p>
                    <xsl:for-each select="/zalba_cutanje/sadrzaj/razlozi_zalbe/razlog">
                        <p class="c11">
                            <xsl:choose>
                                <xsl:when
                                    test="@id = 'r_1' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <p class="c12">
                                                <strong><u>није поступио</u> / није поступио у целости /  у законском року</strong>  
                                            </p>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:when
                                    test="@id = 'r_2' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <p class="c12">
                                                <strong>није поступио / <u>није поступио у целости</u> / у законском року</strong>  
                                            </p>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:when
                                    test="@id = 'r_3' ">
                                    <xsl:choose>
                                        <xsl:when test="@otkaceno = 'true'">
                                            <p class="c12">
                                                <strong> није поступио / није поступио у целости /<u>у законском року</u></strong>
                                            </p>
                                        </xsl:when>
                                    </xsl:choose>
                                </xsl:when>
                                <xsl:otherwise>
                                    <p class="c12">
                                        <strong> није поступио / није поступио у целости / у законском року</strong>
                                    </p>
                                </xsl:otherwise>
                            </xsl:choose>
                        </p>
                    </xsl:for-each>
                    <p class="c12">
                        (подвући  због чега се изјављује жалба)
                    </p>
                    <p class="c13">
                        по мом захтеву  за слободан приступ информацијама од јавног значаја који сам поднео  том органу  дана
                        <span class="dotted"> <xsl:value-of select="/zalba_cutanje/sadrzaj/datum_zahteva"></xsl:value-of></span>
                        године, а којим сам тражио/ла да ми се у складу са Законом о слободном приступу информацијама од јавног 
                        значаја омогући увид- копија документа који садржи информације  о /у вези са :
                    </p>
                    <p class="c9">
                        <xsl:value-of select="/zalba_cutanje/sadrzaj/podaci_o_zahtevu_i_informacijama"></xsl:value-of>
                    </p>
                    <p class="c12">
                        (навести податке о захтеву и информацији/ама)
                    </p>
                    <p class="c14">
                        На основу изнетог, предлажем да Повереник уважи моју жалбу и омогући ми приступ траженој/им  информацији/ма.
                    </p>
                    <p class="c6">
                        Као доказ , уз жалбу достављам копију захтева са доказом о предаји органу власти.
                    </p>
                    <p class="c6">
                        <strong>Напомена:</strong> Код жалбе због непоступању по захтеву у целости, треба приложити и добијени одговор органа власти.
                    </p>
                   
                    
                    <p class="c15">
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/ime_i_prezime"></xsl:value-of>
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">
                        подносилац жалбе / име и презиме
                    </p>
                    <p class="c15">
                        <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/potpis"></xsl:value-of>
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">потпис</p>
                    <p class="c15">
                        
                            <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/mesto"></xsl:value-of>,
                            <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/ulica"></xsl:value-of> бр.
                            <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/adresa/broj"></xsl:value-of> 
                        
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">адреса</p>
                    <p class="c15">
                          <xsl:value-of select="/zalba_cutanje/podnozje/podnosilac_zalbe/drugi_podaci_za_kontakt"></xsl:value-of> 
                    </p>
                    <p class="dots">.........................................................................</p>
                    <p class="c16">други подаци за контакт</p>
                    <p class="c17">
                        У <span class="dotted"><xsl:value-of select="/zalba_cutanje/podnozje/mesto_i_datum/mesto"/></span>, 
                        <span class="dotted"><xsl:value-of select="/zalba_cutanje/podnozje/mesto_i_datum/datum_zalbe"/></span> године.
                    </p>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>