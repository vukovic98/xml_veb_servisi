//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.30 at 10:56:50 PM CET 
//


package com.ftn.xml.model.zalba_cutanje;

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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}korisnik_email"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}ime_i_prezime"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}adresa"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}drugi_podaci_za_kontakt"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_cutanje}potpis"/>
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
    "imeIPrezime",
    "adresa",
    "drugiPodaciZaKontakt",
    "potpis"
})
@XmlRootElement(name = "podnosilac_zalbe")
public class PodnosilacZalbe {

    @XmlElement(name = "korisnik_email", required = true)
    protected KorisnikEmail korisnikEmail;
    @XmlElement(name = "ime_i_prezime", required = true)
    protected ImeIPrezime imeIPrezime;
    @XmlElement(required = true)
    protected Adresa adresa;
    @XmlElement(name = "drugi_podaci_za_kontakt", required = true)
    protected String drugiPodaciZaKontakt;
    @XmlElement(required = true)
    protected Potpis potpis;

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
     * Gets the value of the drugiPodaciZaKontakt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugiPodaciZaKontakt() {
        return drugiPodaciZaKontakt;
    }

    /**
     * Sets the value of the drugiPodaciZaKontakt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugiPodaciZaKontakt(String value) {
        this.drugiPodaciZaKontakt = value;
    }

    /**
     * Gets the value of the potpis property.
     * 
     * @return
     *     possible object is
     *     {@link Potpis }
     *     
     */
    public Potpis getPotpis() {
        return potpis;
    }

    /**
     * Sets the value of the potpis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Potpis }
     *     
     */
    public void setPotpis(Potpis value) {
        this.potpis = value;
    }

}
