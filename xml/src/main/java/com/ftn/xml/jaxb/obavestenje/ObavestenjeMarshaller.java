package com.ftn.xml.jaxb.obavestenje;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ftn.jaxb.util.MyValidationEventHandler;
import com.ftn.jaxb.util.NSPrefixMapper;

public class ObavestenjeMarshaller {

	public void test() throws Exception {
		try {

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.obavestenje");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/obavestenje.xsd"));

			Obavestenje obavestenje = (Obavestenje) unmarshaller.unmarshal(new File("./data/obavestenje.xml"));

			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());

			printObavestenje(obavestenje);
			
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			FileOutputStream os = new FileOutputStream(new File("./data/obavestenje_marshall.xml"));
			marshaller.marshal(obavestenje, os);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	private void printObavestenje(Obavestenje o) {
		System.out.println("Obavestenje:");
		printOsnovniPodaci(o.getOsnovniPodaci());
		printSadrzaj(o.getSadrzaj());
		printPodnozje(o.getPodnozje());
	}
	
	

	private void printSadrzaj(Sadrzaj s) {
		System.out.println("\tSadrzaj:");
		System.out.println("\t\tNaslov: " + s.getNaslov());
		printTeloObavestenja(s.getTeloObavestenja());
	}

	private void printTeloObavestenja(TeloObavestenja t) {
		System.out.println("\t\tTelo obavestenja: ");
		for(Paragraf p : t.getParagraf()) {
			System.out.println("\t\t\tParagraf[" + p.getId() + "]");
			printParagraf(p.getContent());
		}
	}
	
	private void printSluzbeniGlasnik(SluzbeniGlasnik g) {
		System.out.println("\t\t\t\tSluzbeni glasnik broj: " + g.getBroj());
	}

	private void printParagraf(List<Object> content) {
		for(Object o : content) {
			if(o instanceof Zakon) {
				
			} else if(o instanceof Adresa) {
				Adresa a = (Adresa)o;
				System.out.println("\t\t\t\tAdresa: ");
				System.out.println("\t\t\t\t\tGrad: " + a.getGrad());
				System.out.println("\t\t\t\t\tUlica: " + a.getUlica());
				System.out.println("\t\t\t\t\tBroj: " + a.getBroj());
			} else if(o instanceof SluzbeniGlasnik) {
				SluzbeniGlasnik s = (SluzbeniGlasnik) o;
				printSluzbeniGlasnik(s);
			} else if(o instanceof ZiroRacun) {
				ZiroRacun z = (ZiroRacun) o;
				System.out.println("\t\t\t\tZiro racun:");
				System.out.println("\t\t\t\t\tVrednost: " + z.getValue());
				System.out.println("\t\t\t\t\tPoziv na broj: " + z.getPozivNaBroj());
			} else if(o instanceof String) {
				System.out.println("\t\t\t\t" + o);
			} else {
				JAXBElement el = (JAXBElement) o;
				System.out.println("\t\t\t\t" + el.getName());
				System.out.println("\t\t\t\t\t" + el.getValue());
			}
		}
	}

	private void printPodnozje(Podnozje p) {
		System.out.println("\tPodnozje:");
		System.out.println("\t\tDostavljeno:");
		for(Lice l : p.getDostavljeno().getLice()) {
			System.out.println("\t\t\tLice[" + l.getId() + "]: " + l.getValue());
		}
		System.out.println("\t\tPotpis i pecat" + p.getPotpisOvlascenogLica());
	}

	private void printOsnovniPodaci(OsnovniPodaci osnovniPodaci) {
		System.out.println("\tOsnovni podaci:");
		printPodaciOOrganu(osnovniPodaci.getPodaciOOrganu());
		printPodaciOPodnosiocu(osnovniPodaci.getPodaciOPodnosiocu());
	}

	private void printPodaciOPodnosiocu(PodaciOPodnosiocu p) {
		System.out.println("\t\tPodaci o podnosiocu:");
		System.out.println("\t\t\tIme i prezime: " + p.getImeIPrezime());
		System.out.println("\t\t\tNaziv: " + p.getNaziv());
		System.out.println("\t\t\tAdresa: " + p.getAdresa());
	}

	private void printPodaciOOrganu(PodaciOOrganu p) {
		System.out.println("\t\tPodaci o organu:");
		System.out.println("\t\t\tNaziv: " + p.getNaziv());
		System.out.println("\t\t\tSediste: " + p.getSediste());
		System.out.println("\t\t\tBroj predmeta: " + p.getBrojPredmeta());
		System.out.println("\t\t\tDatum: " + p.getDatum());
	}

	public static void main(String[] args) throws Exception {
		ObavestenjeMarshaller o = new ObavestenjeMarshaller();
		o.test();

	}

}
