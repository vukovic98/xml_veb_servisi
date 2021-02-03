//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.03 at 11:45:18 AM CET 
//


package com.ftn.xml.model.zahtev_za_izjasnjenje_odluka;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}korisnik_email" minOccurs="0"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalioc_ime"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalioc_prezime"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalioc_naziv_zalbe" minOccurs="0"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalioc_adresa"/>
 *         &lt;choice>
 *           &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zalioc_sediste"/>
 *           &lt;sequence>
 *             &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}drugi_podaci_za_kontakt"/>
 *             &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}potpis_zalioca"/>
 *           &lt;/sequence>
 *         &lt;/choice>
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
    "korisnikEmail",
    "zaliocIme",
    "zaliocPrezime",
    "zaliocNazivZalbe",
    "zaliocAdresa",
    "zaliocSediste",
    "drugiPodaciZaKontakt",
    "potpisZalioca"
})
@XmlRootElement(name = "podaci_o_zaliocu")
public class PodaciOZaliocu {

    @XmlElement(name = "korisnik_email")
    protected KorisnikEmail korisnikEmail;
    @XmlElement(name = "zalioc_ime", required = true)
    protected ZaliocIme zaliocIme;
    @XmlElement(name = "zalioc_prezime", required = true)
    protected ZaliocPrezime zaliocPrezime;
    @XmlElement(name = "zalioc_naziv_zalbe")
    protected ZaliocNazivZalbe zaliocNazivZalbe;
    @XmlElement(name = "zalioc_adresa", required = true)
    protected ZaliocAdresa zaliocAdresa;
    @XmlElement(name = "zalioc_sediste")
    protected ZaliocSediste zaliocSediste;
    @XmlElement(name = "drugi_podaci_za_kontakt")
    protected DrugiPodaciZaKontakt drugiPodaciZaKontakt;
    @XmlElement(name = "potpis_zalioca")
    protected PotpisZalioca potpisZalioca;

    /**
     * Gets the value of the korisnikEmail property.
     * 
     * @return
     *     possible object is
     *     {@link KorisnikEmail }
     *     
     */
    public KorisnikEmail getKorisnikEmail() {
        return korisnikEmail;
    }

    /**
     * Sets the value of the korisnikEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link KorisnikEmail }
     *     
     */
    public void setKorisnikEmail(KorisnikEmail value) {
        this.korisnikEmail = value;
    }

    /**
     * Gets the value of the zaliocIme property.
     * 
     * @return
     *     possible object is
     *     {@link ZaliocIme }
     *     
     */
    public ZaliocIme getZaliocIme() {
        return zaliocIme;
    }

    /**
     * Sets the value of the zaliocIme property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaliocIme }
     *     
     */
    public void setZaliocIme(ZaliocIme value) {
        this.zaliocIme = value;
    }

    /**
     * Gets the value of the zaliocPrezime property.
     * 
     * @return
     *     possible object is
     *     {@link ZaliocPrezime }
     *     
     */
    public ZaliocPrezime getZaliocPrezime() {
        return zaliocPrezime;
    }

    /**
     * Sets the value of the zaliocPrezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaliocPrezime }
     *     
     */
    public void setZaliocPrezime(ZaliocPrezime value) {
        this.zaliocPrezime = value;
    }

    /**
     * Gets the value of the zaliocNazivZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link ZaliocNazivZalbe }
     *     
     */
    public ZaliocNazivZalbe getZaliocNazivZalbe() {
        return zaliocNazivZalbe;
    }

    /**
     * Sets the value of the zaliocNazivZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaliocNazivZalbe }
     *     
     */
    public void setZaliocNazivZalbe(ZaliocNazivZalbe value) {
        this.zaliocNazivZalbe = value;
    }

    /**
     * Gets the value of the zaliocAdresa property.
     * 
     * @return
     *     possible object is
     *     {@link ZaliocAdresa }
     *     
     */
    public ZaliocAdresa getZaliocAdresa() {
        return zaliocAdresa;
    }

    /**
     * Sets the value of the zaliocAdresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaliocAdresa }
     *     
     */
    public void setZaliocAdresa(ZaliocAdresa value) {
        this.zaliocAdresa = value;
    }

    /**
     * Gets the value of the zaliocSediste property.
     * 
     * @return
     *     possible object is
     *     {@link ZaliocSediste }
     *     
     */
    public ZaliocSediste getZaliocSediste() {
        return zaliocSediste;
    }

    /**
     * Sets the value of the zaliocSediste property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZaliocSediste }
     *     
     */
    public void setZaliocSediste(ZaliocSediste value) {
        this.zaliocSediste = value;
    }

    /**
     * Gets the value of the drugiPodaciZaKontakt property.
     * 
     * @return
     *     possible object is
     *     {@link DrugiPodaciZaKontakt }
     *     
     */
    public DrugiPodaciZaKontakt getDrugiPodaciZaKontakt() {
        return drugiPodaciZaKontakt;
    }

    /**
     * Sets the value of the drugiPodaciZaKontakt property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugiPodaciZaKontakt }
     *     
     */
    public void setDrugiPodaciZaKontakt(DrugiPodaciZaKontakt value) {
        this.drugiPodaciZaKontakt = value;
    }

    /**
     * Gets the value of the potpisZalioca property.
     * 
     * @return
     *     possible object is
     *     {@link PotpisZalioca }
     *     
     */
    public PotpisZalioca getPotpisZalioca() {
        return potpisZalioca;
    }

    /**
     * Sets the value of the potpisZalioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link PotpisZalioca }
     *     
     */
    public void setPotpisZalioca(PotpisZalioca value) {
        this.potpisZalioca = value;
    }

}
