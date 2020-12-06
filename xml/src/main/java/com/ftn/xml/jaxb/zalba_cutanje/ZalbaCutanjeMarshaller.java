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

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ftn.xml.jaxb.util.NSPrefixMapper;
import com.ftn.xml.jaxb.util.MyValidationEventHandler;

public class ZalbaCutanjeMarshaller {

	public static String out = "";
	
	public static String test() throws Exception {
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
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setSchema(schema);
			
			
			marshaller.marshal(zalba, stream);
			
            
		}catch(JAXBException e) {
			e.printStackTrace();
		}
		return out;
		
		
	}
	
	private static void printZalba(ZalbaCutanje zalba) {
		
		out = out.concat("Zalba: \n");
		
		
		
		printZaglavlje(zalba.getZaglavlje());
		printSadrzaj(zalba.getSadrzaj());
		printPodnozje(zalba.getPodnozje());
		
		System.out.println(out);
		
	}
	
	private static void printZaglavlje(Zaglavlje zaglavlje) {
		
		out.concat("\tZaglavlje: \n");
		
		out.concat("\tNaslov: "+zaglavlje.getNaslov());
		printPrimalac(zaglavlje.getPrimalacZalbe());
	}
	
	private static void printPrimalac(PrimalacZalbe primalac) {
		
		out.concat("\t\tPrimalac: \n");
		out.concat("\t\t\tNaziv primaoca: \n"+primalac.getNazivPrimaoca());
		out.concat("\t\t\tAdresa primaoca: \n"+primalac.getAdresaZaPostu());
		
	}
	
	private static void printSadrzaj(Sadrzaj sadrzaj) {
		
		out.concat("\tSadrzaj: \n");
		
		for (Paragraf p : sadrzaj.getParagraf()) {
			printParagraf(p);
		}
		
	}
	
	private static void printParagraf(Paragraf paragraf) {
		
		out.concat("\t\tParagraf "+paragraf.getId()+"\n");
		
		for (Object o : paragraf.getContent()) {
			if(o.getClass() == Zakon.class) {
				Zakon z = (Zakon)o;
				
				out.concat("\t\t\tZakon: \n");
				out.concat("\t\t\tClan: "+z.getClan()+"\n");
				out.concat("\t\t\tNaziv: "+z.getNaziv()+"\n");
			}else if(o.getClass() == RazlogZalbe.class) {
				
				RazlogZalbe rz = (RazlogZalbe)o;
				out.concat("\t\t\tRazlog zalbe: "+"\n");
				
				if(rz.getNijePostupio()!=null)
					out.concat(rz.getNijePostupio().toString());
				if(rz.getNijePostupioUCelosti()!=null)
					out.concat(rz.getNijePostupioUCelosti().toString());
				if(rz.getNijePostupioURoku()!=null)
					out.concat(rz.getNijePostupioURoku().toString());
				
			}else {

				if(o.getClass() == JAXBElement.class) {
					JAXBElement elem = (JAXBElement) o;
					
					String[] name = elem.getName().toString().split("}");
					String ispis = StringUtils.capitalize(name[1]);
					
					out.concat("\t\t\t"+ispis+":\n");
					
					
					out.concat("\t\t\t"+elem.getValue()+"\n");
				}
				else {
					out.concat("\t\t\t"+o+"\n");
				}
				
			}
		}
		
	}
	
	private static void printPodnozje(Podnozje podnozje) {
		
		out.concat("\tPodnozje: \n");
		printDatumMesto(podnozje.getDatumIMestoZalbe());
		printPodnosilacZalbe(podnozje.getPodnosilacZalbe());
	}
	
	private static void printDatumMesto(DatumIMestoZalbe datumMesto) {
		
		out.concat("\t\tDatum i mesto zalbe: \n");
		for (Serializable o : datumMesto.getContent()) {
			
			if(o.getClass() == JAXBElement.class) {
				JAXBElement elem = (JAXBElement) o;
				
				out.concat("\t\t\t"+elem.getValue()+"\n");
			}
			else
				out.concat("\t\t\t"+o+"\n");
		}
		
	}
	
	private static void printPodnosilacZalbe(PodnosilacZalbe podnosilac) {
		
		out.concat("\t\tPodnosilac zalbe: \n");
		
		out.concat("\t\t\tIme i prezime: "+podnosilac.getImeIPrezime()+"\n");
		
		for (Object o : podnosilac.getAdresaPodnosiocaOrDrugiPodaciZaKontaktOrPotpis()) {
			
			if(o.getClass() == Potpis.class)
				out.concat("\t\t\tPotpis: \n");
			else {
				JAXBElement elem = (JAXBElement) o;
				out.concat("\t\t\t"+elem.getValue()+"\n");
			}
		}
		
	}
	
	
}
