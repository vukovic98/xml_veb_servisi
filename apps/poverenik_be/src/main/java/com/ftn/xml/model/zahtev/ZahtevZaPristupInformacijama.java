//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.17 at 07:07:41 PM CET 
//


package com.ftn.xml.model.zahtev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}podaci_o_organu"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}sadrzaj"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}podnozje"/>
 *       &lt;/sequence>
 *       &lt;attribute name="about" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="vocab" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podaciOOrganu",
    "sadrzaj",
    "podnozje"
})
@XmlRootElement(name = "zahtev_za_pristup_informacijama")
public class ZahtevZaPristupInformacijama {

    @XmlElement(name = "podaci_o_organu", required = true)
    protected PodaciOOrganu podaciOOrganu;
    @XmlElement(required = true)
    protected Sadrzaj sadrzaj;
    @XmlElement(required = true)
    protected Podnozje podnozje;
    @XmlAttribute(name = "about", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String about;
    @XmlAttribute(name = "vocab", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String vocab;

    /**
     * Gets the value of the podaciOOrganu property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciOOrganu }
     *     
     */
    public PodaciOOrganu getPodaciOOrganu() {
        return podaciOOrganu;
    }

    /**
     * Sets the value of the podaciOOrganu property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciOOrganu }
     *     
     */
    public void setPodaciOOrganu(PodaciOOrganu value) {
        this.podaciOOrganu = value;
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

    /**
     * Gets the value of the about property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets the value of the about property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbout(String value) {
        this.about = value;
    }

    /**
     * Gets the value of the vocab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVocab() {
        return vocab;
    }

    /**
     * Sets the value of the vocab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVocab(String value) {
        this.vocab = value;
    }

}
