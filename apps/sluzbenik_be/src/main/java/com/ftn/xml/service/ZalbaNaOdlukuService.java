package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.jaxb.util.XSLFORTransformerZalbaNaOdluku;
import com.ftn.xml.repository.ZalbaNaOdlukuRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZalbaNaOdlukuService {
	
	@Autowired
	private ZalbaNaOdlukuRepository zalbaRepo;

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
}
