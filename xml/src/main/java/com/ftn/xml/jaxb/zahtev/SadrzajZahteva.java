//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.06 at 08:57:42 PM CET 
//


package com.ftn.xml.jaxb.zahtev;

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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}naslov"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}paragraf"/>
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
    "naslov",
    "paragraf"
})
@XmlRootElement(name = "sadrzaj_zahteva")
public class SadrzajZahteva {

    @XmlElement(required = true)
    protected String naslov;
    @XmlElement(required = true)
    protected Paragraf paragraf;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the paragraf property.
     * 
     * @return
     *     possible object is
     *     {@link Paragraf }
     *     
     */
    public Paragraf getParagraf() {
        return paragraf;
    }

    /**
     * Sets the value of the paragraf property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paragraf }
     *     
     */
    public void setParagraf(Paragraf value) {
        this.paragraf = value;
    }

}
