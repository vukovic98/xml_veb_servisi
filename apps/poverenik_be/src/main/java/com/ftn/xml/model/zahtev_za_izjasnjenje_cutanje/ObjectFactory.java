//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.03 at 10:57:31 PM CET 
//


package com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Vreme_QNAME = new QName("http://ftn.uns.ac.rs/zahtev/cutanje", "vreme");
    private final static QName _IdZalbe_QNAME = new QName("http://ftn.uns.ac.rs/zahtev/cutanje", "id_zalbe");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZahtevZaIzjasnjenjeCutanje }
     * 
     */
    public ZahtevZaIzjasnjenjeCutanje createZahtevZaIzjasnjenjeCutanje() {
        return new ZahtevZaIzjasnjenjeCutanje();
    }

    /**
     * Create an instance of {@link ZahteviZaIzjasnjenjeCutanje }
     * 
     */
    public ZahteviZaIzjasnjenjeCutanje createZahteviZaIzjasnjenjeCutanje() {
        return new ZahteviZaIzjasnjenjeCutanje();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zahtev/cutanje", name = "vreme")
    public JAXBElement<String> createVreme(String value) {
        return new JAXBElement<String>(_Vreme_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zahtev/cutanje", name = "id_zalbe")
    public JAXBElement<BigInteger> createIdZalbe(BigInteger value) {
        return new JAXBElement<BigInteger>(_IdZalbe_QNAME, BigInteger.class, null, value);
    }

}