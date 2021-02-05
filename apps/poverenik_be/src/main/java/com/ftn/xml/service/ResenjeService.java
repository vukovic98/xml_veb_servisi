package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.Instant;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;

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
import com.ftn.xml.dto.ResenjeDTO;
import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.helper.DodajResenjeMapper;
import com.ftn.xml.jaxb.util.XSLFORTransformerResenje;
import com.ftn.xml.model.resenje.Resenje;
import com.ftn.xml.repository.ResenjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ResenjeService {

	@Autowired
	ResenjeRepository resenjeRepository;

	public ArrayList<ResenjeDTO> getAll() throws XMLDBException, JAXBException {

		ResourceSet result = this.resenjeRepository.getAll();
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ResenjeDTO> resenja = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.resenje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				Resenje r = (Resenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ResenjeDTO dto = new ResenjeDTO();

				String[] params = r.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				dto.setIme_i_prezime(r.getSadrzaj().getUvod().getPodnosilac().getContent());
				dto.setIshod(r.getIshod().getValue());

				XMLGregorianCalendar xmlDateZahtev = r.getSadrzaj().getUvod().getDatumZahteva().getValue();
				String dateZahtev = xmlDateZahtev.getYear() + "-" + xmlDateZahtev.getMonth() + "-"
						+ xmlDateZahtev.getDay();
				dto.setDatum_zahteva(dateZahtev);

				XMLGregorianCalendar xmlDateResenje = r.getOsnovniPodaci().getDatum().getValue();
				String dateResenje = xmlDateResenje.getYear() + "-" + xmlDateResenje.getMonth() + "-"
						+ xmlDateResenje.getDay();
				dto.setDatum_resenja(dateResenje);

				dto.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
				dto.setBroj_resenja(r.getBroj());
				dto.setBroj_zalbe(r.getBrojZalbe().getValue().toString());

				resenja.add(dto);
			} finally {

				// don't forget to cleanup resources
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return resenja;

	}

	public ArrayList<ResenjeDTO> getAllForUser(String userMail) throws XMLDBException, JAXBException {

		ResourceSet result = this.resenjeRepository.getAllForUser(userMail);
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ResenjeDTO> resenja = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.resenje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				Resenje r = (Resenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ResenjeDTO dto = new ResenjeDTO();

				String[] params = r.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				dto.setIme_i_prezime(r.getSadrzaj().getUvod().getPodnosilac().getContent());
				dto.setIshod(r.getIshod().getValue());

				XMLGregorianCalendar xmlDateZahtev = r.getSadrzaj().getUvod().getDatumZahteva().getValue();
				String dateZahtev = xmlDateZahtev.getYear() + "-" + xmlDateZahtev.getMonth() + "-"
						+ xmlDateZahtev.getDay();
				dto.setDatum_zahteva(dateZahtev);

				XMLGregorianCalendar xmlDateResenje = r.getOsnovniPodaci().getDatum().getValue();
				String dateResenje = xmlDateResenje.getYear() + "-" + xmlDateResenje.getMonth() + "-"
						+ xmlDateResenje.getDay();
				dto.setDatum_resenja(dateResenje);

				dto.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
				dto.setBroj_resenja(r.getBroj());
				dto.setBroj_zalbe(r.getBrojZalbe().getValue().toString());

				resenja.add(dto);
			} finally {

				// don't forget to cleanup resources
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return resenja;

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

	public String findById_RAW(long id) throws XMLDBException {
		ResourceSet set = this.resenjeRepository.findById(id);
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

	public String generatePDF(long id) throws XMLDBException {
		XSLFORTransformerResenje transformer = null;

		try {
			transformer = new XSLFORTransformerResenje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String resenje = this.findById_RAW(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/resenje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(resenje, pdf_path);
			if (ok)
				return pdf_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String generateHTML(long resenje_id) throws XMLDBException {
		XSLFORTransformerResenje transformer = null;

		try {
			transformer = new XSLFORTransformerResenje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String resenje = this.findById_RAW(resenje_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/resenje_" + resenje_id + ".html";

		try {
			ok = transformer.generateHTML(resenje, html_path);
			if (ok)
				return html_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean dodajResenje(Resenje r, int index) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.resenje");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter resenjeSW = new StringWriter();

			marshaller.marshal(r, resenjeSW);

			String resenje = resenjeSW.toString();

			String changedResenje = this.removeNamespace(resenje);

			DodajResenjeMapper mapper = new DodajResenjeMapper();

			ResenjeFusekiDTO dto = mapper.klasaUFusekiDTO(r);

			return this.resenjeRepository.sacuvajResenje(changedResenje, dto, index);

		} catch (Exception e) {
			return false;
		}
	}

	public ArrayList<ResenjeDTO> pretraga(String text) throws Exception {
		ResourceSet result = this.resenjeRepository.pretraga(text);
		ResourceIterator i = result.getIterator();
		Resource res = null;
		ArrayList<ResenjeDTO> resenja = new ArrayList<>();
		JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.resenje");

		while (i.hasMoreResources()) {

			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				res = i.nextResource();
				Resenje r = (Resenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				ResenjeDTO dto = new ResenjeDTO();

				String[] params = r.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);
				dto.setId(id);

				dto.setIme_i_prezime(r.getSadrzaj().getUvod().getPodnosilac().getContent());
				dto.setIshod(r.getIshod().getValue());

				XMLGregorianCalendar xmlDateZahtev = r.getSadrzaj().getUvod().getDatumZahteva().getValue();
				String dateZahtev = xmlDateZahtev.getYear() + "-" + xmlDateZahtev.getMonth() + "-"
						+ xmlDateZahtev.getDay();
				dto.setDatum_zahteva(dateZahtev);

				XMLGregorianCalendar xmlDateResenje = r.getOsnovniPodaci().getDatum().getValue();
				String dateResenje = xmlDateResenje.getYear() + "-" + xmlDateResenje.getMonth() + "-"
						+ xmlDateResenje.getDay();
				dto.setDatum_resenja(dateResenje);

				dto.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
				dto.setBroj_resenja(r.getBroj());
				dto.setBroj_zalbe(r.getBrojZalbe().getValue().toString());

				resenja.add(dto);
			} finally {

				// don't forget to cleanup resources
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return resenja;
	}

	public void generisiJSON(long resenje_id) {
		FusekiManager fm = new FusekiManager();
		try {
			fm.generisiJSONResenje(resenje_id);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	

	public void generisiRDF(long id) throws SAXException, IOException {
		String rdfFilePath = "src/main/resources/static/rdf/resenje_" + id + ".rdf";
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		String rs;
		FileWriter fw;
		try {
			rs = this.findById_RAW(id);
			String pocetak = rs.substring(0, 8);
			String ubaci = " xmlns:obav=\"http://www.ftn.uns.ac.rs/rdf/example\"  xmlns:pred=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" ";
			String kraj = rs.substring(9);
			String novi = pocetak + ubaci + kraj;
			fw = new FileWriter("src/main/resources/static/xml/resenje_" + id + ".xml");
			fw.write(novi);
			fw.close();
		} catch (XMLDBException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			metadataExtractor.extractMetadata(
					new FileInputStream(new File("src/main/resources/static/xml/resenje_" + id + ".xml")),
					new FileOutputStream(new File(rdfFilePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
