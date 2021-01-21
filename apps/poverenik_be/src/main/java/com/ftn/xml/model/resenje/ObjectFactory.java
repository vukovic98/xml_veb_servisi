//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.17 at 07:05:34 PM CET 
//


package com.ftn.xml.model.resenje;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn.xml.jaxb.resenje package. 
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

    private final static QName _TekstObrazlozenja_QNAME = new QName("http://ftn.uns.ac.rs/resenje", "tekst_obrazlozenja");
    private final static QName _Ulica_QNAME = new QName("http://ftn.uns.ac.rs/resenje", "ulica");
    private final static QName _TekstResenja_QNAME = new QName("http://ftn.uns.ac.rs/resenje", "tekst_resenja");
    private final static QName _Sud_QNAME = new QName("http://ftn.uns.ac.rs/resenje", "sud");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ftn.xml.jaxb.resenje
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Datum }
     * 
     */
    public Datum createDatum() {
        return new Datum();
    }

    /**
     * Create an instance of {@link Uvod }
     * 
     */
    public Uvod createUvod() {
        return new Uvod();
    }

    /**
     * Create an instance of {@link Organ }
     * 
     */
    public Organ createOrgan() {
        return new Organ();
    }

    /**
     * Create an instance of {@link Podnosilac }
     * 
     */
    public Podnosilac createPodnosilac() {
        return new Podnosilac();
    }

    /**
     * Create an instance of {@link Ustanova }
     * 
     */
    public Ustanova createUstanova() {
        return new Ustanova();
    }

    /**
     * Create an instance of {@link Naziv }
     * 
     */
    public Naziv createNaziv() {
        return new Naziv();
    }

    /**
     * Create an instance of {@link DatumZahteva }
     * 
     */
    public DatumZahteva createDatumZahteva() {
        return new DatumZahteva();
    }

    /**
     * Create an instance of {@link TrazeniDokument }
     * 
     */
    public TrazeniDokument createTrazeniDokument() {
        return new TrazeniDokument();
    }

    /**
     * Create an instance of {@link Resenje }
     * 
     */
    public Resenje createResenje() {
        return new Resenje();
    }

    /**
     * Create an instance of {@link OsnovniPodaci }
     * 
     */
    public OsnovniPodaci createOsnovniPodaci() {
        return new OsnovniPodaci();
    }

    /**
     * Create an instance of {@link Naslov }
     * 
     */
    public Naslov createNaslov() {
        return new Naslov();
    }

    /**
     * Create an instance of {@link KorisnikEmail }
     * 
     */
    public KorisnikEmail createKorisnikEmail() {
        return new KorisnikEmail();
    }

    /**
     * Create an instance of {@link Sadrzaj }
     * 
     */
    public Sadrzaj createSadrzaj() {
        return new Sadrzaj();
    }

    /**
     * Create an instance of {@link DonetoResenje }
     * 
     */
    public DonetoResenje createDonetoResenje() {
        return new DonetoResenje();
    }

    /**
     * Create an instance of {@link Obrazlozenje }
     * 
     */
    public Obrazlozenje createObrazlozenje() {
        return new Obrazlozenje();
    }

    /**
     * Create an instance of {@link Taksa }
     * 
     */
    public Taksa createTaksa() {
        return new Taksa();
    }

    /**
     * Create an instance of {@link Poverenik }
     * 
     */
    public Poverenik createPoverenik() {
        return new Poverenik();
    }

    /**
     * Create an instance of {@link BrojZalbe }
     * 
     */
    public BrojZalbe createBrojZalbe() {
        return new BrojZalbe();
    }

    /**
     * Create an instance of {@link Ishod }
     * 
     */
    public Ishod createIshod() {
        return new Ishod();
    }

    /**
     * Create an instance of {@link ListaResenja }
     * 
     */
    public ListaResenja createListaResenja() {
        return new ListaResenja();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/resenje", name = "tekst_obrazlozenja")
    public JAXBElement<String> createTekstObrazlozenja(String value) {
        return new JAXBElement<String>(_TekstObrazlozenja_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/resenje", name = "ulica")
    public JAXBElement<String> createUlica(String value) {
        return new JAXBElement<String>(_Ulica_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/resenje", name = "tekst_resenja")
    public JAXBElement<String> createTekstResenja(String value) {
        return new JAXBElement<String>(_TekstResenja_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/resenje", name = "sud")
    public JAXBElement<String> createSud(String value) {
        return new JAXBElement<String>(_Sud_QNAME, String.class, null, value);
    }

}