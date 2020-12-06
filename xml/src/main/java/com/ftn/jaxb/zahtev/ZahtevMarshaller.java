package com.ftn.jaxb.zahtev;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ftn.jaxb.util.MyValidationEventHandler;

public class ZahtevMarshaller {

	public void test() throws Exception {
		try {

			System.out.println("[INFO] Zahtev: JAXB unmarshalling/marshalling.\n");

			JAXBContext context = JAXBContext.newInstance("com.ftn.jaxb.zahtev");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("./data/zahtev.xml"));
			//Izmena nad OM
			zahtev.getSadrzajZahteva().setNaslov("NOVI NASLOV ZAHTEVA");
			
			// XML schema validacija
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zahtev.xsd"));
            
			// Podesavanje unmarshaller-a za XML schema validaciju
			unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());
			
			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(zahtev, System.out);

			printZahtev(zahtev);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void printZahtev(Zahtev zahtev) {
		printPodaciOOrganu(zahtev.getPodaciOOrganu());
		printSadrzajZahteva(zahtev.getSadrzajZahteva());
		printPodnozje(zahtev.getPodnozje());
	}

	private void printPodnozje(Podnozje podnozje) {
		System.out.println("Podnozje:");
		printMestoIDatum(podnozje.getMestoIDatum());
		printInformacijeOTraziocu(podnozje.getInformacijeOTraziocu());
	}

	private void printMestoIDatum(MestoIDatum mestoIDatum) {
		System.out.println("-Mesto i Datum:");
		System.out.println("--Mesto: " + mestoIDatum.getMesto());
		System.out.println("--Datum: " + mestoIDatum.getDatum());

	}

	private void printInformacijeOTraziocu(InformacijeOTraziocu info) {
		System.out.println("-Informacije o traziocu:");
		System.out.println("--Ime i prezime: " + info.getImeIPrezime());
		System.out.println("--Adresa:");
		System.out.println("--- " + info.getAdresa().getUlica() + " " + info.getAdresa().getBroj().getBr() + ", "
				+ info.getAdresa().getMesto());
		System.out.println("--Kontakt: " + info.getKontakt());
		System.out.println("--Potpis: " + info.getPotpis());

	}

	private void printSadrzajZahteva(SadrzajZahteva sadrzaj) {
		System.out.println("Sadrzaj zahteva:");
		System.out.println("-Naslov: " + sadrzaj.getNaslov());
		printParagraf(sadrzaj.getParagraf());

	}

	private void printParagraf(Paragraf paragraf) {
		System.out.println("-Paragraf[ id=" + paragraf.getId() + ", fusnota=" + paragraf.getFusnota() + "]");
		// System.out.println();
	}

	private void printPodaciOOrganu(PodaciOOrganu podaci) {
		System.out.println("Podaci o organu:");
		System.out.println("-Naziv: " + podaci.getNaziv());
		System.out.println("-Sediste: " + podaci.getSediste());

	}

	public static void main(String[] args) throws Exception {
		ZahtevMarshaller test = new ZahtevMarshaller();
		test.test();

	}

}
