//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.06 at 06:22:57 PM CET 
//


package com.ftn.xml.jaxb.zalba_na_odluku;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalba_na_odluku"/>
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
    "zalbaNaOdluku"
})
@XmlRootElement(name = "lista_zalbi_na_odluku")
public class ListaZalbiNaOdluku {

    @XmlElement(name = "zalba_na_odluku", required = true)
    protected ZalbaNaOdluku zalbaNaOdluku;

    /**
     * Gets the value of the zalbaNaOdluku property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaNaOdluku }
     *     
     */
    public ZalbaNaOdluku getZalbaNaOdluku() {
        return zalbaNaOdluku;
    }

    /**
     * Sets the value of the zalbaNaOdluku property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaNaOdluku }
     *     
     */
    public void setZalbaNaOdluku(ZalbaNaOdluku value) {
        this.zalbaNaOdluku = value;
    }

}
