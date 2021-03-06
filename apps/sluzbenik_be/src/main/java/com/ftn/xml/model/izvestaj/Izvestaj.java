//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.04 at 11:51:28 AM CET 
//


package com.ftn.xml.model.izvestaj;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/izvestaj}broj_zahteva"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/izvestaj}broj_odbijenih_zahteva"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/izvestaj}broj_zalbi_na_cutanje"/>
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/izvestaj}broj_zalbi_na_odluku"/>
 *       &lt;/sequence>
 *       &lt;attribute name="datum" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "brojZahteva",
    "brojOdbijenihZahteva",
    "brojZalbiNaCutanje",
    "brojZalbiNaOdluku"
})
@XmlRootElement(name = "izvestaj")
public class Izvestaj {

    @XmlElement(name = "broj_zahteva", required = true)
    protected long brojZahteva;
    @XmlElement(name = "broj_odbijenih_zahteva", required = true)
    protected long brojOdbijenihZahteva;
    @XmlElement(name = "broj_zalbi_na_cutanje", required = true)
    protected long brojZalbiNaCutanje;
    @XmlElement(name = "broj_zalbi_na_odluku", required = true)
    protected long brojZalbiNaOdluku;
    @XmlAttribute(name = "datum", required = true)
    protected String datum;

    /**
     * Gets the value of the brojZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public long getBrojZahteva() {
        return brojZahteva;
    }

    /**
     * Sets the value of the brojZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojZahteva(long value) {
        this.brojZahteva = value;
    }

    /**
     * Gets the value of the brojOdbijenihZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public long getBrojOdbijenihZahteva() {
        return brojOdbijenihZahteva;
    }

    /**
     * Sets the value of the brojOdbijenihZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojOdbijenihZahteva(long value) {
        this.brojOdbijenihZahteva = value;
    }

    /**
     * Gets the value of the brojZalbiNaCutanje property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public long getBrojZalbiNaCutanje() {
        return brojZalbiNaCutanje;
    }

    /**
     * Sets the value of the brojZalbiNaCutanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojZalbiNaCutanje(long value) {
        this.brojZalbiNaCutanje = value;
    }

    /**
     * Gets the value of the brojZalbiNaOdluku property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public long getBrojZalbiNaOdluku() {
        return brojZalbiNaOdluku;
    }

    /**
     * Sets the value of the brojZalbiNaOdluku property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojZalbiNaOdluku(long value) {
        this.brojZalbiNaOdluku = value;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public String getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(String value) {
        this.datum = value;
    }

}
