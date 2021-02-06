package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.model.brojac.Brojac;
import com.ftn.xml.repository.BrojacRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class BrojacService {

	@Autowired
	private BrojacRepository brojacRepository;

	public int dobaviIdZalbeCutanje() {
		try {
			Resource res = this.brojacRepository.dobaviIdZalbeCutanje();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
			
			BigInteger trenutni = b.getBrojacZalbi();
			int sledeci = trenutni.intValue() + 1;
			
			String brojacXml = String.valueOf(sledeci);
			
			this.brojacRepository.sacuvajIdZalbeCutanje(brojacXml);
			
			return trenutni.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int dobaviIdZalbeOdluka() {
		try {
			Resource res = this.brojacRepository.dobaviIdZalbeOdluka();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller
					.unmarshal(((XMLResource) res).getContentAsDOM());
			
			BigInteger trenutni = b.getBrojacZalbi();
			int sledeci = trenutni.intValue() + 1;
			
			String brojacXml = String.valueOf(sledeci);
			
			this.brojacRepository.sacuvajIdZalbeOdluka(brojacXml);
			
			return trenutni.intValue();
		} catch (Exception e) {
			return -1;
		}
	}
	
	public int dobaviIdResenja() {
		try {
			Resource res = this.brojacRepository.dobaviIdResenja();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller
					.unmarshal(((XMLResource) res).getContentAsDOM());
			
			BigInteger trenutni = b.getBrojacResenje();
			int sledeci = trenutni.intValue() + 1;
			
			String brojacXml = String.valueOf(sledeci);
			
			this.brojacRepository.sacuvajIdResenja(brojacXml);
			
			return trenutni.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int dobaviIdResenjaNoIncrement() {
		
		try {
			Resource res = this.brojacRepository.dobaviIdResenja();

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.brojac");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			Brojac b = (Brojac) unmarshaller
					.unmarshal(((XMLResource) res).getContentAsDOM());
			
			BigInteger trenutni = b.getBrojacResenje();
			
			return trenutni.intValue();
		} catch (Exception e) {
			e.printStackTrace();
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
