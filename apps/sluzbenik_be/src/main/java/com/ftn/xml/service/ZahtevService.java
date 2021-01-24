package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.jaxb.util.XSLFOTransformerZahtev;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.ZahtevRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;

	public boolean dodajZahtev(String z) {
		return this.zahtevRepository.sacuvajZahtev(z);
	}
	
	public ListaZahtevaZaPristupInformacijama pronadjiZahteveZaKorisnika(String email) {
		ResourceSet set = this.zahtevRepository.pronadjiZahteveZaKorisnika(email);

		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getZahtevZaPristupInformacijama().add(zahtev);

				} finally {
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ZahtevZaPristupInformacijama pronadjiZahtevPoId(long id) {
		ResourceSet set = this.zahtevRepository.pronadjiPoId(id);

		try {
			if (set.getSize() == 1) {

				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Resource res = set.getResource(0);

				ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
						.unmarshal(((XMLResource) res).getContentAsDOM());

				return zahtev;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}

	}

	public String pronadjiZahtevPoId_Raw(long id) {
		ResourceSet set = this.zahtevRepository.pronadjiPoId(id);

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

	public String generisiPDF(long id) {
		XSLFOTransformerZahtev transformer = null;

		try {
			transformer = new XSLFOTransformerZahtev();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiZahtevPoId_Raw(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/zahtev_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zahtev, pdf_path);
			if (ok)
				return pdf_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String generisiHTML(long zahtev_id) {
		XSLFOTransformerZahtev transformer = null;

		try {
			transformer = new XSLFOTransformerZahtev();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiZahtevPoId_Raw(zahtev_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/zahtev_" + zahtev_id + ".html";

		try {
			ok = transformer.generateHTML(zahtev, html_path);
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
