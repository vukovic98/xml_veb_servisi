package com.ftn.xml.jaxb.zalba_cutanje;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.springframework.util.StringUtils;

import com.ftn.xml.jaxb.util.MyValidationEventHandler;

public class ZalbaCutanjeMarshaller {

	public void test() throws Exception {
		try {
			
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zalba_cutanje");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zalba_cutanje.xsd"));
						
			ZalbaCutanje zalba = (ZalbaCutanje) unmarshaller.unmarshal(new File("./data/zalba_cutanje.xml"));
			
			unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());
			
			printZalba(zalba);
			
			// Marshaller je objekat zaduÅ¾en za konverziju iz objektnog u XML model
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			
			FileOutputStream stream = new FileOutputStream(new File("./data/zalba_cutanje_marshalled.xml"));
			
			marshaller.setSchema(schema);
			
			marshaller.marshal(zalba, stream);
            
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void printZalba(ZalbaCutanje zalba) {
		
		System.out.println("Zalba: ");
		
		printZaglavlje(zalba.getZaglavlje());
		printSadrzaj(zalba.getSadrzaj());
		printPodnozje(zalba.getPodnozje());
		
	}
	
	private void printZaglavlje(Zaglavlje zaglavlje) {
		
		System.out.println("\tZaglavlje: ");
		
		System.out.println("\tNaslov: "+zaglavlje.getNaslov());
		printPrimalac(zaglavlje.getPrimalacZalbe());
	}
	
	private void printPrimalac(PrimalacZalbe primalac) {
		
		System.out.println("\t\tPrimalac: ");
		System.out.println("\t\t\tNaziv primaoca: "+primalac.getNazivPrimaoca());
		System.out.println("\t\t\tAdresa primaoca: "+primalac.getAdresaZaPostu());
		
	}
	
	private void printSadrzaj(Sadrzaj sadrzaj) {
		
		System.out.println("\tSadrzaj: ");
		
		for (Paragraf p : sadrzaj.getParagraf()) {
			printParagraf(p);
		}
		
	}
	
	private void printParagraf(Paragraf paragraf) {
		
		System.out.println("\t\tParagraf "+paragraf.getId());
		
		for (Object o : paragraf.getContent()) {
			if(o.getClass() == Zakon.class) {
				Zakon z = (Zakon)o;
				
				System.out.println("\t\t\tZakon: ");
				System.out.println("\t\t\tClan: "+z.getClan());
				System.out.println("\t\t\tNaziv: "+z.getNaziv());
			}else if(o.getClass() == RazlogZalbe.class) {
				
				RazlogZalbe rz = (RazlogZalbe)o;
				System.out.println("\t\t\tRazlog zalbe: ");
				
				if(rz.getNijePostupio()!=null)
					System.out.println(rz.getNijePostupio());
				if(rz.getNijePostupioUCelosti()!=null)
					System.out.println(rz.getNijePostupioUCelosti());
				if(rz.getNijePostupioURoku()!=null)
					System.out.println(rz.getNijePostupioURoku());
				
			}else {

				if(o.getClass() == JAXBElement.class) {
					JAXBElement elem = (JAXBElement) o;
					
					String[] name = elem.getName().toString().split("}");
					String ispis = StringUtils.capitalize(name[1]);
					
					System.out.println("\t\t\t"+ispis+":");
					
					
					System.out.println("\t\t\t"+elem.getValue());
				}
				else {
					System.out.println("\t\t\t"+o);
				}
				
			}
		}
		
	}
	
	private void printPodnozje(Podnozje podnozje) {
		
		System.out.println("\tPodnozje: ");
		printDatumMesto(podnozje.getDatumIMestoZalbe());
		printPodnosilacZalbe(podnozje.getPodnosilacZalbe());
	}
	
	private void printDatumMesto(DatumIMestoZalbe datumMesto) {
		
		System.out.println("\t\tDatum i mesto zalbe: ");
		for (Serializable o : datumMesto.getContent()) {
			
			if(o.getClass() == JAXBElement.class) {
				JAXBElement elem = (JAXBElement) o;
				
				System.out.println("\t\t\t"+elem.getValue());
			}
			else
				System.out.println("\t\t\t"+o);
		}
		
	}
	
	private void printPodnosilacZalbe(PodnosilacZalbe podnosilac) {
		
		System.out.println("\t\tPodnosilac zalbe: ");
		
		System.out.println("\t\t\tIme i prezime: "+podnosilac.getImeIPrezime());
		
		for (Object o : podnosilac.getAdresaPodnosiocaOrDrugiPodaciZaKontaktOrPotpis()) {
			
			if(o.getClass() == Potpis.class)
				System.out.println("\t\t\tPotpis: ");
			else {
				JAXBElement elem = (JAXBElement) o;
				System.out.println("\t\t\t"+elem.getValue());
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		ZalbaCutanjeMarshaller test = new ZalbaCutanjeMarshaller();
		test.test();
	}
	
}
