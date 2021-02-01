package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.ZalbaCutanjeDTO;
import com.ftn.xml.dto.ZalbaCutanjeDodavanjeDTO;
import com.ftn.xml.helper.DodajZalbuCutanjeMapper;
import com.ftn.xml.jaxb.util.XSLFORTransformerZalbaCutanje;
import com.ftn.xml.model.korisnik.Korisnik;
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
	public String generisiPDF(long id) throws XMLDBException {
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

	public void dodajZalbuIzTeksta(String zalba) throws Exception {
		// TODO Auto-generated method stub
		this.zalbaCutanjeRepository.dodajZalbuIzTeksta(zalba);
	}

}
