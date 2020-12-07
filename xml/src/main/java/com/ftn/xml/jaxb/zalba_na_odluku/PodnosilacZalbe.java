
package com.ftn.xml.jaxb.zalba_na_odluku;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podnosilac_naziv"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podnosilac_adresa"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podnosilac_sediste"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilacNaziv",
    "podnosilacAdresa",
    "podnosilacSediste"
})
@XmlRootElement(name = "podnosilac_zalbe", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class PodnosilacZalbe {

    @XmlElement(name = "podnosilac_naziv", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected String podnosilacNaziv;
    @XmlElement(name = "podnosilac_adresa", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected PodnosilacAdresa podnosilacAdresa;
    @XmlElement(name = "podnosilac_sediste", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected String podnosilacSediste;

    /**
     * Gets the value of the podnosilacNaziv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodnosilacNaziv() {
        return podnosilacNaziv;
    }

    /**
     * Sets the value of the podnosilacNaziv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodnosilacNaziv(String value) {
        this.podnosilacNaziv = value;
    }

    /**
     * Gets the value of the podnosilacAdresa property.
     * 
     * @return
     *     possible object is
     *     {@link PodnosilacAdresa }
     *     
     */
    public PodnosilacAdresa getPodnosilacAdresa() {
        return podnosilacAdresa;
    }

    /**
     * Sets the value of the podnosilacAdresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodnosilacAdresa }
     *     
     */
    public void setPodnosilacAdresa(PodnosilacAdresa value) {
        this.podnosilacAdresa = value;
    }

    /**
     * Gets the value of the podnosilacSediste property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPodnosilacSediste() {
        return podnosilacSediste;
    }

    /**
     * Sets the value of the podnosilacSediste property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPodnosilacSediste(String value) {
        this.podnosilacSediste = value;
    }

}
