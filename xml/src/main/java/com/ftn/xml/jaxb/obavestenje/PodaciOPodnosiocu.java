//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.04 at 02:42:49 PM CET 
//


package com.ftn.xml.jaxb.obavestenje;

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
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}ime_i_prezime"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}naziv"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}adresa"/>
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
    "imeIPrezime",
    "naziv",
    "adresa"
})
@XmlRootElement(name = "podaci_o_podnosiocu")
public class PodaciOPodnosiocu {

    @XmlElement(name = "ime_i_prezime", required = true)
    protected ImeIPrezime imeIPrezime;
    @XmlElement(required = true)
    protected Naziv naziv;
    @XmlElement(required = true)
    protected Adresa adresa;

    /**
     * Gets the value of the imeIPrezime property.
     * 
     * @return
     *     possible object is
     *     {@link ImeIPrezime }
     *     
     */
    public ImeIPrezime getImeIPrezime() {
        return imeIPrezime;
    }

    /**
     * Sets the value of the imeIPrezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImeIPrezime }
     *     
     */
    public void setImeIPrezime(ImeIPrezime value) {
        this.imeIPrezime = value;
    }

    /**
     * Gets the value of the naziv property.
     * 
     * @return
     *     possible object is
     *     {@link Naziv }
     *     
     */
    public Naziv getNaziv() {
        return naziv;
    }

    /**
     * Sets the value of the naziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Naziv }
     *     
     */
    public void setNaziv(Naziv value) {
        this.naziv = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link Adresa }
     *     
     */
    public Adresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Adresa }
     *     
     */
    public void setAdresa(Adresa value) {
        this.adresa = value;
    }

}
