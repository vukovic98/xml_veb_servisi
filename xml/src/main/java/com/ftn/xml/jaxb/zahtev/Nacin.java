//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.04 at 04:02:48 PM CET 
//


package com.ftn.xml.jaxb.zahtev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element ref="{http://www.ftn.uns.ac.rs/zahtev}drugi_nacin" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="nacin" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="otkaceno" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "drugiNacin"
})
@XmlRootElement(name = "nacin")
public class Nacin {

    @XmlElement(name = "drugi_nacin")
    protected String drugiNacin;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String id;
    @XmlAttribute(name = "nacin", required = true)
    protected String nacin;
    @XmlAttribute(name = "otkaceno", required = true)
    protected boolean otkaceno;

    /**
     * Gets the value of the drugiNacin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugiNacin() {
        return drugiNacin;
    }

    /**
     * Sets the value of the drugiNacin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugiNacin(String value) {
        this.drugiNacin = value;
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

    /**
     * Gets the value of the nacin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacin() {
        return nacin;
    }

    /**
     * Sets the value of the nacin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacin(String value) {
        this.nacin = value;
    }

    /**
     * Gets the value of the otkaceno property.
     * 
     */
    public boolean isOtkaceno() {
        return otkaceno;
    }

    /**
     * Sets the value of the otkaceno property.
     * 
     */
    public void setOtkaceno(boolean value) {
        this.otkaceno = value;
    }

}