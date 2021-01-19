package com.ftn.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	public ResourceSet prijavaKorisnika(String mail, String pass) {
		return this.korisnikRepository.prijavaKorisnika(mail, pass);
	}
	
	public boolean registrujKorisnika(String email, String text) {
		
		boolean postoji = this.korisnikRepository.postojiPoMejlu(email);
		
		if(!postoji) {
			this.korisnikRepository.registracija(text);
			return true;
		} else 
			return false;
	}

}
