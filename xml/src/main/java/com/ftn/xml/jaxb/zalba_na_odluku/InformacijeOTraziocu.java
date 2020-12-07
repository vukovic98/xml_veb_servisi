
package com.ftn.xml.jaxb.zalba_na_odluku;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice maxOccurs="unbounded" minOccurs="0"&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}adresa"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}ime"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}kontakt"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}potpis" minOccurs="0"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}prezime"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "adresaOrImeOrKontakt"
})
@XmlRootElement(name = "informacije_o_traziocu", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class InformacijeOTraziocu {

    @XmlElementRefs({
        @XmlElementRef(name = "adresa", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = Adresa.class, required = false),
        @XmlElementRef(name = "ime", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "kontakt", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "potpis", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "prezime", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false)
    })
    protected List<Object> adresaOrImeOrKontakt;

    /**
     * Gets the value of the adresaOrImeOrKontakt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the adresaOrImeOrKontakt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdresaOrImeOrKontakt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Adresa }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<Object> getAdresaOrImeOrKontakt() {
        if (adresaOrImeOrKontakt == null) {
            adresaOrImeOrKontakt = new ArrayList<Object>();
        }
        return this.adresaOrImeOrKontakt;
    }

}
