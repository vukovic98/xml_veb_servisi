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
                #d1{
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
                #p1{
                margin-bottom: 0px;
                font-family: "Times New Roman";
                }
                #p2 {
                float: left;
                font-family: "Times New Roman";
                }
                #s1 {
                float: right;
                font-family: "Times New Roman";
                }
                #p3{
                margin-top: 10pt;
                font-family: "Times New Roman";
                
                }
                #p4{
                margin-top: 10pt;
                font-family: "Times New Roman";
                font-size: 15pt;
                text-align: center;
                margin-bottom: 10pt;
                }
                #p5,#p7,#p8{
                margin-top: 10pt;
                font-family: "Times New Roman";
                text-align: left;
                }
                #p6{
                margin-top: 10pt;
                margin-bottom: 10pt;
                font-family: "Times New Roman";
                text-align: center;
                font-size: 12pt;
                }
                #p9{
                margin-top: 10pt;
                margin-bottom: 0pt;
                font-family: "Times New Roman";
                text-align: right;
                }
                #p10{
                margin-top: 0pt;
                font-family: "Times New Roman";
                text-align: right;
                
                }
                </style>
                
            </head>
       	
       	<body>
       	<div id="d1">
       		<p id="p1"><xsl:value-of select="/resenje/osnovni_podaci/naslov"></xsl:value-of></p>
       		<span id="p2">Број: <strong><xsl:value-of select="/resenje/@broj"></xsl:value-of></strong></span>
       		<span id="s1">Датум:<strong><xsl:value-of select="/resenje/osnovni_podaci/datum"></xsl:value-of></strong> године.</span>
       	<br></br>
       	
       		<p id="p3"><xsl:value-of select="/resenje/sadrzaj/uvod/organ"></xsl:value-of>, у поступку по
жалби коју је изјавио <xsl:value-of select="/resenje/sadrzaj/uvod/podnosilac"></xsl:value-of> против <xsl:value-of select="/resenje/sadrzaj/uvod/ustanova/naziv"></xsl:value-of>
				  због недобијања тражених информација по његовом захтеву за приступ информацијама од јавног
значаја поднетом <xsl:value-of select="/resenje/sadrzaj/uvod/datum_zahteva"></xsl:value-of> године, на основу члана 35. став 1. тачка 5. Закона о слободном
приступу информацијама од јавног значаја („Службени гласник РС” бр. 120/04, 54/07, 104/09 и
36/10), а у вези са чланом 4. тачка 22. Закона о заштити података о личности („Службени гласник
РС” бр. 87/18), као и члана 23. и члана 24. став 1. Закона о слободном приступу информацијама од
јавног значаја и члана 170. став 1. тачка 1. Закона о општем управном поступку („Службени гласник
РС”, бр. 18/16 и 95/18), доноси</p>
       	
       	<p id="p4">Р Е Ш Е Њ Е</p>
       	<p id="p5"><xsl:value-of select="/resenje/sadrzaj/doneto_resenje/tekst_resenja"></xsl:value-of></p>
       	<p id="p6">O б р а з л о ж е њ е</p>
       	<p id="p7"><xsl:value-of select="/resenje/sadrzaj/obrazlozenje/tekst_obrazlozenja"></xsl:value-of></p>
       	<p id="p8">Против овог решења није допуштена жалба већ се, у складу са Законом о управним
споровима, може покренути управни спор тужбом <xsl:value-of select="/resenje/sadrzaj/obrazlozenje/sud"></xsl:value-of>, у року од 30 дана од
дана пријема решења. Такса на тужбу износи <xsl:value-of select="/resenje/sadrzaj/obrazlozenje/taksa"></xsl:value-of>.</p>

       	<p id="p9">ПОВЕРЕНИК</p>
       	<p id="p10"><xsl:value-of select="/resenje/poverenik"></xsl:value-of></p>

       	</div>

       	
       	</body>
       	
       	</html>
    </xsl:template>
</xsl:stylesheet>