//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.31 at 05:18:16 PM CET 
//


package com.ftn.xml.model.zahtev;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}zahtev_za_pristup_informacijama"/>
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
    "zahtevZaPristupInformacijama"
})
@XmlRootElement(name = "lista_zahteva_za_pristup_informacijama")
public class ListaZahtevaZaPristupInformacijama {

    @XmlElement(name = "zahtev_za_pristup_informacijama", required = true)
    protected List<ZahtevZaPristupInformacijama> zahtevZaPristupInformacijama;

    /**
     * Gets the value of the zahtevZaPristupInformacijama property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zahtevZaPristupInformacijama property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZahtevZaPristupInformacijama().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ZahtevZaPristupInformacijama }
     * 
     * 
     */
    public List<ZahtevZaPristupInformacijama> getZahtevZaPristupInformacijama() {
        if (zahtevZaPristupInformacijama == null) {
            zahtevZaPristupInformacijama = new ArrayList<ZahtevZaPristupInformacijama>();
        }
        return this.zahtevZaPristupInformacijama;
    }

}
