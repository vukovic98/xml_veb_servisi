package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.model.zahtev_za_izjasnjenje_odluka.ZahteviZaIzjasnjenjeOdluka;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeOdlukaService;

@RestController
@RequestMapping(path = "/izjasnjenje-odluka")
public class ZahtevZaIzjasnjenjeOdlukaController {

	@Autowired
	private ZahtevZaIzjasnjenjeOdlukaService zahtevService;
	
	@GetMapping
	public ResponseEntity<ZahteviZaIzjasnjenjeOdluka> pronadjiSve() {
		ZahteviZaIzjasnjenjeOdluka lista = this.zahtevService.pronadjiSveZahteve();
		
		if(!lista.getZalbaNaOdluku().isEmpty())
			return new ResponseEntity<ZahteviZaIzjasnjenjeOdluka>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
