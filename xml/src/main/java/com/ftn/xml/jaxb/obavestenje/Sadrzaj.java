//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.04 at 02:42:49 PM CET 
//


package com.ftn.xml.jaxb.obavestenje;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}godina_zahteva"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}opis_trazene_informacije"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}datum_uvida"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}cas_uvida"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}sat_od"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}sat_do"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}adresa"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}broj_kancelarije"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}cena"/>
 *         &lt;element ref="{http://www.w3.org/ns/obavestenje}ziro_racun"/>
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
    "godinaZahteva",
    "opisTrazeneInformacije",
    "datumUvida",
    "casUvida",
    "satOd",
    "satDo",
    "adresa",
    "brojKancelarije",
    "cena",
    "ziroRacun"
})
@XmlRootElement(name = "sadrzaj")
public class Sadrzaj {

    @XmlElement(name = "godina_zahteva", required = true)
    protected GodinaZahteva godinaZahteva;
    @XmlElement(name = "opis_trazene_informacije", required = true)
    protected OpisTrazeneInformacije opisTrazeneInformacije;
    @XmlElement(name = "datum_uvida", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumUvida;
    @XmlElement(name = "cas_uvida", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar casUvida;
    @XmlElement(name = "sat_od", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar satOd;
    @XmlElement(name = "sat_do", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar satDo;
    @XmlElement(required = true)
    protected Adresa adresa;
    @XmlElement(name = "broj_kancelarije", required = true)
    protected BigInteger brojKancelarije;
    @XmlElement(required = true)
    protected BigDecimal cena;
    @XmlElement(name = "ziro_racun", required = true)
    protected ZiroRacun ziroRacun;

    /**
     * Gets the value of the godinaZahteva property.
     * 
     * @return
     *     possible object is
     *     {@link GodinaZahteva }
     *     
     */
    public GodinaZahteva getGodinaZahteva() {
        return godinaZahteva;
    }

    /**
     * Sets the value of the godinaZahteva property.
     * 
     * @param value
     *     allowed object is
     *     {@link GodinaZahteva }
     *     
     */
    public void setGodinaZahteva(GodinaZahteva value) {
        this.godinaZahteva = value;
    }

    /**
     * Gets the value of the opisTrazeneInformacije property.
     * 
     * @return
     *     possible object is
     *     {@link OpisTrazeneInformacije }
     *     
     */
    public OpisTrazeneInformacije getOpisTrazeneInformacije() {
        return opisTrazeneInformacije;
    }

    /**
     * Sets the value of the opisTrazeneInformacije property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpisTrazeneInformacije }
     *     
     */
    public void setOpisTrazeneInformacije(OpisTrazeneInformacije value) {
        this.opisTrazeneInformacije = value;
    }

    /**
     * Gets the value of the datumUvida property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumUvida() {
        return datumUvida;
    }

    /**
     * Sets the value of the datumUvida property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumUvida(XMLGregorianCalendar value) {
        this.datumUvida = value;
    }

    /**
     * Gets the value of the casUvida property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCasUvida() {
        return casUvida;
    }

    /**
     * Sets the value of the casUvida property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCasUvida(XMLGregorianCalendar value) {
        this.casUvida = value;
    }

    /**
     * Gets the value of the satOd property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSatOd() {
        return satOd;
    }

    /**
     * Sets the value of the satOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSatOd(XMLGregorianCalendar value) {
        this.satOd = value;
    }

    /**
     * Gets the value of the satDo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSatDo() {
        return satDo;
    }

    /**
     * Sets the value of the satDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSatDo(XMLGregorianCalendar value) {
        this.satDo = value;
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

    /**
     * Gets the value of the brojKancelarije property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getBrojKancelarije() {
        return brojKancelarije;
    }

    /**
     * Sets the value of the brojKancelarije property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setBrojKancelarije(BigInteger value) {
        this.brojKancelarije = value;
    }

    /**
     * Gets the value of the cena property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCena() {
        return cena;
    }

    /**
     * Sets the value of the cena property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCena(BigDecimal value) {
        this.cena = value;
    }

    /**
     * Gets the value of the ziroRacun property.
     * 
     * @return
     *     possible object is
     *     {@link ZiroRacun }
     *     
     */
    public ZiroRacun getZiroRacun() {
        return ziroRacun;
    }

    /**
     * Sets the value of the ziroRacun property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZiroRacun }
     *     
     */
    public void setZiroRacun(ZiroRacun value) {
        this.ziroRacun = value;
    }

}
