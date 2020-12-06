package com.ftn.xml.jaxb.resenje;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


import com.ftn.jaxb.util.NSPrefixMapper;
import com.ftn.xml.jaxb.util.MyValidationEventHandler;


public class ResenjeMarshaller {

	public void test() throws Exception {
		try {

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.resenje");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/resenje.xsd"));

			ResenjeObrazac resenje = (ResenjeObrazac) unmarshaller.unmarshal(new File("./data/resenje.xml"));

			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());

			printResenje(resenje);
			
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			FileOutputStream os = new FileOutputStream(new File("./data/resenje_marshall.xml"));
			marshaller.marshal(resenje, os);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	private void printResenje(ResenjeObrazac resenje) {
		System.out.println("Resenje " + resenje.getBroj() + ": ");

		printOsnovniPodaci(resenje.getOsnovniPodaci());
		printSadrzaj(resenje.getSadrzaj());
		printPoverenik(resenje.getPoverenik());

	}

	private void printSadrzaj(Sadrzaj sadrzaj) {
		printSadrzajUvod(sadrzaj.getUvod());
		printSadrzajResenje(sadrzaj.getResenje());
		printSadrzajObrazlozenje(sadrzaj.getObrazlozenje());
	}

	private void printSadrzajObrazlozenje(Obrazlozenje o) {
		System.out.println("\tObrazlozenje:");
		for (Object s : o.getContent()) {
			if (s instanceof String) {
				System.out.println(s);
			} else {
				JAXBElement el = (JAXBElement) s;
				System.out.println("\t\t\t" + el.getName());
				System.out.println("\t\t\t\t" + el.getValue());
			}
		}
	}

	private void printSadrzajResenje(Resenje resenje) {
		System.out.println("\tResenje: ");
		for (Object s : resenje.getContent()) {
			if (s instanceof Ustanova) {
				Ustanova us = (Ustanova) s;
				System.out.println("\t\t\tUstanova:");
				System.out.println("\t\t\t\t" + us.getNaziv());
				System.out.println("\t\t\t\t" + us.getUlica());
			} else if (s instanceof String) {
				System.out.println(s);
			} else {

				JAXBElement el = (JAXBElement) s;
				System.out.println("\t\t\t" + el.getName());
				System.out.println("\t\t\t\t" + el.getValue());
			}
		}
	}

	private void printSadrzajUvod(Uvod uvod) {
		System.out.println("\t\tUvod:");

		for (Object s : uvod.getContent()) {
			if (s instanceof Ustanova) {
				Ustanova us = (Ustanova) s;
				System.out.println("\t\t\tUstanova:");
				System.out.println("\t\t\t\t" + us.getNaziv());
				System.out.println("\t\t\t\t" + us.getUlica());
			} else if (s instanceof Zakon) {
				Zakon z = (Zakon) s;
				printZakon(z);
			} else if (s instanceof String) {
				System.out.println(s);
			} else {
				JAXBElement el = (JAXBElement) s;
				System.out.println("\t\t\t" + el.getName());
				System.out.println("\t\t\t\t" + el.getValue());
			}
		}
	}

	private void printZakon(Zakon z) {
		System.out.println("\t\t\tZakon:");
		System.out.println("\t\t\t\t" + z.getNaziv());
		System.out.println("\t\t\t\t\tSekcije:");
		for (SekcijaZakona s : z.getSekcijaZakona()) {
			System.out.println("\t\t\t\t\t\tStav: " + s.getStav());
			System.out.println("\t\t\t\t\t\tTacka: " + s.getTacka());
		}
		printSluzbeniGlasnik(z.getSluzbeniGlasnik());
	}

	private void printSluzbeniGlasnik(SluzbeniGlasnik g) {
		System.out.println("\t\t\t\tSluzbeni glasnik broj: " + g.getBroj());
	}

	private void printPoverenik(String poverenik) {
		System.out.println("\tPoverenik: ");
		System.out.println("\t\t" + poverenik);
	}

	private void printOsnovniPodaci(OsnovniPodaci osnovniPodaci) {
		System.out.println("\tOsnovni podaci:");
		for (Serializable s : osnovniPodaci.getContent()) {
			if (s instanceof String) {
				System.out.println(s);
			} else {
				JAXBElement el = (JAXBElement) s;
				System.out.println("\t\t" + el.getName());
				System.out.println("\t\t\t" + el.getValue());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		ResenjeMarshaller r = new ResenjeMarshaller();

		r.test();
	}
}
