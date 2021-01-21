//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.17 at 07:12:56 PM CET 
//


package com.ftn.xml.model.zalba_na_odluku;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}broj_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}godina_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}datum_odbijenog_zahteva"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}odluka_organa_vlasti"/>
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
    "brojZalbe",
    "godinaZalbe",
    "datumOdbijenogZahteva",
    "odlukaOrganaVlasti"
})
@XmlRootElement(name = "sadrzaj")
public class Sadrzaj {

    @XmlElement(name = "broj_zalbe", required = true)
    protected BrojZalbe brojZalbe;
    @XmlElement(name = "godina_zalbe", required = true)
    protected GodinaZalbe godinaZalbe;
    @XmlElement(name = "datum_odbijenog_zahteva", required = true)
    protected DatumOdbijenogZahteva datumOdbijenogZahteva;
    @XmlElement(name = "odluka_organa_vlasti", required = true)
    protected OdlukaOrganaVlasti odlukaOrganaVlasti;

    /**
     * Gets the value of the brojZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link BrojZalbe }
     *     
     */
    public BrojZalbe getBrojZalbe() {
        return brojZalbe;
    }

    /**
     * Sets the value of the brojZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrojZalbe }
     *     
     */
    public void setBrojZalbe(BrojZalbe value) {
        this.brojZalbe = value;
    }

    /**
     * Gets the value of the godinaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link GodinaZalbe }
     *     
     */
    public GodinaZalbe getGodinaZalbe() {
        return godinaZalbe;
    }

    /**
     * Sets the value of the godinaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link GodinaZalbe }
     *     
     */
    public void setGodinaZalbe(GodinaZalbe value) {
        this.godinaZalbe = value;
    }

    /**
     * Gets the value of the datumOdbijenogZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link DatumOdbijenogZahteva }
     *     
     */
    public DatumOdbijenogZahteva getDatumOdbijenogZahteva() {
        return datumOdbijenogZahteva;
    }

    /**
     * Sets the value of the datumOdbijenogZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatumOdbijenogZahteva }
     *     
     */
    public void setDatumOdbijenogZahteva(DatumOdbijenogZahteva value) {
        this.datumOdbijenogZahteva = value;
    }

    /**
     * Gets the value of the odlukaOrganaVlasti property.
     * 
     * @return
     *     possible object is
     *     {@link OdlukaOrganaVlasti }
     *     
     */
    public OdlukaOrganaVlasti getOdlukaOrganaVlasti() {
        return odlukaOrganaVlasti;
    }

    /**
     * Sets the value of the odlukaOrganaVlasti property.
     * 
     * @param value
     *     allowed object is
     *     {@link OdlukaOrganaVlasti }
     *     
     */
    public void setOdlukaOrganaVlasti(OdlukaOrganaVlasti value) {
        this.odlukaOrganaVlasti = value;
    }

}
