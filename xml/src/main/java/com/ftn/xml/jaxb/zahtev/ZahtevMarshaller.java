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

	public void test() throws Exception {
		try {

			System.out.println("[INFO] Zahtev: JAXB unmarshalling/marshalling.\n");

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zahtev");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			// XML schema validacija
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zahtev.xsd"));
			
			Zahtev zahtev = (Zahtev) unmarshaller.unmarshal(new File("./data/zahtev.xml"));
			
			// Izmena nad OM
			//zahtev.getSadrzajZahteva().setNaslov("NOVI NASLOV ZAHTEVA");

			// Podesavanje unmarshaller-a za XML schema validaciju
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());
			
			printZahtev(zahtev);
			
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			FileOutputStream os = new FileOutputStream(new File("./data/zahtev_marshall.xml"));

			marshaller.marshal(zahtev, System.out);
			marshaller.marshal(zahtev, os);

			

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
		System.out.println("\tMesto i Datum:");
		System.out.println("\t\tMesto: " + mestoIDatum.getMesto());
		System.out.println("\t\tDatum: " + mestoIDatum.getDatum());

	}

	private void printInformacijeOTraziocu(InformacijeOTraziocu info) {
		System.out.println("\tInformacije o traziocu:");
		System.out.println("\t\tIme i prezime: " + info.getImeIPrezime());
		System.out.println("\t\tAdresa:");
		System.out.println("\t\t\t" + info.getAdresa().getUlica() + " " + info.getAdresa().getBrojUlice() + ", "
				+ info.getAdresa().getMesto());
		System.out.println("\t\tKontakt: " + info.getKontakt());
		System.out.println("\t\tPotpis: " + info.getPotpis());

	}

	private void printSadrzajZahteva(SadrzajZahteva sadrzaj) {
		System.out.println("Sadrzaj zahteva:");
		System.out.println("\tNaslov: " + sadrzaj.getNaslov());
		printParagraf(sadrzaj.getParagraf());

	}

	private void printParagraf(Paragraf paragraf) {
		System.out.println("\tParagraf[ id=" + paragraf.getId() + ", fusnota=" + paragraf.getFusnota() + "]");

		for (Object s : paragraf.getContent()) {
			if (s instanceof String) {
				System.out.println(s);

			} else if (s instanceof SluzbeniGlasnik) {
				SluzbeniGlasnik sg = (SluzbeniGlasnik) s;
				System.out.println("\t\tSluzbeni glasnik:");

				System.out.println("\t\t\tBrojevi:");
				for (String b : sg.getBrojevi().getBroj()) {
					System.out.println("\t\t\t\t"+b);
				}

			} else if (s instanceof Stavke) {
				printStavke((Stavke) s);

			} else if (s instanceof Zakon) {
				Zakon zakon = (Zakon) s;
				System.out.println("\t\tZakon:");
				System.out.println("\t\t\tNaziv: " + zakon.getNaziv());
				System.out.println("\t\t\tStav: " + zakon.getStav());

			} else {
				JAXBElement el = (JAXBElement) s;
				//System.out.println("\t\t" + el.getName().getLocalPart());
				System.out.println("\t\tOpis trazene informacije:");
				if (!el.getValue().toString().equals(""))
					System.out.println("\t\t\t\t" + el.getValue());

			}
		}
	}

	private void printStavke(Stavke s) {
		System.out.println("\t\tStavke:");
		Stavke stavke = (Stavke) s;
		for (Object o : stavke.getContent()) {
			if (o instanceof Stavka) {
				Stavka st = (Stavka) o;
				printStavka(st);

			}
		}
	}

	private void printStavka(Stavka st) {
		if(st.isOtkaceno())
			System.out.println("\t\t\tStavka[*] : " + st.getZahtev());
		else
			System.out.println("\t\t\tStavka[ ] : " + st.getZahtev());
		Object p = st.getPodstavke();

		if (p != null) {
			printPodstavke((Podstavke) p);
		}

	}

	private void printPodstavke(Podstavke p) {
		List<Podstavka> podstavke = p.getPodstavka();
		System.out.println("\t\t\t\tPodstavke:");
		for (Podstavka ps : podstavke) {
			if(ps.isOtkaceno())
				System.out.println("\t\t\t\t\tPodstavka[*] : " + ps.getNacin());
			else
				System.out.println("\t\t\t\t\tPodstavka[ ] : " + ps.getNacin());

			Object drugiNacin = ps.getDrugiNacin();
			if (drugiNacin != null) {
				System.out.println("\t\t\t\t\t\tDrugi nacin:");
				System.out.println("\t\t\t\t\t\t\t"+ps.getDrugiNacin());
			}
				
		}

	}

	private void printPodaciOOrganu(PodaciOOrganu podaci) {
		System.out.println("Podaci o organu:");
		System.out.println("\tNaziv: " + podaci.getNaziv());
		System.out.println("\tSediste: " + podaci.getSediste());

	}

	public static void main(String[] args) throws Exception {
		ZahtevMarshaller test = new ZahtevMarshaller();
		test.test();

	}

}
