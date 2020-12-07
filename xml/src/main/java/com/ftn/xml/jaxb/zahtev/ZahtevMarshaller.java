package com.ftn.xml.jaxb.zahtev;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.NSPrefixMapper;

public class ZahtevMarshaller {

	public static String output = "";

	public static String test() throws Exception {
		try {

			System.out.println("[INFO] Zahtev: JAXB unmarshalling/marshalling.\n");

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zahtev");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			// XML schema validacija
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zahtev.xsd"));

			Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("./data/zahtev.xml"));

			// Izmena nad OM
			// zahtev.getSadrzajZahteva().setNaslov("NOVI NASLOV ZAHTEVA");

			// Podesavanje unmarshaller-a za XML schema validaciju
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());

			printZahtev(zahtev);

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			FileOutputStream os = new FileOutputStream(new File("./data/zahtev_marshall.xml"));

			//marshaller.marshal(zahtev, System.out);
			marshaller.marshal(zahtev, os);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	public static void printZahtev(Zahtev zahtev) {
		printPodaciOOrganu(zahtev.getPodaciOOrganu());
		printSadrzajZahteva(zahtev.getSadrzajZahteva());
		printPodnozje(zahtev.getPodnozje());
	}

	private static void printPodnozje(Podnozje podnozje) {
		output = output + "Podnozje:\n";
		printMestoIDatum(podnozje.getMestoIDatum());
		printInformacijeOTraziocu(podnozje.getInformacijeOTraziocu());
	}

	private static void printMestoIDatum(MestoIDatum mestoIDatum) {
		output = output + "\tMesto i Datum:\n";
		output = output + "\t\tMesto: " + mestoIDatum.getMesto() + "\n";
		output = output + "\t\tDatum: " + mestoIDatum.getDatum() + "\n";

	}

	private static void printInformacijeOTraziocu(InformacijeOTraziocu info) {
		output = output + "\tInformacije o traziocu:\n";
		output = output + "\t\tIme i prezime: " + info.getImeIPrezime() + "\n";
		output = output + "\t\tAdresa:\n";
		output = output + "\t\t\t" + info.getAdresa().getUlica() + " " + info.getAdresa().getBrojUlice() + ", "
				+ info.getAdresa().getMesto() + "\n";
		output = output + "\t\tKontakt: " + info.getKontakt() + "\n";
		output = output + "\t\tPotpis: " + info.getPotpis() + "\n";

	}

	private static void printSadrzajZahteva(SadrzajZahteva sadrzaj) {
		output = output + "Sadrzaj zahteva:\n";
		output = output + "\tNaslov: " + sadrzaj.getNaslov() + "\n";
		printParagraf(sadrzaj.getParagraf());

	}

	private static void printParagraf(Paragraf paragraf) {
		output = output + "\tParagraf[ id=" + paragraf.getId() + ", fusnota=" + paragraf.getFusnota() + "]\n";

		for (Object s : paragraf.getContent()) {
			if (s instanceof String) {
				output = output + s + "\n";

			} else if (s instanceof SluzbeniGlasnik) {
				SluzbeniGlasnik sg = (SluzbeniGlasnik) s;
				output = output + "\t\tSluzbeni glasnik:\n";

				output = output + "\t\t\tBrojevi:\n";
				for (String b : sg.getBrojevi().getBroj()) {
					output = output + "\t\t\t\t" + b + "\n";
				}

			} else if (s instanceof Stavke) {
				printStavke((Stavke) s);

			} else if (s instanceof Zakon) {
				Zakon zakon = (Zakon) s;
				output = output + "\t\tZakon:\n";
				output = output + "\t\t\tNaziv: " + zakon.getNaziv() + "\n";
				output = output + "\t\t\tStav: " + zakon.getStav() + "\n";

			} else {
				JAXBElement el = (JAXBElement) s;
				// System.out.println("\t\t" + el.getName().getLocalPart());
				output = output + "\t\tOpis trazene informacije:\n";
				if (!el.getValue().toString().equals(""))
					output = output + "\t\t\t\t" + el.getValue() + "\n";

			}
		}
	}

	private static void printStavke(Stavke s) {
		output = output + "\t\tStavke:\n";
		Stavke stavke = (Stavke) s;
		for (Object o : stavke.getContent()) {
			if (o instanceof Stavka) {
				Stavka st = (Stavka) o;
				printStavka(st);

			}
		}
	}

	private static void printStavka(Stavka st) {
		if (st.isOtkaceno())
			output = output + "\t\t\tStavka[*] : " + st.getZahtev() + "\n";
		else
			output = output + "\t\t\tStavka[ ] : " + st.getZahtev() + "\n";
		Object p = st.getPodstavke();

		if (p != null) {
			printPodstavke((Podstavke) p);
		}

	}

	private static void printPodstavke(Podstavke p) {
		List<Podstavka> podstavke = p.getPodstavka();
		output = output + "\t\t\t\tPodstavke:\n";
		for (Podstavka ps : podstavke) {
			if (ps.isOtkaceno())
				output = output + "\t\t\t\t\tPodstavka[*] : " + ps.getNacin() + "\n";
			else
				output = output + "\t\t\t\t\tPodstavka[ ] : " + ps.getNacin() + "\n";

			Object drugiNacin = ps.getDrugiNacin();
			if (drugiNacin != null) {
				output = output + "\t\t\t\t\t\tDrugi nacin:\n";
				output = output + "\t\t\t\t\t\t\t" + ps.getDrugiNacin() + "\n";
			}

		}

	}

	private static void printPodaciOOrganu(PodaciOOrganu podaci) {
		output = output + "Podaci o organu:\n";
		output = output + "\tNaziv: " + podaci.getNaziv() + "\n";
		output = output + "\tSediste: " + podaci.getSediste() + "\n";

	}

}
