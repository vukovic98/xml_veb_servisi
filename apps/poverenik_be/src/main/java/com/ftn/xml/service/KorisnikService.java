package com.ftn.xml.service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.KorisnikPrijavaDto;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.repository.KorisnikRepository;

@Service
public class KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	public boolean registruj(String email, String korisnik) {
		boolean postoji = this.korisnikRepository.postojiPoMejlu(email);

		if (!postoji) {
			this.korisnikRepository.registruj(korisnik);
			return true;
		} else
			return false;
	}

	public boolean prijava(KorisnikPrijavaDto korisnik) {
		return this.korisnikRepository.prijava(korisnik);
	}

	public Korisnik pronadjiPoEmailu(String email) {
		Resource res = this.korisnikRepository.pronadjiPoEmailu(email);

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

}
