package com.ftn.xml.jaxb.zalba_na_odluku;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.tomcat.jni.Address;

import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.NSPrefixMapper;

public class ZalbaNaOdlukuMarshaller {
	
	public static String output = "";
	
	public static String test() throws Exception {
		
		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zalba_na_odluku");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/zalba_na_odluku.xsd"));
			
			ZalbaNaOdluku zalba = (ZalbaNaOdluku) unmarshaller.unmarshal(new File("./data/zalba_na_odluku.xml"));
			
			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());
			
			printZalbaNaOdluku(zalba);
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());

			FileOutputStream fostream = new FileOutputStream(new File("./data/zalba_na_odluku_marshall.xml"));
			marshaller.marshal(zalba, fostream);
		}catch (JAXBException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return output;
	}

	private static void printZalbaNaOdluku(ZalbaNaOdluku zalba) {
		// TODO Auto-generated method stub
		output += "Zalba na odluku:" + "\n";
		printSadrzajZalbe(zalba.getSadrzajZalbe());
		printPodnozje(zalba.getPodnozje());
	}
	
	private static void printSadrzajZalbe(SadrzajZalbe sadrzajZalbe) {
		// TODO Auto-generated method stub
		output += "\tSadrzaj Zalbe:\n";
		output = output.concat("\tNaslov: " + sadrzajZalbe.getNaslov());
		printParagraf(sadrzajZalbe.getParagraf());
	}
	

	private static void printParagraf(List<Paragraf> paragraf) {
		// TODO Auto-generated method stub
		for(Paragraf p: paragraf) {
			output = output.concat("\t\tParagraf " + p.getId() + "\n");
			
			for(Object o : p.getContent()) {
				if(o.getClass() == Zakon.class) {
					Zakon z = (Zakon)o;
					output = output.concat("\t\t\tZakon: \n");
					output = output.concat("\t\t\tNaziv: "+ z.getNaziv() + "\n");
					output = output.concat("\t\t\tClan: "+ z.getClan() + "\n");
					output = output.concat("\t\t\tStav: "+ z.getStav() + "\n");
						
				}else if(o.getClass() == PodnosilacZalbe.class) {
					PodnosilacZalbe podnosilac = (PodnosilacZalbe) o;
					output = output.concat("\t\t\tPodnosilac zalbe: \n");
					output = output.concat("\t\t\tIme i prezime: " + podnosilac.getPodnosilacNaziv()+ "\n");
					output = output.concat("\t\t\tAdresa:" + podnosilac.getPodnosilacAdresa().getGrad()+ "," +podnosilac.getPodnosilacAdresa().getUlica()+ ","+ podnosilac.getPodnosilacAdresa().getBroj()+ "\n");
					output = output.concat("\t\t\tSediste: " + podnosilac.getPodnosilacSediste()+"\n");
					
				}else if(o.getClass() == JAXBElement.class) {
					JAXBElement e = (JAXBElement) o;
					output = output.concat("\t\t\t" + e.getName()+"\n");
					output = output.concat("\t\t\t" + e.getValue()+"\n");
					
 				} else if(o.getClass() == AdresaZaPostu.class) {
 					AdresaZaPostu e = (AdresaZaPostu) o;
 					output = output.concat("\t\t\tAdresa: " + e.getGrad() + ","+e.getUlica()+"," +e.getBroj()+"\n");
 				}
				else {
 					output = output.concat("\t\t\t"+o+"\n");
 				}
			}
		}
		
		
	}

	

	private static void printPodnozje(Podnozje podnozje) {
		// TODO Auto-generated method stub
		output = output.concat("\tPodnozje: \n");
		printMestoDatumZalbe(podnozje.getMestoIDatum());
		printInformacijeOTraziocu(podnozje.getInformacijeOTraziocu());
		printNapomene(podnozje.getNapomene());
	}

	private static void printNapomene(Napomene napomene) {
		// TODO Auto-generated method stub
		output = output.concat("\t\tNapomene: \n");
		for(Napomena n : napomene.getNapomena()) {
			output = output.concat("\t\t\t" + n.getValue());
			
		}
		
		
	}

	private static void printInformacijeOTraziocu(InformacijeOTraziocu informacijeOTraziocu) {
		// TODO Auto-generated method stub
		output = output.concat("\t\tPodnosilac zalbe: \n");
		//output = output.concat("\t\t\tIme i prezime: "+"\n");
		
		for(int i = 0 ; i<informacijeOTraziocu.getAdresaOrImeOrKontakt().size();i++) {
			if(informacijeOTraziocu.getAdresaOrImeOrKontakt().get(i).getClass() == Adresa.class) {
				Adresa as = (Adresa) informacijeOTraziocu.getAdresaOrImeOrKontakt().get(i);
				output = output.concat("\t\t\t" + as.getGrad()+"\n");
				output = output.concat("\t\t\t" + as.getUlica()+"\n");
				output = output.concat("\t\t\t" + as.getBroj()+"\n");
			} else {
				JAXBElement e = (JAXBElement) informacijeOTraziocu.getAdresaOrImeOrKontakt().get(i);
				output = output.concat("\t\t\t" + e.getName()+"\n");
				output = output.concat("\t\t\t" + e.getValue()+"\n");
			}

		}
		
		for(Object o : informacijeOTraziocu.getAdresaOrImeOrKontakt()) {
			if(o.getClass() == PodnosilacZalbe.class) {
				PodnosilacZalbe pz = (PodnosilacZalbe) o;
				output = output.concat("\t\t\tIme i prezime: "+pz.getPodnosilacNaziv()+"\n");
				output = output.concat("\t\t\tAdresa: " + pz.getPodnosilacAdresa().getGrad() + ","+pz.getPodnosilacAdresa().getUlica()+"," +pz.getPodnosilacAdresa().getBroj()+"\n");
				
			} else if(o.getClass() == Adresa.class) {
				Adresa as = (Adresa) o;
				output = output.concat("\t\t\tAdresa: " + as.getGrad() + ","+as.getUlica()+"," +as.getBroj()+"\n");
				
			} else {
				JAXBElement e = (JAXBElement) o;
				output = output.concat("\t\t\t" + e.getValue()+"\n");
			}
		}

		
		
	}

	private static void printMestoDatumZalbe(MestoIDatum mestoIDatum) {
		// TODO Auto-generated method stub
		output = output.concat("\t\tDatum i mesto zalbe: \n");
		for (Serializable o : mestoIDatum.getContent()) {
					
			if(o.getClass() == JAXBElement.class) {
				JAXBElement elem = (JAXBElement) o;
						
				output = output.concat("\t\t\t"+elem.getValue()+"\n");
			}
			else
				output = output.concat("\t\t\t"+o+"\n");
		}
	}}
