//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.03 at 04:48:37 PM CET 
//


package com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://ftn.uns.ac.rs/izjasnjenje/odgovor}id_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/izjasnjenje/odgovor}sadrzaj"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/izjasnjenje/odgovor}tip"/>
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
    "idZalbe",
    "sadrzaj",
    "tip"
})
@XmlRootElement(name = "odgovor_zahtev_za_izjasnjenje")
public class OdgovorZahtevZaIzjasnjenje {

    @XmlElement(name = "id_zalbe", required = true)
    protected BigInteger idZalbe;
    @XmlElement(required = true)
    protected String sadrzaj;
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String tip;

    /**
     * Gets the value of the idZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdZalbe() {
        return idZalbe;
    }

    /**
     * Sets the value of the idZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdZalbe(BigInteger value) {
        this.idZalbe = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSadrzaj(String value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the tip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTip() {
        return tip;
    }

    /**
     * Sets the value of the tip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTip(String value) {
        this.tip = value;
    }

}
