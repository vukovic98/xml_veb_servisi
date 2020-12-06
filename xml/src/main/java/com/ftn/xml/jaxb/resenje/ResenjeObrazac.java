//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.06 at 06:51:46 PM CET 
//


package com.ftn.xml.jaxb.resenje;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/resenje}osnovni_podaci"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/resenje}sadrzaj"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/resenje}poverenik"/>
 *       &lt;/sequence>
 *       &lt;attribute name="broj" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "osnovniPodaci",
    "sadrzaj",
    "poverenik"
})
@XmlRootElement(name = "resenje_obrazac")
public class ResenjeObrazac {

    @XmlElement(name = "osnovni_podaci", required = true)
    protected OsnovniPodaci osnovniPodaci;
    @XmlElement(required = true)
    protected Sadrzaj sadrzaj;
    @XmlElement(required = true)
    protected String poverenik;
    @XmlAttribute(name = "broj", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String broj;

    /**
     * Gets the value of the osnovniPodaci property.
     * 
     * @return
     *     possible object is
     *     {@link OsnovniPodaci }
     *     
     */
    public OsnovniPodaci getOsnovniPodaci() {
        return osnovniPodaci;
    }

    /**
     * Sets the value of the osnovniPodaci property.
     * 
     * @param value
     *     allowed object is
     *     {@link OsnovniPodaci }
     *     
     */
    public void setOsnovniPodaci(OsnovniPodaci value) {
        this.osnovniPodaci = value;
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
     * Gets the value of the poverenik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoverenik() {
        return poverenik;
    }

    /**
     * Sets the value of the poverenik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoverenik(String value) {
        this.poverenik = value;
    }

    /**
     * Gets the value of the broj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroj() {
        return broj;
    }

    /**
     * Sets the value of the broj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroj(String value) {
        this.broj = value;
    }

}
