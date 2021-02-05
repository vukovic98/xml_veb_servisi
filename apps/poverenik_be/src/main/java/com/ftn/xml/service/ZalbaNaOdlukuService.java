package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.db.MetadataExtractor;
import com.ftn.xml.dto.ZalbaNaOdlukuDTO;
import com.ftn.xml.dto.ZalbaNaOdlukuDodavanjeDTO;
import com.ftn.xml.helper.DodajZalbuNaOdlukuMapper;
import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.XSLFORTransformerZalbaNaOdluku;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
import com.ftn.xml.repository.ResenjeRepository;
import com.ftn.xml.repository.ZalbaNaOdlukuRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZalbaNaOdlukuService {
	
	@Autowired 
	private ZalbaNaOdlukuRepository zalbaRepo;
	
	@Autowired
	private ResenjeRepository resenjeRepo;
	
	@Autowired
	private DodajZalbuNaOdlukuMapper mapper;
	
	public ArrayList<ZalbaNaOdlukuDTO> dobaviSveNereseneZalbe() throws XMLDBException, JAXBException {
		
		ResourceSet resource = this.zalbaRepo.dobaviSve();
		ResourceIterator i = resource.getIterator();
		Resource res = null;
		
		ArrayList<ZalbaNaOdlukuDTO> zalbeDTO = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");
		
		while(i.hasMoreResources()) {
			
			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaNaOdluku z = (ZalbaNaOdluku) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaNaOdlukuDTO dto = new ZalbaNaOdlukuDTO();
				
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);
				
				boolean resena = this.resenjeRepo.proveriDaLiJeZalbaResena(id);
				if(!resena) {
					dto.setRazresena("ne");
					dto.setBroj_zahteva((z.getBrojZahteva().getValue().longValue()));
					
					XMLGregorianCalendar xmlDate = z.getPodnozje().getDatumZakljuckaZalbe().getValue();
					String date = xmlDate.getYear() + "-" + xmlDate.getMonth() + "-" + xmlDate.getDay();
					dto.setDatum(date);

					dto.setIme(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());
					dto.setPrezime(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
					dto.setNaziv_organa(z.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());

					zalbeDTO.add(dto);
				}
			}finally {
				
				try {
					((EXistResource) res).freeResources();	
				}catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
			
		return zalbeDTO;		
	}
	
	public ArrayList<ZalbaNaOdlukuDTO> dobaviSve() throws JAXBException, XMLDBException {
		
		ResourceSet resource = this.zalbaRepo.dobaviSve();
		ResourceIterator i = resource.getIterator();
		Resource res = null;
		ArrayList<ZalbaNaOdlukuDTO> zalbeDTO = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");
		
		while(i.hasMoreResources()) {
			
			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaNaOdluku z = (ZalbaNaOdluku) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaNaOdlukuDTO dto = new ZalbaNaOdlukuDTO();
				
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);
				
				boolean resena = this.resenjeRepo.proveriDaLiJeZalbaResena(id);
				if (resena) 
					dto.setRazresena("da");
				else
					dto.setRazresena("ne");
				
				dto.setBroj_zahteva((z.getBrojZahteva().getValue().longValue()));
				
				XMLGregorianCalendar xmlDate = z.getPodnozje().getDatumZakljuckaZalbe().getValue();
				String date = xmlDate.getYear() + "-" + xmlDate.getMonth() + "-" + xmlDate.getDay();
				dto.setDatum(date);

				dto.setIme(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());
				dto.setPrezime(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
				dto.setNaziv_organa(z.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());

				zalbeDTO.add(dto);
			
			}finally {
				try {
					((EXistResource) res).freeResources();	
				}catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		return zalbeDTO;
	}
	
	public ArrayList<ZalbaNaOdlukuDTO> dobaviSvePoEmailu(String email) throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaRepo.dobaviSvePoEmailu(email);
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ZalbaNaOdlukuDTO> zalbe = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaNaOdluku z = (ZalbaNaOdluku) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaNaOdlukuDTO dto = new ZalbaNaOdlukuDTO();

				// id
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);
				
				boolean resena = this.resenjeRepo.proveriDaLiJeZalbaResena(id);
				if (resena) 
					dto.setRazresena("da");
				else
					dto.setRazresena("ne");
				dto.setBroj_zahteva((z.getBrojZahteva().getValue()).longValue());

				// datum zalbe
				XMLGregorianCalendar xmlDate = z.getPodnozje().getDatumZakljuckaZalbe().getValue();
				String date = xmlDate.getYear() + "-" + xmlDate.getMonth() + "-" + xmlDate.getDay();
				dto.setDatum(date);

				dto.setIme(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());
				dto.setPrezime(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
				dto.setNaziv_organa(z.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());

				zalbe.add(dto);

			} finally {

				// don't forget to cleanup resources
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return zalbe;

	}
	
	public String removeNamespace(String xml) {
		try {
			VTDGen vg = new VTDGen();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			vg.setDoc(xml.getBytes());
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot(vn);
			XMLModifier xm = new XMLModifier(vn);
			ap.selectXPath("//@xmlns");
			int i = 0;
			while ((i = ap.evalXPath()) != -1) {
				xm.remove();
			}
			xm.output(baos);

			return baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String pronadjiZalbuPoId_Raw(long id) throws XMLDBException {
		ResourceSet set = this.zalbaRepo.dobaviPoId(id);
		try {
			if (set.getSize() == 1) {

				Resource res = set.getResource(0);

				String result = (String) res.getContent();

				String finalRes = this.removeNamespace(result);

				return finalRes;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}
	
	public String generisiPDF(long id) throws XMLDBException {
		XSLFORTransformerZalbaNaOdluku transformer = null;

		try {
			transformer = new XSLFORTransformerZalbaNaOdluku();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zalba = this.pronadjiZalbuPoId_Raw(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/zalba_na_odluku_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zalba, pdf_path);
			if (ok)
				return pdf_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generisiHTML(long zalba_id) throws XMLDBException {
		XSLFORTransformerZalbaNaOdluku transformer = null;

		try {
			transformer = new XSLFORTransformerZalbaNaOdluku();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zalba = this.pronadjiZalbuPoId_Raw(zalba_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/zalba_na_odluku_" + zalba_id + ".html";

		try {
			ok = transformer.generateHTML(zalba, html_path);
			if (ok)
				return html_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean dodajZalbu(ZalbaNaOdlukuDodavanjeDTO zalbaDto, Korisnik korisnik) {
		
		String[] podaci = korisnik.getImeIPrezime().split(" ");
		
		ZalbaNaOdluku zalba = this.mapper.dtoUKlasu(zalbaDto, korisnik.getEmail(), podaci[0],podaci[1]);
		boolean ok = this.zalbaRepo.dodajZalbu(zalba);
		if (ok)
			return true;
		else
			return false;

	}
	
	public boolean dodajZalbuIzTeksta(String zalba) throws JAXBException {
		// validacija
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		// XML schema validacija
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(new File("./data/zalba_na_odluku.xsd"));
			unmarshaller.setSchema(schema);
		} catch (SAXException e2) {
			e2.printStackTrace();
			return false;
		}
		// Podesavanje unmarshaller-a za XML schema validaciju

		try {
			unmarshaller.setEventHandler(new MyValidationEventHandler());
		} catch (JAXBException e1) {
			e1.printStackTrace();
			return false;
		}
		StringReader reader = new StringReader(zalba);
		ZalbaNaOdluku z;
		try {
			//System.out.println(zalba);
			z = (ZalbaNaOdluku) unmarshaller.unmarshal(reader);
			this.zalbaRepo.dodajZalbuIzTeksta(zalba, z);
			return true;
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public boolean odustaniOdZalbe(long id) {
		return this.zalbaRepo.odustaniOdZalbe(id);
	}
	
	public void generisiRDF(long id) throws SAXException, IOException {
		String rdfFilePath = "src/main/resources/static/rdf/zalba_na_odluku_" + id + ".rdf";
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		// dobavi zalbu po id i stavi je u xml fajl
		String rs;
		FileWriter fw;
		try {
			rs = this.pronadjiZalbuPoId_Raw(id);
			String pocetak = rs.substring(0, 15);
			String ubaci = "xmlns:obav=\"http://www.ftn.uns.ac.rs/rdf/example\"  xmlns:pred=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" ";
			String kraj = rs.substring(16);
			String novi = pocetak + ubaci + kraj;
			fw = new FileWriter("src/main/resources/static/xml/zalba_na_odluku_"+id+".xml");
			fw.write(novi);
			fw.close(); 
		} catch (XMLDBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			metadataExtractor.extractMetadata(
					new FileInputStream(new File("src/main/resources/static/xml/zalba_na_odluku_"+id+".xml")),
					new FileOutputStream(new File(rdfFilePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void generisiJSON(long id) {
		FusekiManager fm = new FusekiManager();
		try {
			fm.generisiJSONZalbaNaOdluku(id);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ZalbaNaOdlukuDTO> pretraga(String text) throws Exception {
		ResourceSet resource = this.zalbaRepo.pretraga(text);
		ResourceIterator i = resource.getIterator();
		Resource res = null;
		
		ArrayList<ZalbaNaOdlukuDTO> zalbeDTO = new ArrayList<>();
		
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");
		
		while(i.hasMoreResources()) {
			
			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaNaOdluku z = (ZalbaNaOdluku) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaNaOdlukuDTO dto = new ZalbaNaOdlukuDTO();
				
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);
				
				boolean resena = this.resenjeRepo.proveriDaLiJeZalbaResena(id);
				if(!resena) {
					dto.setRazresena("ne");
				} else
					dto.setRazresena("da");
				
				dto.setBroj_zahteva((z.getBrojZahteva().getValue().longValue()));

				XMLGregorianCalendar xmlDate = z.getPodnozje().getDatumZakljuckaZalbe().getValue();
				String date = xmlDate.getYear() + "-" + xmlDate.getMonth() + "-" + xmlDate.getDay();
				dto.setDatum(date);

				dto.setIme(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());
				dto.setPrezime(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
				dto.setNaziv_organa(z.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());

				zalbeDTO.add(dto);
				
			}finally {
				
				try {
					((EXistResource) res).freeResources();	
				}catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
		}
		
		return zalbeDTO;

	}
	
	public ArrayList<ZalbaNaOdluku> naprednaPretraga(String zahtev, String mail, String organ, boolean and) {
		List<String> ids = new ArrayList<>();
		try {
			ids = this.zalbaRepo.naprednaPretraga(zahtev, mail, organ, and);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ids = (ArrayList<String>) ids;
		
		ArrayList<ZalbaNaOdluku> lista = new ArrayList<>();
		
		for(String i : ids) {
			ZalbaNaOdluku z = this.pronadjiZalbuPoId(Long.parseLong(i));
			
			lista.add(z);
		}
		
		return lista;
		
	}
	
	public ZalbaNaOdluku pronadjiZalbuPoId(long id) {
		ResourceSet set = this.zalbaRepo.dobaviPoId(id);
		try {
			if (set.getSize() == 1) {

				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Resource res = set.getResource(0);

				ZalbaNaOdluku zalba = (ZalbaNaOdluku) unmarshaller
						.unmarshal(((XMLResource) res).getContentAsDOM());

				return zalba;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}
}
