//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.05 at 01:08:25 PM CET 
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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}mesto_zakljucka_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}datum_zakljucka_zalbe"/>
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podaci_o_zaliocu"/>
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
    "mestoZakljuckaZalbe",
    "datumZakljuckaZalbe",
    "podaciOZaliocu"
})
@XmlRootElement(name = "podnozje")
public class Podnozje {

    @XmlElement(name = "mesto_zakljucka_zalbe", required = true)
    protected MestoZakljuckaZalbe mestoZakljuckaZalbe;
    @XmlElement(name = "datum_zakljucka_zalbe", required = true)
    protected DatumZakljuckaZalbe datumZakljuckaZalbe;
    @XmlElement(name = "podaci_o_zaliocu", required = true)
    protected PodaciOZaliocu podaciOZaliocu;

    /**
     * Gets the value of the mestoZakljuckaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link MestoZakljuckaZalbe }
     *     
     */
    public MestoZakljuckaZalbe getMestoZakljuckaZalbe() {
        return mestoZakljuckaZalbe;
    }

    /**
     * Sets the value of the mestoZakljuckaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link MestoZakljuckaZalbe }
     *     
     */
    public void setMestoZakljuckaZalbe(MestoZakljuckaZalbe value) {
        this.mestoZakljuckaZalbe = value;
    }

    /**
     * Gets the value of the datumZakljuckaZalbe property.
     * 
     * @return
     *     possible object is
     *     {@link DatumZakljuckaZalbe }
     *     
     */
    public DatumZakljuckaZalbe getDatumZakljuckaZalbe() {
        return datumZakljuckaZalbe;
    }

    /**
     * Sets the value of the datumZakljuckaZalbe property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatumZakljuckaZalbe }
     *     
     */
    public void setDatumZakljuckaZalbe(DatumZakljuckaZalbe value) {
        this.datumZakljuckaZalbe = value;
    }

    /**
     * Gets the value of the podaciOZaliocu property.
     * 
     * @return
     *     possible object is
     *     {@link PodaciOZaliocu }
     *     
     */
    public PodaciOZaliocu getPodaciOZaliocu() {
        return podaciOZaliocu;
    }

    /**
     * Sets the value of the podaciOZaliocu property.
     * 
     * @param value
     *     allowed object is
     *     {@link PodaciOZaliocu }
     *     
     */
    public void setPodaciOZaliocu(PodaciOZaliocu value) {
        this.podaciOZaliocu = value;
    }

}
