package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.XMLGregorianCalendar;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.ResenjeDTO;
import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.jaxb.util.XSLFORTransformerResenje;
import com.ftn.xml.mapper.DodajResenjeMapper;
import com.ftn.xml.model.resenje.ListaResenja;
import com.ftn.xml.model.resenje.Resenje;
import com.ftn.xml.repository.ResenjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ResenjeService {

	@Autowired
	private ResenjeRepository resenjeRepository;
	
	public long ukupanBrojResenja() {
		return this.resenjeRepository.ukupanBrojResenja();
	}

	public boolean dodajResenje(Resenje r) {

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
			
			String[] about = r.getAbout().split("/");
			String id = about[about.length-1];

			return this.resenjeRepository.sacuvajResenje(changedResenje, dto, Integer.parseInt(id));

		} catch (Exception e) {
			return false;
		}
	}

	public ListaResenja naprednaPretraga(String zalba, String ishod, String korisnik, boolean and) {
		List<String> ids = new ArrayList<>();
		try {
			ids = this.resenjeRepository.naprednaPretraga(zalba, ishod, korisnik, and);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ids = (ArrayList<String>) ids;

		ListaResenja lista = new ListaResenja();

		for (String i : ids) {
			Resenje z = this.pronadjiPoId(Long.parseLong(i));

			lista.getResenje().add(z);
		}

		return lista;

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

	public Resenje pronadjiPoId(long id) {
		ResourceSet set = this.resenjeRepository.findById(id);
		try {
			if (set.getSize() == 1) {

				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.resenje");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Resource res = set.getResource(0);

				Resenje resenje = (Resenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());

				return resenje;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
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
}
