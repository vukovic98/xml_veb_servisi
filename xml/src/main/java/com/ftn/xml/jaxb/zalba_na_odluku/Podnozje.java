
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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}mesto_i_datum"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}informacije_o_traziocu"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}napomene"/&gt;
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
    "mestoIDatum",
    "informacijeOTraziocu",
    "napomene"
})
@XmlRootElement(name = "podnozje", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class Podnozje {

    @XmlElement(name = "mesto_i_datum", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected MestoIDatum mestoIDatum;
    @XmlElement(name = "informacije_o_traziocu", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected InformacijeOTraziocu informacijeOTraziocu;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected Napomene napomene;

    /**
     * Gets the value of the mestoIDatum property.
     * 
     * @return
     *     possible object is
     *     {@link MestoIDatum }
     *     
     */
    public MestoIDatum getMestoIDatum() {
        return mestoIDatum;
    }

    /**
     * Sets the value of the mestoIDatum property.
     * 
     * @param value
     *     allowed object is
     *     {@link MestoIDatum }
     *     
     */
    public void setMestoIDatum(MestoIDatum value) {
        this.mestoIDatum = value;
    }

    /**
     * Gets the value of the informacijeOTraziocu property.
     * 
     * @return
     *     possible object is
     *     {@link InformacijeOTraziocu }
     *     
     */
    public InformacijeOTraziocu getInformacijeOTraziocu() {
        return informacijeOTraziocu;
    }

    /**
     * Sets the value of the informacijeOTraziocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link InformacijeOTraziocu }
     *     
     */
    public void setInformacijeOTraziocu(InformacijeOTraziocu value) {
        this.informacijeOTraziocu = value;
    }

    /**
     * Gets the value of the napomene property.
     * 
     * @return
     *     possible object is
     *     {@link Napomene }
     *     
     */
    public Napomene getNapomene() {
        return napomene;
    }

    /**
     * Sets the value of the napomene property.
     * 
     * @param value
     *     allowed object is
     *     {@link Napomene }
     *     
     */
    public void setNapomene(Napomene value) {
        this.napomene = value;
    }

}
