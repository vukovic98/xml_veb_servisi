//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.01.17 at 07:02:09 PM CET 
//

package com.ftn.xml.model.obavestenje;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.ftn.xml.model.obavestenje package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _Broj_QNAME = new QName("http://www.w3.org/ns/obavestenje", "broj");
	private final static QName _SatOd_QNAME = new QName("http://www.w3.org/ns/obavestenje", "sat_od");
	private final static QName _DatumUvida_QNAME = new QName("http://www.w3.org/ns/obavestenje", "datum_uvida");
	private final static QName _Ulica_QNAME = new QName("http://www.w3.org/ns/obavestenje", "ulica");
	private final static QName _CasUvida_QNAME = new QName("http://www.w3.org/ns/obavestenje", "cas_uvida");
	private final static QName _Grad_QNAME = new QName("http://www.w3.org/ns/obavestenje", "grad");
	private final static QName _SatDo_QNAME = new QName("http://www.w3.org/ns/obavestenje", "sat_do");
	private final static QName _Cena_QNAME = new QName("http://www.w3.org/ns/obavestenje", "cena");
	private final static QName _BrojKancelarije_QNAME = new QName("http://www.w3.org/ns/obavestenje",
			"broj_kancelarije");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of schema
	 * derived classes for package: com.ftn.xml.model.obavestenje
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link PotpisOvlascenogLica }
	 * 
	 */
	public PotpisOvlascenogLica createPotpisOvlascenogLica() {
		return new PotpisOvlascenogLica();
	}

	/**
	 * Create an instance of {@link Obavestenje }
	 * 
	 */
	public Obavestenje createObavestenje() {
		return new Obavestenje();
	}

	/**
	 * Create an instance of {@link OsnovniPodaci }
	 * 
	 */
	public OsnovniPodaci createOsnovniPodaci() {
		return new OsnovniPodaci();
	}

	/**
	 * Create an instance of {@link PodaciOOrganu }
	 * 
	 */
	public PodaciOOrganu createPodaciOOrganu() {
		return new PodaciOOrganu();
	}

	/**
	 * Create an instance of {@link Naziv }
	 * 
	 */
	public Naziv createNaziv() {
		return new Naziv();
	}

	/**
	 * Create an instance of {@link Sediste }
	 * 
	 */
	public Sediste createSediste() {
		return new Sediste();
	}

	/**
	 * Create an instance of {@link BrojPredmeta }
	 * 
	 */
	public BrojPredmeta createBrojPredmeta() {
		return new BrojPredmeta();
	}

	/**
	 * Create an instance of {@link DatumZahteva }
	 * 
	 */
	public DatumZahteva createDatumZahteva() {
		return new DatumZahteva();
	}

	/**
	 * Create an instance of {@link PodaciOPodnosiocu }
	 * 
	 */
	public PodaciOPodnosiocu createPodaciOPodnosiocu() {
		return new PodaciOPodnosiocu();
	}

	/**
	 * Create an instance of {@link ImeIPrezime }
	 * 
	 */
	public ImeIPrezime createImeIPrezime() {
		return new ImeIPrezime();
	}

	/**
	 * Create an instance of {@link Adresa }
	 * 
	 */
	public Adresa createAdresa() {
		return new Adresa();
	}

	/**
	 * Create an instance of {@link Sadrzaj }
	 * 
	 */
	public Sadrzaj createSadrzaj() {
		return new Sadrzaj();
	}

	/**
	 * Create an instance of {@link GodinaZahteva }
	 * 
	 */
	public GodinaZahteva createGodinaZahteva() {
		return new GodinaZahteva();
	}

	/**
	 * Create an instance of {@link OpisTrazeneInformacije }
	 * 
	 */
	public OpisTrazeneInformacije createOpisTrazeneInformacije() {
		return new OpisTrazeneInformacije();
	}

	/**
	 * Create an instance of {@link ZiroRacun }
	 * 
	 */
	public ZiroRacun createZiroRacun() {
		return new ZiroRacun();
	}

	/**
	 * Create an instance of {@link Podnozje }
	 * 
	 */
	public Podnozje createPodnozje() {
		return new Podnozje();
	}

	/**
	 * Create an instance of {@link BrojZahteva }
	 * 
	 */
	public BrojZahteva createBrojZahteva() {
		return new BrojZahteva();
	}

	/**
	 * Create an instance of {@link ListaObavestenja }
	 * 
	 */
	public ListaObavestenja createListaObavestenja() {
		return new ListaObavestenja();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "broj")
	public JAXBElement<BigInteger> createBroj(BigInteger value) {
		return new JAXBElement<BigInteger>(_Broj_QNAME, BigInteger.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "sat_od")
	public JAXBElement<XMLGregorianCalendar> createSatOd(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_SatOd_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "datum_uvida")
	public JAXBElement<XMLGregorianCalendar> createDatumUvida(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_DatumUvida_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "ulica")
	public JAXBElement<String> createUlica(String value) {
		return new JAXBElement<String>(_Ulica_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "cas_uvida")
	public JAXBElement<XMLGregorianCalendar> createCasUvida(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_CasUvida_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "grad")
	public JAXBElement<String> createGrad(String value) {
		return new JAXBElement<String>(_Grad_QNAME, String.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement
	 * }{@code <}{@link XMLGregorianCalendar }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "sat_do")
	public JAXBElement<XMLGregorianCalendar> createSatDo(XMLGregorianCalendar value) {
		return new JAXBElement<XMLGregorianCalendar>(_SatDo_QNAME, XMLGregorianCalendar.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "cena")
	public JAXBElement<BigDecimal> createCena(BigDecimal value) {
		return new JAXBElement<BigDecimal>(_Cena_QNAME, BigDecimal.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://www.w3.org/ns/obavestenje", name = "broj_kancelarije")
	public JAXBElement<BigInteger> createBrojKancelarije(BigInteger value) {
		return new JAXBElement<BigInteger>(_BrojKancelarije_QNAME, BigInteger.class, null, value);
	}

}
