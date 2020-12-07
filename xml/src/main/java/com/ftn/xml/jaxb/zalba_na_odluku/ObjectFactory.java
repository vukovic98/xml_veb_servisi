
package com.ftn.xml.jaxb.zalba_na_odluku;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the rs.ac.uns.ftn.zalba_na_odluku package. 
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

    private final static QName _Naslov_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "naslov");
    private final static QName _Datum_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "datum");
    private final static QName _Grad_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "grad");
    private final static QName _Ulica_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "ulica");
    private final static QName _Broj_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "broj");
    private final static QName _BrojInput_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "broj_input");
    private final static QName _NazivOrgana_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "naziv_organa");
    private final static QName _ObrazlozenjeZalbe_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "obrazlozenje_zalbe");
    private final static QName _PodnosilacNaziv_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "podnosilac_naziv");
    private final static QName _PodnosilacSediste_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "podnosilac_sediste");
    private final static QName _Primalac_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "primalac");
    private final static QName _Mesto_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "mesto");
    private final static QName _Ime_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "ime");
    private final static QName _Kontakt_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "kontakt");
    private final static QName _Potpis_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "potpis");
    private final static QName _Prezime_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "prezime");
    private final static QName _PodnosilacIme_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "podnosilac_ime");
    private final static QName _PodnosilacPrezime_QNAME = new QName("http://ftn.uns.ac.rs/zalba_na_odluku", "podnosilac_prezime");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.zalba_na_odluku
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZalbaNaOdluku }
     * 
     */
    public ZalbaNaOdluku createZalbaNaOdluku() {
        return new ZalbaNaOdluku();
    }

    /**
     * Create an instance of {@link SadrzajZalbe }
     * 
     */
    public SadrzajZalbe createSadrzajZalbe() {
        return new SadrzajZalbe();
    }

    /**
     * Create an instance of {@link Paragraf }
     * 
     */
    public Paragraf createParagraf() {
        return new Paragraf();
    }

    /**
     * Create an instance of {@link AdresaZaPostu }
     * 
     */
    public AdresaZaPostu createAdresaZaPostu() {
        return new AdresaZaPostu();
    }

    /**
     * Create an instance of {@link PodnosilacZalbe }
     * 
     */
    public PodnosilacZalbe createPodnosilacZalbe() {
        return new PodnosilacZalbe();
    }

    /**
     * Create an instance of {@link PodnosilacAdresa }
     * 
     */
    public PodnosilacAdresa createPodnosilacAdresa() {
        return new PodnosilacAdresa();
    }

    /**
     * Create an instance of {@link Zakon }
     * 
     */
    public Zakon createZakon() {
        return new Zakon();
    }

    /**
     * Create an instance of {@link Podnozje }
     * 
     */
    public Podnozje createPodnozje() {
        return new Podnozje();
    }

    /**
     * Create an instance of {@link MestoIDatum }
     * 
     */
    public MestoIDatum createMestoIDatum() {
        return new MestoIDatum();
    }

    /**
     * Create an instance of {@link InformacijeOTraziocu }
     * 
     */
    public InformacijeOTraziocu createInformacijeOTraziocu() {
        return new InformacijeOTraziocu();
    }

    /**
     * Create an instance of {@link Adresa }
     * 
     */
    public Adresa createAdresa() {
        return new Adresa();
    }

    /**
     * Create an instance of {@link Napomene }
     * 
     */
    public Napomene createNapomene() {
        return new Napomene();
    }

    /**
     * Create an instance of {@link Napomena }
     * 
     */
    public Napomena createNapomena() {
        return new Napomena();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "naslov")
    public JAXBElement<String> createNaslov(String value) {
        return new JAXBElement<String>(_Naslov_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "datum")
    public JAXBElement<XMLGregorianCalendar> createDatum(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_Datum_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "grad")
    public JAXBElement<String> createGrad(String value) {
        return new JAXBElement<String>(_Grad_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "ulica")
    public JAXBElement<String> createUlica(String value) {
        return new JAXBElement<String>(_Ulica_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "broj")
    public JAXBElement<BigInteger> createBroj(BigInteger value) {
        return new JAXBElement<BigInteger>(_Broj_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "broj_input")
    public JAXBElement<BigInteger> createBrojInput(BigInteger value) {
        return new JAXBElement<BigInteger>(_BrojInput_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "naziv_organa")
    public JAXBElement<String> createNazivOrgana(String value) {
        return new JAXBElement<String>(_NazivOrgana_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "obrazlozenje_zalbe")
    public JAXBElement<String> createObrazlozenjeZalbe(String value) {
        return new JAXBElement<String>(_ObrazlozenjeZalbe_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "podnosilac_naziv")
    public JAXBElement<String> createPodnosilacNaziv(String value) {
        return new JAXBElement<String>(_PodnosilacNaziv_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "podnosilac_sediste")
    public JAXBElement<String> createPodnosilacSediste(String value) {
        return new JAXBElement<String>(_PodnosilacSediste_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "primalac")
    public JAXBElement<String> createPrimalac(String value) {
        return new JAXBElement<String>(_Primalac_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "mesto")
    public JAXBElement<String> createMesto(String value) {
        return new JAXBElement<String>(_Mesto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "ime")
    public JAXBElement<String> createIme(String value) {
        return new JAXBElement<String>(_Ime_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "kontakt")
    public JAXBElement<String> createKontakt(String value) {
        return new JAXBElement<String>(_Kontakt_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "potpis")
    public JAXBElement<String> createPotpis(String value) {
        return new JAXBElement<String>(_Potpis_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "prezime")
    public JAXBElement<String> createPrezime(String value) {
        return new JAXBElement<String>(_Prezime_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "podnosilac_ime")
    public JAXBElement<String> createPodnosilacIme(String value) {
        return new JAXBElement<String>(_PodnosilacIme_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/zalba_na_odluku", name = "podnosilac_prezime")
    public JAXBElement<String> createPodnosilacPrezime(String value) {
        return new JAXBElement<String>(_PodnosilacPrezime_QNAME, String.class, null, value);
    }

}
