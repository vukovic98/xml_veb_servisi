//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.01 at 02:36:59 PM CET 
//


package com.ftn.xml.model.obavestenje;

import java.math.BigDecimal;
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
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}godina_zahteva"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}opis_trazene_informacije"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}datum_uvida"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}cas_uvida"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}sat_od"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}sat_do"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}adresa"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}broj_kancelarije"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}cena"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/obavestenje}ziro_racun"/>
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
    protected String datumUvida;
    @XmlElement(name = "cas_uvida", required = true)
    protected String casUvida;
    @XmlElement(name = "sat_od", required = true)
    protected String satOd;
    @XmlElement(name = "sat_do", required = true)
    protected String satDo;
    @XmlElement(required = true)
    protected Adresa adresa;
    @XmlElement(name = "broj_kancelarije", required = true)
    protected int brojKancelarije;
    @XmlElement(required = true)
    protected long cena;
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
     *     {@link String }
     *     
     */
    public String getDatumUvida() {
        return datumUvida;
    }

    /**
     * Sets the value of the datumUvida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumUvida(String value) {
        this.datumUvida = value;
    }

    /**
     * Gets the value of the casUvida property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCasUvida() {
        return casUvida;
    }

    /**
     * Sets the value of the casUvida property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCasUvida(String value) {
        this.casUvida = value;
    }

    /**
     * Gets the value of the satOd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSatOd() {
        return satOd;
    }

    /**
     * Sets the value of the satOd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatOd(String value) {
        this.satOd = value;
    }

    /**
     * Gets the value of the satDo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSatDo() {
        return satDo;
    }

    /**
     * Sets the value of the satDo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSatDo(String value) {
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
    public int getBrojKancelarije() {
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
    public void setBrojKancelarije(int value) {
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
    public long getCena() {
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
    public void setCena(long value) {
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
