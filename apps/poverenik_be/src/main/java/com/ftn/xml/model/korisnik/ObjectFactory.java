//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.17 at 06:44:51 PM CET 
//


package com.ftn.xml.model.korisnik;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn.xml.model.user package. 
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

    private final static QName _Lozinka_QNAME = new QName("http://ftn.uns.ac.rs/korisnik", "lozinka");
    private final static QName _Uloga_QNAME = new QName("http://ftn.uns.ac.rs/korisnik", "uloga");
    private final static QName _ImeIPrezime_QNAME = new QName("http://ftn.uns.ac.rs/korisnik", "ime_i_prezime");
    private final static QName _Email_QNAME = new QName("http://ftn.uns.ac.rs/korisnik", "email");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftn.xml.model.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListaKorisnika }
     * 
     */
    public ListaKorisnika createListaKorisnika() {
        return new ListaKorisnika();
    }

    /**
     * Create an instance of {@link Korisnik }
     * 
     */
    public Korisnik createKorisnik() {
        return new Korisnik();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/korisnik", name = "lozinka")
    public JAXBElement<String> createLozinka(String value) {
        return new JAXBElement<String>(_Lozinka_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/korisnik", name = "uloga")
    public JAXBElement<String> createUloga(String value) {
        return new JAXBElement<String>(_Uloga_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/korisnik", name = "ime_i_prezime")
    public JAXBElement<String> createImeIPrezime(String value) {
        return new JAXBElement<String>(_ImeIPrezime_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/korisnik", name = "email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

}
