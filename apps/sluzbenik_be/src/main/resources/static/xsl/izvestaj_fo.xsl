<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:b="http://www.ftn.uns.ac.rs/xpath/examples" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
    
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="izvestaj-page">
                    <fo:region-body margin="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            
            <fo:page-sequence master-reference="izvestaj-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-family="Times New Roman" font-size="24px" font-weight="bold" padding="10px">
                        Годишњи извештај портала еСлужбеник
                    </fo:block>
                    <fo:block font-family="Times New Roman" font-size="12px" padding="10px">
                        Датум креирања извештаја: 
                        <fo:inline text-decoration="underline">
                            <xsl:value-of select="/izvestaj/@datum"/>
                        </fo:inline>
                    </fo:block>
                    <fo:block font-family="Times New Roman" margin-top="30px" font-size="12px" font-weight="bold" padding="10px">
                        Табела са бројевима издатих докумената:
                    </fo:block>
                    <fo:block>
                        <fo:table font-family="serif" margin="5px auto 50px auto" border="1px">
                            <fo:table-column column-width="50%"/>
                            <fo:table-column column-width="50%"/>
                            <fo:table-body>
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            Укупан број захтева
                                        </fo:block>
                                    </fo:table-cell>
                                    	
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            <xsl:value-of select="/izvestaj/broj_zahteva"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            Број одбијених захтева
                                        </fo:block>
                                    </fo:table-cell>
                                    	
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            <xsl:value-of select="/izvestaj/broj_odbijenih_zahteva"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <fo:table-row border="1px solid darkgrey">
	                                <xsl:variable name="odluka" select="/izvestaj/broj_zalbi_na_cutanje" />
	                                <xsl:variable name="cutanje" select="/izvestaj/broj_zalbi_na_odluku" />
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            Укупан број жалби
                                        </fo:block>
                                    </fo:table-cell>
                                    	
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            <xsl:value-of select="$odluka + $cutanje"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            Број предатих жалби на ћутање
                                        </fo:block>
                                    </fo:table-cell>
                                    	
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            <xsl:value-of select="/izvestaj/broj_zalbi_na_cutanje"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                                <fo:table-row border="1px solid darkgrey">
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            Број предатих жалби на одлуку
                                        </fo:block>
                                    </fo:table-cell>
                                    	
                                    <fo:table-cell padding="10px">
                                        <fo:block font-family="Times New Roman" text-align="center"	font-size="11pt">
                                            <xsl:value-of select="/izvestaj/broj_zalbi_na_odluku"/>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
