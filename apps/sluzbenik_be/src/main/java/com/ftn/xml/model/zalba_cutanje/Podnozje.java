//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.30 at 10:56:50 PM CET 
//


package com.ftn.xml.model.zalba_cutanje;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}podnosilac_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}mesto_i_datum"/>
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
    "podnosilacZalbe",
    "mestoIDatum"
})
@XmlRootElement(name = "podnozje")
public class Podnozje {

    @XmlElement(name = "podnosilac_zalbe", required = true)
    protected PodnosilacZalbe podnosilacZalbe;
    @XmlElement(name = "mesto_i_datum", required = true)
    protected MestoIDatum mestoIDatum;

    /**
     * Gets the value of the podnosilacZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link PodnosilacZalbe }
     *     
     */
    public PodnosilacZalbe getPodnosilacZalbe() {
        return podnosilacZalbe;
    }

    /**
     * Sets the value of the podnosilacZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodnosilacZalbe }
     *     
     */
    public void setPodnosilacZalbe(PodnosilacZalbe value) {
        this.podnosilacZalbe = value;
    }

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

}
