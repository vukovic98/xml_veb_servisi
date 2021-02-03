package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje.ZahteviZaIzjasnjenjeCutanje;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeCutnjaService;

@RestController
@RequestMapping(path = "/izjasnjenje-cutanje")
public class ZahtevZaIzjasnjenjeCutanjeController {

	@Autowired
	private ZahtevZaIzjasnjenjeCutnjaService zahtevService;
	
	@GetMapping
	public ResponseEntity<ZahteviZaIzjasnjenjeCutanje> pronadjiSve() {
		ZahteviZaIzjasnjenjeCutanje lista = this.zahtevService.pronadjiSveZahteve();
		
		if(!lista.getZalbaCutanje().isEmpty())
			return new ResponseEntity<ZahteviZaIzjasnjenjeCutanje>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
