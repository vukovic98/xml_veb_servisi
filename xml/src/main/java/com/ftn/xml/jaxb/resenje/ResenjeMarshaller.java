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

import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.NSPrefixMapper;

public class ResenjeMarshaller {

	public static String output = "";

	public static String test() throws Exception {
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
		return output;

	}

	private static void printResenje(ResenjeObrazac resenje) {
		output +="Resenje " + resenje.getBroj() + ": \n";

		printOsnovniPodaci(resenje.getOsnovniPodaci());
		printSadrzaj(resenje.getSadrzaj());
		printPoverenik(resenje.getPoverenik());

	}

	private static void printSadrzaj(Sadrzaj sadrzaj) {
		printSadrzajUvod(sadrzaj.getUvod());
		printSadrzajResenje(sadrzaj.getResenje());
		printSadrzajObrazlozenje(sadrzaj.getObrazlozenje());
	}

	private static void printSadrzajObrazlozenje(Obrazlozenje o) {
		output +="\tObrazlozenje:\n";
		for (Object s : o.getContent()) {
			if (s instanceof String) {
				output +=s;
				output += "\n";
			} else {
				JAXBElement el = (JAXBElement) s;
				output +="\t\t\t" + el.getName()+ "\n";
				output +="\t\t\t\t" + el.getValue()+ "\n";
			}
		}
	}

	private static void printSadrzajResenje(Resenje resenje) {
		output +="\tResenje: \n";
		for (Object s : resenje.getContent()) {
			if (s instanceof Ustanova) {
				Ustanova us = (Ustanova) s;
				output +="\t\t\tUstanova:\n";
				output +="\t\t\t\t" + us.getNaziv()+ "\n";
				output +="\t\t\t\t" + us.getUlica()+ "\n";
			} else if (s instanceof String) {
				output +=s;
				output += "\n";
			} else {

				JAXBElement el = (JAXBElement) s;
				output +="\t\t\t" + el.getName()+ "\n";
				output +="\t\t\t\t" + el.getValue()+ "\n";
			}
		}
	}

	private static void printSadrzajUvod(Uvod uvod) {
		output +="\t\tUvod:\n";

		for (Object s : uvod.getContent()) {
			if (s instanceof Ustanova) {
				Ustanova us = (Ustanova) s;
				output +="\t\t\tUstanova:\n";
				output +="\t\t\t\t" + us.getNaziv()+ "\n";
				output +="\t\t\t\t" + us.getUlica()+ "\n";
			} else if (s instanceof Zakon) {
				Zakon z = (Zakon) s;
				printZakon(z);
			} else if (s instanceof String) {
				output +=s;
				output += "\n";
			} else {
				JAXBElement el = (JAXBElement) s;
				output +="\t\t\t" + el.getName()+ "\n";
				output +="\t\t\t\t" + el.getValue()+ "\n";
			}
		}
	}

	private static void printZakon(Zakon z) {
		output +="\t\t\tZakon:\n";
		output +="\t\t\t\t" + z.getNaziv()+ "\n";
		output +="\t\t\t\t\tSekcije:\n";
		for (SekcijaZakona s : z.getSekcijaZakona()) {
			output +="\t\t\t\t\t\tStav: " + s.getStav()+ "\n";
			output +="\t\t\t\t\t\tTacka: " + s.getTacka()+ "\n";
		}
		printSluzbeniGlasnik(z.getSluzbeniGlasnik());
	}

	private static void printSluzbeniGlasnik(SluzbeniGlasnik g) {
		output += "\t\t\t\tSluzbeni glasnik broj: " + g.getBroj()+ "\n";
	}

	private static void printPoverenik(String poverenik) {
		output += "\tPoverenik: \n";
		output += "\t\t" + poverenik+ "\n";
	}

	private static void printOsnovniPodaci(OsnovniPodaci osnovniPodaci) {
		output +="\tOsnovni podaci:\n";
		for (Serializable s : osnovniPodaci.getContent()) {
			if (s instanceof String) {
				output +=s;
				output += "\n";
			} else {
				JAXBElement el = (JAXBElement) s;
				output +="\t\t" + el.getName()+ "\n";
				output +="\t\t\t" + el.getValue()+ "\n";
			}
		}
	}

}
