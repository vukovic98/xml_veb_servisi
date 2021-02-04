package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
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

import com.ftn.xml.dto.ZalbaNaOdlukuDTO;
import com.ftn.xml.jaxb.util.XSLFORTransformerZalbaNaOdluku;
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

	public long ukupanBrojZalbiNaOdluku() {
		return this.zalbaRepo.ukupanBrojZalbiNaOdluku();
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
		System.out.println(zalba);

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
}
