//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.21 at 07:35:32 PM CET 
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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}zalba_cutanje"/>
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
    "zalbaCutanje"
})
@XmlRootElement(name = "lista_zalbi_cutanje")
public class ListaZalbiCutanje {

    @XmlElement(name = "zalba_cutanje", required = true)
    protected ZalbaCutanje zalbaCutanje;

    /**
     * Gets the value of the zalbaCutanje property.
     * 
     * @return
     *     possible object is
     *     {@link ZalbaCutanje }
     *     
     */
    public ZalbaCutanje getZalbaCutanje() {
        return zalbaCutanje;
    }

    /**
     * Sets the value of the zalbaCutanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZalbaCutanje }
     *     
     */
    public void setZalbaCutanje(ZalbaCutanje value) {
        this.zalbaCutanje = value;
    }

}
