//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.21 at 07:35:32 PM CET 
//

package com.ftn.xml.model.zalba_cutanje;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>integer">
 *       &lt;attribute name="datatype" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="property" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "value" })
@XmlRootElement(name = "broj_zahteva")
public class BrojZahteva {

	@XmlValue
	protected BigInteger value;
	@XmlAttribute(name = "datatype", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String datatype;
	@XmlAttribute(name = "property", required = true)
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlSchemaType(name = "NMTOKEN")
	protected String property;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link BigInteger }
	 * 
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value allowed object is {@link BigInteger }
	 * 
	 */
	public void setValue(BigInteger value) {
		this.value = value;
	}

	/**
	 * Gets the value of the datatype property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDatatype() {
		return datatype;
	}

	/**
	 * Sets the value of the datatype property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setDatatype(String value) {
		this.datatype = value;
	}

	/**
	 * Gets the value of the property property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * Sets the value of the property property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setProperty(String value) {
		this.property = value;
	}

}
