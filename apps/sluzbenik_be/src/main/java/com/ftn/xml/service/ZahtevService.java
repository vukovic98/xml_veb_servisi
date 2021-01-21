package com.ftn.xml.service;

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

import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.ZahtevRepository;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;

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
					System.out.println(res.getContent());
					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
					
					System.out.println("prodje");
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
}
