//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.04 at 02:26:48 PM CET 
//


package com.ftn.xml.model.brojac;

import java.math.BigInteger;
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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/brojac}brojac_zalbi"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/brojac}brojac_resenje"/>
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
    "brojacZalbi",
    "brojacResenje"
})
@XmlRootElement(name = "brojac")
public class Brojac {

    @XmlElement(name = "brojac_zalbi", required = true)
    protected BigInteger brojacZalbi;
    @XmlElement(name = "brojac_resenje", required = true)
    protected BigInteger brojacResenje;

    /**
     * Gets the value of the brojacZalbi property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojacZalbi() {
        return brojacZalbi;
    }

    /**
     * Sets the value of the brojacZalbi property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojacZalbi(BigInteger value) {
        this.brojacZalbi = value;
    }

    /**
     * Gets the value of the brojacResenje property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojacResenje() {
        return brojacResenje;
    }

    /**
     * Sets the value of the brojacResenje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojacResenje(BigInteger value) {
        this.brojacResenje = value;
    }

}
