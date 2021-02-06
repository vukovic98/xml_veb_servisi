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

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
import com.ftn.xml.dto.ZalbaCutanjeDTO;
import com.ftn.xml.dto.ZalbaCutanjeDodavanjeDTO;
import com.ftn.xml.helper.DodajZalbuCutanjeMapper;
import com.ftn.xml.jaxb.util.MyValidationEventHandler;
import com.ftn.xml.jaxb.util.XSLFORTransformerZalbaCutanje;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.repository.ResenjeRepository;
import com.ftn.xml.repository.ZalbaCutanjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZalbaCutanjeService {

	@Autowired
	ZalbaCutanjeRepository zalbaCutanjeRepository;

	@Autowired
	ResenjeRepository resenjeRepository;

	@Autowired
	DodajZalbuCutanjeMapper mapper;

	public ArrayList<ZalbaCutanjeDTO> dobaviSveNeresene() throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaCutanjeRepository.dobaviSve();
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ZalbaCutanjeDTO> zalbe = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaCutanjeDTO dto = new ZalbaCutanjeDTO();

				// id
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				// provera da li je zalba vec resena
				boolean resena = this.resenjeRepository.proveriDaLiJeZalbaResena(id);
				if (!resena) {
					dto.setRazresena("ne");
					dto.setBroj_zahteva((z.getBrojZahteva().getValue()).longValue());

					dto.setDatum_zalbe(z.getPodnozje().getMestoIDatum().getDatumZalbe().getValue());

					dto.setIme_i_prezime(z.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());
					dto.setNaziv_organa(z.getSadrzaj().getNazivOrgana().getValue());

					zalbe.add(dto);
				}

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

	public ArrayList<ZalbaCutanjeDTO> dobaviSvePoEmailu(String email) throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaCutanjeRepository.dobaviSvePoEmailu(email);
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ZalbaCutanjeDTO> zalbe = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaCutanjeDTO dto = new ZalbaCutanjeDTO();

				// id
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				boolean resena = this.resenjeRepository.proveriDaLiJeZalbaResena(id);
				if (resena)
					dto.setRazresena("da");
				else
					dto.setRazresena("ne");
				dto.setBroj_zahteva((z.getBrojZahteva().getValue()).longValue());

				// datum zalbe

				dto.setDatum_zalbe(z.getPodnozje().getMestoIDatum().getDatumZalbe().getValue());

				dto.setIme_i_prezime(z.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());
				dto.setNaziv_organa(z.getSadrzaj().getNazivOrgana().getValue());

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

	public ArrayList<ZalbaCutanjeDTO> dobaviSve() throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaCutanjeRepository.dobaviSve();
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ZalbaCutanjeDTO> zalbe = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaCutanjeDTO dto = new ZalbaCutanjeDTO();

				// id
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				boolean resena = this.resenjeRepository.proveriDaLiJeZalbaResena(id);
				if (resena)
					dto.setRazresena("da");
				else
					dto.setRazresena("ne");
				dto.setBroj_zahteva((z.getBrojZahteva().getValue()).longValue());

				// datum zalbe

				dto.setDatum_zalbe(z.getPodnozje().getMestoIDatum().getDatumZalbe().getValue());

				dto.setIme_i_prezime(z.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());
				dto.setNaziv_organa(z.getSadrzaj().getNazivOrgana().getValue());

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
		ResourceSet set = this.zalbaCutanjeRepository.pronadjiPoId(id);
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

	public String generisiPDF(long id) throws XMLDBException, SAXException, IOException {
		XSLFORTransformerZalbaCutanje transformer = null;

		try {
			transformer = new XSLFORTransformerZalbaCutanje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zalba = this.pronadjiZalbuPoId_Raw(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/zalba_cutanje_" + id + ".pdf";

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
		XSLFORTransformerZalbaCutanje transformer = null;

		try {
			transformer = new XSLFORTransformerZalbaCutanje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zalba = this.pronadjiZalbuPoId_Raw(zalba_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/zalba_cutanje_" + zalba_id + ".html";

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

	public boolean dodajZalbu(ZalbaCutanjeDodavanjeDTO zalbaDto, Korisnik korisnik) {

		ZalbaCutanje zalba = this.mapper.dtoUKlasu(zalbaDto, korisnik.getEmail(), korisnik.getImeIPrezime());
		boolean ok = this.zalbaCutanjeRepository.dodajZalbu(zalba);
		if (ok)
			return true;
		else
			return false;

	}

	public boolean dodajZalbuIzTeksta(String zalba) throws JAXBException {
		// validacija
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		// XML schema validacija
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(new File("./data/zalba_cutanje.xsd"));
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
		ZalbaCutanje z;
		try {
			z = (ZalbaCutanje) unmarshaller.unmarshal(reader);
			this.zalbaCutanjeRepository.dodajZalbuIzTeksta(zalba, z);
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
		return this.zalbaCutanjeRepository.odustaniOdZalbe(id);
	}

	public void generisiRDF(long id) throws SAXException, IOException {
		String rdfFilePath = "src/main/resources/static/rdf/zalba_cutanje_" + id + ".rdf";
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
			fw = new FileWriter("src/main/resources/static/xml/zalba_cutanje_"+id+".xml");
			fw.write(novi);
			fw.close(); 
		} catch (XMLDBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			metadataExtractor.extractMetadata(
					new FileInputStream(new File("src/main/resources/static/xml/zalba_cutanje_"+id+".xml")),
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
			fm.generisiJSON(id);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ZalbaCutanjeDTO> pretraga(String text) throws Exception{
		ResourceSet result = this.zalbaCutanjeRepository.pretraga(text);

		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ZalbaCutanjeDTO> zalbe = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ZalbaCutanjeDTO dto = new ZalbaCutanjeDTO();

				// id
				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				boolean resena = this.resenjeRepository.proveriDaLiJeZalbaResena(id);
				if (resena)
					dto.setRazresena("da");
				else
					dto.setRazresena("ne");
				dto.setBroj_zahteva((z.getBrojZahteva().getValue()).longValue());

				// datum zalbe

				dto.setDatum_zalbe(z.getPodnozje().getMestoIDatum().getDatumZalbe().getValue());

				dto.setIme_i_prezime(z.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());
				dto.setNaziv_organa(z.getSadrzaj().getNazivOrgana().getValue());

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

	public ZalbaCutanje dobaviZalbuPoId(long id) {
			ResourceSet set = this.zalbaCutanjeRepository.pronadjiPoId(id);

			try {
				if (set.getSize() == 1) {

					JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");

					Unmarshaller unmarshaller = context.createUnmarshaller();
					Resource res = set.getResource(0);

					ZalbaCutanje zalba = (ZalbaCutanje) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					return zalba;
				} else
					return null;
			} catch (Exception e) {
				return null;
			}
	}

}
