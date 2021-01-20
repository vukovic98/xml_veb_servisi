package com.ftn.xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.xml.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository korisnikRepository;

	
	public boolean registruj(String email, String korisnik) {
		boolean postoji = this.korisnikRepository.postojiPoMejlu(email);
		
		if(!postoji) {
			this.korisnikRepository.registruj(korisnik);
			return true;
		} else 
			return false;
	}
	
}
