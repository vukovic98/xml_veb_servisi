<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples" version="2.0">

    <xsl:template match="/">
        <html>
            <head>
                <title>Извештај</title>
                <style>
					body {
					background-color: grey;
					}
					td{
					border: 1px solid black;
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
					margin-left:351px;
					margin-right:351px;
					position: sticky;
					top: 25pt;
					}

					.c6 {
					font-family: 'Times New Roman';
					font-size: 12pt;
					text-align: center;
					}

					.c7 {
					text-align: center;
					}

					.c8 {
					font-family: 'Times New Roman';
					width: 60%;
					margin-top: 10pt;
					text-align: center;
					border: none
					}

					.c9 {
					margin-top: 0pt;
					margin-bottom: 0pt;
					text-align: center;
					font-size: 11pt;
					font-family: 'Times New Roman';
					}

					.c17 {
					font-family: 'Times New Roman';
					}

					.c20 {
					margin-top: 0pt;
					margin-bottom: 0pt;
					text-align: justify;
					font-size:
					11pt;
					}

					.c21 {
					margin-top: 0pt;
					margin-bottom: 0pt;
					text-align: justify;
					font-size: 11pt;
					}

					.c22 {
					display: block;
					text-align: right;
					}

					.c23 {
					width: 200px;
					display: inline-block;
					margin-top: 20pt
					}

					.c24 {
					margin-top: -25pt;
					font-size: 11pt;
					font-family: 'Times New Roman';
					}

					.c25 {
					display: block;
					text-align: center;
					width: 100%;
					}

					.c26 {
					width:
					50%;
					display: inline-block;
					}

					.c27 {
					text-indent: 10pt;
					margin-top:
					-40pt;
					margin-bottom: 30pt;
					text-align: justify;
					font-size: 11pt;
					font-family: 'Times New Roman';
					}

					.c28 {
					width: 200px;
					margin-left:
					5px;
					display: inline-block;
					}

					.c29 {
					text-indent: 10pt;
					margin-top:
					-20pt;
					margin-bottom: 30pt;
					text-align: justify;
					font-size: 11pt;
					font-family: 'Times New Roman';
					}

					.c30 {
					width: 50%;
					margin-left: 2px;
					display: inline-block;
					}

					.c34 {
					font-family: 'Times New Roman';
					}

					.c35 {
					font-family: 'Times New Roman';
					font-size:
					9pt;
					}

					.c36 {
					font-family: 'Times New Roman';
					width: 80%;
					border: none
					}

					.c37 {
					font-family: 'Times New Roman';
					font-size: 11pt;
					text-align:
					center;
					margin-top: -10pt;
					}

					.c38 {
					font-family: 'Times New Roman';
					font-size: 9pt;
					margin-top:
					-10pt;
					}

					.c39 {
					font-size: 11pt;
					font-family: 'Times New Roman';
					text-align:
					justify;
					}
					.c40 {
					margin-top: -10pt;
					font-size:
					11pt;
					font-family: 'Times New Roman';
					}

					.c44 {
					margin-top: 0pt;
					font-size:
					11pt;
					font-family: 'Times New Roman';
					}

					.c41 {
					margin-top:
					-10pt;
					margin-left: -6pt;
					font-size: 11pt;
					font-family: 'Times New Roman';
					}

					.c42 {
					font-family: 'Times New Roman';
					margin-top: -10pt;
					}

					.c43 {
					font-family: 'Times New Roman';
					font-size: 11pt;
					text-align: center;
					margin-top: -10pt;
					}

					.c45 {
					font-family: 'Times New Roman';
					}
					.c46 {
					font-family: 'Times New Roman';
					}

					.c47 {
					font-family: 'Times New Roman';
					margin-top: -10pt;
					}

					tab1 {
					padding-left: 4em; }
					tab2 {
					padding-left: 8em; }
				</style>
            </head>
            <body>
            <div style="padding-left: 50pt;
					margin: 0 auto;
					margin-top: 20pt;
					margin-bottom: 20pt;
					background-color: white;
					padding-right: 50pt;
					padding-top: 60pt;
					padding-bottom: 60pt;
					box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
					margin-left:351px;
					margin-right:351px;
					position: sticky;
					top: 25pt;">
                <h1>Годишњи извештај портала еСлужбеник</h1>
                <p>Датум креирања извештаја: 
                    <b><xsl:value-of select="/izvestaj/@datum"/></b>
                </p>
                <p>Табела са бројевима издатих докумената:</p>
                <table style="border: 1px solid black; width:100%;">
                <tbody style="text-align: left;">
                        <tr>
                            <td>Укупан број захтева</td>
                            
                            <td>
                                <xsl:value-of select="/izvestaj/broj_zahteva"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Број одбијених захтева</td>
                            
                            <td>
                                <xsl:value-of select="/izvestaj/broj_odbijenih_zahteva"/>
                            </td>
                        </tr>
                        <tr>
                        	<xsl:variable name="odluka" select="/izvestaj/broj_zalbi_na_cutanje" />
	                        <xsl:variable name="cutanje" select="/izvestaj/broj_zalbi_na_odluku" />
                            <td>Укупан број жалби</td>
                            
                            <td>
                                <xsl:value-of select="$odluka + $cutanje"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Број предатих жалби на ћутање</td>
                            
                            <td>
                                <xsl:value-of select="/izvestaj/broj_zalbi_na_cutanje"/>
                            </td>
                        </tr>
                        <tr>
                            <td>Број предатих жалби на одлуку</td>
                            
                            <td>
                                <xsl:value-of select="/izvestaj/broj_zalbi_na_odluku"/>
                            </td>
                        </tr>
                        </tbody>
                </table>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
