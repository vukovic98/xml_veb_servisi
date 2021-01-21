package com.ftn.xml.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	public ResourceSet prijavaKorisnika(String mail, String pass) {
		return this.korisnikRepository.prijavaKorisnika(mail, pass);
	}

	public Korisnik pronadjiPoMejlu(String mejl) {
		Resource res = this.korisnikRepository.pronadjiPoMejlu(mejl);
		

		if (res != null) {
			try {
				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.korisnik");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Korisnik korisnik = (Korisnik) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());
				return korisnik;

			} catch (Exception e) {
				return null;
			}
		} else
			return null;
	}

	public boolean registrujKorisnika(String email, String text) {

		boolean postoji = this.korisnikRepository.postojiPoMejlu(email);

		if (!postoji) {
			this.korisnikRepository.registracija(text);
			return true;
		} else
			return false;
	}

}
