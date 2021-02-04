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

import com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje.OdgovorZahtevZaIzjasnjenje;
import com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje.OdgovoriZahtevZaIzjasnjenje;
import com.ftn.xml.repository.OdgovorZahtevZaIzjasnjenjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class OdgovorZahtevZaIzjasnjenjeService {

	@Autowired
	OdgovorZahtevZaIzjasnjenjeRepository odgovorRepository;
	
	public boolean dodajOdgovorZahtevZaIzjasnjenje(OdgovorZahtevZaIzjasnjenje o) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter odgovorSW = new StringWriter();

			marshaller.marshal(o, odgovorSW);

			String odgovor = odgovorSW.toString();

			String changedOdgovor = this.removeNamespace(odgovor);

			System.out.println(changedOdgovor);
			
			return this.odgovorRepository.dodajOdgovorZahtevZaIzjasnjenje(changedOdgovor);
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

	public boolean izbrisiOdgovorZahtevZaIzjasnjenje(String id) {

		return this.odgovorRepository.izbrisiOdgovorZahtevZaIzjasnjenje(id);

	}
	
	public OdgovoriZahtevZaIzjasnjenje pronadjiSveOdgovore() {
		ResourceSet set = this.odgovorRepository.dobaviSve();

		OdgovoriZahtevZaIzjasnjenje lista = new OdgovoriZahtevZaIzjasnjenje();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					OdgovorZahtevZaIzjasnjenje odgovor = (OdgovorZahtevZaIzjasnjenje) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getOdgovorZahtevZaIzjasnjenje().add(odgovor);

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
	
}
