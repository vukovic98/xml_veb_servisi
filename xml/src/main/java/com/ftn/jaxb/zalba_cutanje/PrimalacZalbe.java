//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.05 at 07:03:30 PM CET 
//


package com.ftn.jaxb.zalba_cutanje;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}naziv_primaoca"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}adresa_za_postu"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nazivPrimaoca",
    "adresaZaPostu"
})
@XmlRootElement(name = "primalac_zalbe")
public class PrimalacZalbe {

    @XmlElement(name = "naziv_primaoca", required = true)
    protected String nazivPrimaoca;
    @XmlElement(name = "adresa_za_postu", required = true)
    protected String adresaZaPostu;

    /**
     * Gets the value of the nazivPrimaoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazivPrimaoca() {
        return nazivPrimaoca;
    }

    /**
     * Sets the value of the nazivPrimaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazivPrimaoca(String value) {
        this.nazivPrimaoca = value;
    }

    /**
     * Gets the value of the adresaZaPostu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdresaZaPostu() {
        return adresaZaPostu;
    }

    /**
     * Sets the value of the adresaZaPostu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdresaZaPostu(String value) {
        this.adresaZaPostu = value;
    }

}