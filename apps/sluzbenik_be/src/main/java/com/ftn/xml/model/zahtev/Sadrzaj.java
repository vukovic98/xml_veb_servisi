//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.31 at 05:18:16 PM CET 
//


package com.ftn.xml.model.zahtev;

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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}zahtevi"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}opis_trazene_informacije"/>
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
    "zahtevi",
    "opisTrazeneInformacije"
})
@XmlRootElement(name = "sadrzaj")
public class Sadrzaj {

    @XmlElement(required = true)
    protected Zahtevi zahtevi;
    @XmlElement(name = "opis_trazene_informacije", required = true)
    protected OpisTrazeneInformacije opisTrazeneInformacije;

    /**
     * Gets the value of the zahtevi property.
     * 
     * @return
     *     possible object is
     *     {@link Zahtevi }
     *     
     */
    public Zahtevi getZahtevi() {
        return zahtevi;
    }

    /**
     * Sets the value of the zahtevi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zahtevi }
     *     
     */
    public void setZahtevi(Zahtevi value) {
        this.zahtevi = value;
    }

    /**
     * Gets the value of the opisTrazeneInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link OpisTrazeneInformacije }
     *     
     */
    public OpisTrazeneInformacije getOpisTrazeneInformacije() {
        return opisTrazeneInformacije;
    }

    /**
     * Sets the value of the opisTrazeneInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpisTrazeneInformacije }
     *     
     */
    public void setOpisTrazeneInformacije(OpisTrazeneInformacije value) {
        this.opisTrazeneInformacije = value;
    }

}
