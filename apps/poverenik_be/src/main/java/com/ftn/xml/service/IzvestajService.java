package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.jaxb.util.XSLFOTransformerIzvestaj;
import com.ftn.xml.model.izvestaj.Izvestaj;
import com.ftn.xml.model.izvestaj.ListaIzvestaja;
import com.ftn.xml.repository.IzvestajRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class IzvestajService {
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	public boolean dodajIzvestaj(String xml) {
		return this.izvestajRepository.dodajIzvestaj(xml);
	}
	
	public ListaIzvestaja dobaviSve() {
		ResourceSet set = this.izvestajRepository.dobaviSveIzvestaje();

		ListaIzvestaja lista = new ListaIzvestaja();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.izvestaj");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					Izvestaj izvestaj = (Izvestaj) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getIzvestaj().add(izvestaj);

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
	
	public String pronadjiPoDatumu(String date) {
		ResourceSet set = this.izvestajRepository.pronadjiPoDatumu(date);

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

	public String generisiPDF(String date) {
		XSLFOTransformerIzvestaj transformer = null;

		try {
			transformer = new XSLFOTransformerIzvestaj();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String izvestaj = this.pronadjiPoDatumu(date);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/izvestaj.pdf";

		try {
			ok = transformer.generatePDF(izvestaj, pdf_path);
			if (ok)
				return pdf_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String generisiHTML(String date) {
		XSLFOTransformerIzvestaj transformer = null;

		try {
			transformer = new XSLFOTransformerIzvestaj();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String izvestaj = this.pronadjiPoDatumu(date);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/izvestaj.html";

		try {
			ok = transformer.generateHTML(izvestaj, html_path);
			if (ok)
				return html_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean dodajIzvestaj(Izvestaj i) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.izvestaj");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter zahtevSW = new StringWriter();

			marshaller.marshal(i, zahtevSW);

			String izvestaj = zahtevSW.toString();
			
			String changedIzvestaj = this.removeNamespace(izvestaj);
			
			return this.izvestajRepository.dodajIzvestaj(changedIzvestaj);

		} catch (Exception e) {
			return false;
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

}
