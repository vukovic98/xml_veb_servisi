package com.ftn.jaxb.zalba_cutanje;

import java.io.File;
import java.io.Serializable;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ftn.jaxb.util.MyValidationEventHandler;

public class ZalbaCutanjeMarshaller {

	public void test() throws Exception {
		try {
			
			JAXBContext context = JAXBContext.newInstance("com.ftn.jaxb.zalba_cutanje");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory =  SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zalba_cutanje.xsd"));
						
			ZalbaCutanje zalba = (ZalbaCutanje) unmarshaller.unmarshal(new File("./data/zalba_cutanje.xml"));
			
			unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new MyValidationEventHandler());
			
			printZalba(zalba);
            
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
					toXml((JAXBElement)o );
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
			System.out.println("\t\t\t"+o);
		}
		
	}
	
	private void printPodnosilacZalbe(PodnosilacZalbe podnosilac) {
		
		System.out.println("\t\tPodnosilac zalbe: ");
		
		System.out.println("\t\t\tIme i prezime: "+podnosilac.getImeIPrezime());
		
		for (Object o : podnosilac.getAdresaPodnosiocaOrDrugiPodaciZaKontaktOrPotpis()) {
			
			if(o.getClass() == Potpis.class)
				System.out.println("\t\t\tPotpis: ");
			else
				System.out.println("\t\t\t"+o);
		}
		
	}
	
	public void toXml(JAXBElement element) {
	    try {
	        JAXBContext ctx = JAXBContext.newInstance(element.getValue().getClass());
	        Marshaller marshaller = ctx.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(this, System.out);
	    }
	    catch (Exception
	            e) {
e.printStackTrace(); 
	    }
	}
	
	public static void main(String[] args) throws Exception {
		ZalbaCutanjeMarshaller test = new ZalbaCutanjeMarshaller();
		test.test();
	}
	
}
