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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}zaglavlje"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}sadrzaj"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}podnozje"/>
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
    "zaglavlje",
    "sadrzaj",
    "podnozje"
})
@XmlRootElement(name = "zalba_cutanje")
public class ZalbaCutanje {

    @XmlElement(required = true)
    protected Zaglavlje zaglavlje;
    @XmlElement(required = true)
    protected Sadrzaj sadrzaj;
    @XmlElement(required = true)
    protected Podnozje podnozje;

    /**
     * Gets the value of the zaglavlje property.
     * 
     * @return
     *     possible object is
     *     {@link Zaglavlje }
     *     
     */
    public Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    /**
     * Sets the value of the zaglavlje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zaglavlje }
     *     
     */
    public void setZaglavlje(Zaglavlje value) {
        this.zaglavlje = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link Sadrzaj }
     *     
     */
    public Sadrzaj getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sadrzaj }
     *     
     */
    public void setSadrzaj(Sadrzaj value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the podnozje property.
     * 
     * @return
     *     possible object is
     *     {@link Podnozje }
     *     
     */
    public Podnozje getPodnozje() {
        return podnozje;
    }

    /**
     * Sets the value of the podnozje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Podnozje }
     *     
     */
    public void setPodnozje(Podnozje value) {
        this.podnozje = value;
    }

}