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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}mesto_i_datum"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}informacije_o_traziocu"/>
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
    "mestoIDatum",
    "informacijeOTraziocu"
})
@XmlRootElement(name = "podnozje")
public class Podnozje {

    @XmlElement(name = "mesto_i_datum", required = true)
    protected MestoIDatum mestoIDatum;
    @XmlElement(name = "informacije_o_traziocu", required = true)
    protected InformacijeOTraziocu informacijeOTraziocu;

    /**
     * Gets the value of the mestoIDatum property.
     * 
     * @return
     *     possible object is
     *     {@link MestoIDatum }
     *     
     */
    public MestoIDatum getMestoIDatum() {
        return mestoIDatum;
    }

    /**
     * Sets the value of the mestoIDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link MestoIDatum }
     *     
     */
    public void setMestoIDatum(MestoIDatum value) {
        this.mestoIDatum = value;
    }

    /**
     * Gets the value of the informacijeOTraziocu property.
     * 
     * @return
     *     possible object is
     *     {@link InformacijeOTraziocu }
     *     
     */
    public InformacijeOTraziocu getInformacijeOTraziocu() {
        return informacijeOTraziocu;
    }

    /**
     * Sets the value of the informacijeOTraziocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacijeOTraziocu }
     *     
     */
    public void setInformacijeOTraziocu(InformacijeOTraziocu value) {
        this.informacijeOTraziocu = value;
    }

}