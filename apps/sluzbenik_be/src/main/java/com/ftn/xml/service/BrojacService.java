package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.model.brojac.Brojac;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.BrojacRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class BrojacService {

	@Autowired
	private BrojacRepository brojacRepository;
	
	public int dobaviIdZahteva() {
		try {
			Resource res = this.brojacRepository.dobaviBrojac();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller
					.unmarshal(((XMLResource) res).getContentAsDOM());
			
			int trenutni = b.getBrojacZahtev();
			int sledeci = trenutni + 1;
			
			String brojacXml = "<brojac_obavestenje>" + b.getBrojacObavestenje() + "</brojac_obavestenje><brojac_zahtev>" + sledeci + "</brojac_zahtev>";
			
			this.brojacRepository.sacuvajBrojac(brojacXml);
			
			return trenutni;
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int dobaviIdObavestenja() {
		try {
			Resource res = this.brojacRepository.dobaviBrojac();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller
					.unmarshal(((XMLResource) res).getContentAsDOM());
			
			int trenutni = b.getBrojacObavestenje();
			int sledeci = trenutni + 1;
			
			String brojacXml = "<brojac_zahtev>" + b.getBrojacZahtev() + "</brojac_zahtev><brojac_obavestenje>" + sledeci + "</brojac_obavestenje>";
			
			this.brojacRepository.sacuvajBrojac(brojacXml);
			
			return trenutni;
		} catch (Exception e) {
			return -1;
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
