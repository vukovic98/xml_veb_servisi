
package com.ftn.xml.jaxb.zalba_na_odluku;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}datum"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}naslov"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}adresa_za_postu"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}broj_input"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}naziv_organa"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}obrazlozenje_zalbe"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}podnosilac_zalbe"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}primalac"/&gt;
 *         &lt;element ref="{http://ftn.uns.ac.rs/zalba_na_odluku}zakon"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "content"
})
@XmlRootElement(name = "paragraf", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku")
public class Paragraf {

    @XmlElementRefs({
        @XmlElementRef(name = "datum", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "naslov", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "adresa_za_postu", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = AdresaZaPostu.class, required = false),
        @XmlElementRef(name = "broj_input", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "naziv_organa", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "obrazlozenje_zalbe", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "podnosilac_zalbe", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = PodnosilacZalbe.class, required = false),
        @XmlElementRef(name = "primalac", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "zakon", namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", type = Zakon.class, required = false)
    })
    @XmlMixed
    protected List<Object> content;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link AdresaZaPostu }
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link PodnosilacZalbe }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link Zakon }
     * {@link String }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
