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
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje.ZahtevZaIzjasnjenjeCutanje;
import com.ftn.xml.repository.ZahtevZaIzjasnjenjeCutanjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZahtevZaIzjasnjenjeCutanjeService {

	@Autowired
	private ZahtevZaIzjasnjenjeCutanjeRepository zahtevRepository;

	public boolean dodajOdgovorZahtevZaIzjasnjenje(ZahtevZaIzjasnjenjeCutanje z) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter zahtevSW = new StringWriter();

			marshaller.marshal(z, zahtevSW);

			String zahtev = zahtevSW.toString();

			String changedOdgovor = this.removeNamespace(zahtev);

			System.out.println(changedOdgovor);

			return this.zahtevRepository.sacuvajZahtev(changedOdgovor);
		} catch (Exception e) {
			e.printStackTrace();
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

	public ZahtevZaIzjasnjenjeCutanje pronadjiPoIdZalbeCutanje(long id_zalbe) throws XMLDBException {

		ResourceSet set = zahtevRepository.pronadjiPoIdZalbeCutanje(id_zalbe);

		Resource r = set.getResource(0);

		ZahtevZaIzjasnjenjeCutanje z = new ZahtevZaIzjasnjenjeCutanje();

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje");
			try {
				Unmarshaller unmarshaller = context.createUnmarshaller();
				z = (ZahtevZaIzjasnjenjeCutanje) unmarshaller.unmarshal(((XMLResource) r).getContentAsDOM());

			} finally {
				try {
					((EXistResource) r).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
					
				}catch(NullPointerException e) {
					return null;
				}
			}

			return z;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
