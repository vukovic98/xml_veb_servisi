
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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}sadrzaj_zalbe"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podnozje"/&gt;
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
    "sadrzajZalbe",
    "podnozje"
})
@XmlRootElement(name = "zalba_na_odluku", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class ZalbaNaOdluku {

    @XmlElement(name = "sadrzaj_zalbe", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected SadrzajZalbe sadrzajZalbe;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected Podnozje podnozje;

    /**
     * Gets the value of the sadrzajZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link SadrzajZalbe }
     *     
     */
    public SadrzajZalbe getSadrzajZalbe() {
        return sadrzajZalbe;
    }

    /**
     * Sets the value of the sadrzajZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link SadrzajZalbe }
     *     
     */
    public void setSadrzajZalbe(SadrzajZalbe value) {
        this.sadrzajZalbe = value;
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

}
