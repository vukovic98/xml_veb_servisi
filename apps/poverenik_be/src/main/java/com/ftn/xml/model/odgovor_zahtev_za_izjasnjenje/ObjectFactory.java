//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.02.03 at 04:48:37 PM CET 
//


package com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje package. 
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

    private final static QName _Tip_QNAME = new QName("http://ftn.uns.ac.rs/izjasnjenje/odgovor", "tip");
    private final static QName _IdZalbe_QNAME = new QName("http://ftn.uns.ac.rs/izjasnjenje/odgovor", "id_zalbe");
    private final static QName _Sadrzaj_QNAME = new QName("http://ftn.uns.ac.rs/izjasnjenje/odgovor", "sadrzaj");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OdgovorZahtevZaIzjasnjenje }
     * 
     */
    public OdgovorZahtevZaIzjasnjenje createOdgovorZahtevZaIzjasnjenje() {
        return new OdgovorZahtevZaIzjasnjenje();
    }

    /**
     * Create an instance of {@link OdgovoriZahtevZaIzjasnjenje }
     * 
     */
    public OdgovoriZahtevZaIzjasnjenje createOdgovoriZahtevZaIzjasnjenje() {
        return new OdgovoriZahtevZaIzjasnjenje();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/izjasnjenje/odgovor", name = "tip")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public JAXBElement<String> createTip(String value) {
        return new JAXBElement<String>(_Tip_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/izjasnjenje/odgovor", name = "id_zalbe")
    public JAXBElement<BigInteger> createIdZalbe(BigInteger value) {
        return new JAXBElement<BigInteger>(_IdZalbe_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/izjasnjenje/odgovor", name = "sadrzaj")
    public JAXBElement<String> createSadrzaj(String value) {
        return new JAXBElement<String>(_Sadrzaj_QNAME, String.class, null, value);
    }

}
