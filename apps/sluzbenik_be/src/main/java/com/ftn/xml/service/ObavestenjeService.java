package com.ftn.xml.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ftn.xml.repository.ObavestenjeRepository;
import org.springframework.stereotype.Service;

@Service
public class ObavestenjeService {

	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	public boolean proveraPotvrdeZahteva(long zahtev_id) {
		return this.obavestenjeRepository.proveraPotvrdeZahteva(zahtev_id);
	}
}
