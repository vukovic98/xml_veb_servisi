
package com.ftn.xml.jaxb.zalba_na_odluku;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}naslov"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}paragraf" maxOccurs="unbounded"/&gt;
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
    "naslov",
    "paragraf"
})
@XmlRootElement(name = "sadrzaj_zalbe", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class SadrzajZalbe {

    @XmlElement(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected String naslov;
    @XmlElement(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", required = true)
    protected List<Paragraf> paragraf;

    /**
     * Gets the value of the naslov property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNaslov() {
        return naslov;
    }

    /**
     * Sets the value of the naslov property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNaslov(String value) {
        this.naslov = value;
    }

    /**
     * Gets the value of the paragraf property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paragraf property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParagraf().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Paragraf }
     * 
     * 
     */
    public List<Paragraf> getParagraf() {
        if (paragraf == null) {
            paragraf = new ArrayList<Paragraf>();
        }
        return this.paragraf;
    }

}
