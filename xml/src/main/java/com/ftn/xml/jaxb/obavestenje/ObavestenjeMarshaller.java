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

import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.NSPrefixMapper;

public class ObavestenjeMarshaller {
	
	public static String output = "";

	public static String test() throws Exception {
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
		
		return output;

	}
	
	private static void printObavestenje(Obavestenje o) {
		output +="Obavestenje:" + "\n";
		printOsnovniPodaci(o.getOsnovniPodaci());
		printSadrzaj(o.getSadrzaj());
		printPodnozje(o.getPodnozje());
	}
	
	

	private static void printSadrzaj(Sadrzaj s) {
		output +="\tSadrzaj:" + "\n";
		output +="\t\tNaslov: " + s.getNaslov() + "\n";
		printTeloObavestenja(s.getTeloObavestenja());
	}

	private static void printTeloObavestenja(TeloObavestenja t) {
		output +="\t\tTelo obavestenja: " + "\n";
		for(Paragraf p : t.getParagraf()) {
			output +="\t\t\tParagraf[" + p.getId() + "]" + "\n";
			printParagraf(p.getContent());
		}
	}
	
	private static void printSluzbeniGlasnik(SluzbeniGlasnik g) {
		output +="\t\t\t\tSluzbeni glasnik broj: " + g.getBroj() + "\n";
	}

	private static void printParagraf(List<Object> content) {
		for(Object o : content) {
			if(o instanceof Zakon) {
				
			} else if(o instanceof Adresa) {
				Adresa a = (Adresa)o;
				output +="\t\t\t\tAdresa: " + "\n";
				output +="\t\t\t\t\tGrad: " + a.getGrad() + "\n";
				output +="\t\t\t\t\tUlica: " + a.getUlica() + "\n";
				output +="\t\t\t\t\tBroj: " + a.getBroj() + "\n";
			} else if(o instanceof SluzbeniGlasnik) {
				SluzbeniGlasnik s = (SluzbeniGlasnik) o;
				printSluzbeniGlasnik(s);
			} else if(o instanceof ZiroRacun) {
				ZiroRacun z = (ZiroRacun) o;
				output +="\t\t\t\tZiro racun:" + "\n";
				output +="\t\t\t\t\tVrednost: " + z.getValue() + "\n";
				output +="\t\t\t\t\tPoziv na broj: " + z.getPozivNaBroj() + "\n";
			} else if(o instanceof String) {
				output +="\t\t\t\t" + o + "\n";
			} else {
				JAXBElement el = (JAXBElement) o;
				output +="\t\t\t\t" + el.getName() + "\n";
				output +="\t\t\t\t\t" + el.getValue() + "\n";
			}
		}
	}

	private static void printPodnozje(Podnozje p) {
		output +="\tPodnozje:\n";
		output +="\t\tDostavljeno:\n";
		for(Lice l : p.getDostavljeno().getLice()) {
			output +="\t\t\tLice[" + l.getId() + "]: " + l.getValue() + "\n";
		}
		output +="\t\tPotpis i pecat" + p.getPotpisOvlascenogLica() + "\n";
	}

	private static void printOsnovniPodaci(OsnovniPodaci osnovniPodaci) {
		output +="\tOsnovni podaci:\n";
		printPodaciOOrganu(osnovniPodaci.getPodaciOOrganu());
		printPodaciOPodnosiocu(osnovniPodaci.getPodaciOPodnosiocu());
	}

	private static void printPodaciOPodnosiocu(PodaciOPodnosiocu p) {
		output +="\t\tPodaci o podnosiocu:" + "\n";
		output +="\t\t\tIme i prezime: " + p.getImeIPrezime() + "\n";
		output +="\t\t\tNaziv: " + p.getNaziv() + "\n";
		output +="\t\t\tAdresa: " + p.getAdresa() + "\n";
	}

	private static void printPodaciOOrganu(PodaciOOrganu p) {
		output +="\t\tPodaci o organu:" + "\n";
		output +="\t\t\tNaziv: " + p.getNaziv() + "\n";
		output +="\t\t\tSediste: " + p.getSediste() + "\n";
		output +="\t\t\tBroj predmeta: " + p.getBrojPredmeta() + "\n";
		output +="\t\t\tDatum: " + p.getDatum() + "\n";
	}
}
