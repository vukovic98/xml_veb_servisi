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

import com.ftn.xml.model.zahtev_za_izjasnjenje_odluka.ZahteviZaIzjasnjenjeOdluka;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
import com.ftn.xml.repository.ZahtevZaIzjasnjenjeOdlukaRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZahtevZaIzjasnjenjeOdlukaService {

	@Autowired
	private ZahtevZaIzjasnjenjeOdlukaRepository zahtevRepository;
	
	@Autowired
	private ZalbaNaOdlukuService zalbaService;

	public ZahteviZaIzjasnjenjeOdluka pronadjiSveZahteve() {
		ResourceSet set = this.zahtevRepository.dobaviSve();

		ZahteviZaIzjasnjenjeOdluka lista = new ZahteviZaIzjasnjenjeOdluka();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZalbaNaOdluku zahtev = (ZalbaNaOdluku) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getZalbaNaOdluku().add(zahtev);

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

	public boolean dodajZahtevZaIzjasnjenjeOdluka(ZalbaNaOdluku z) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_na_odluku");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter zahtevSW = new StringWriter();

			marshaller.marshal(z, zahtevSW);

			String zahtev = zahtevSW.toString();

			String changedZahtev = this.removeNamespace(zahtev);
			
			this.zalbaService.dodajZalbuIzTeksta(changedZahtev);

			return this.zahtevRepository.dodajZahtevZaIzjasnjenjeOdluka(changedZahtev);

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

	public boolean izbrisiZahtevZaIzjasnjenjeOdluka(String id) {

		return this.zahtevRepository.izbrisiZahtevZaIzjasnjenjeOdluka(id);

	}

}
