package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.xml.XmlMapper;
import com.ftn.xml.dto.KorisnikRegistracijaDto;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.service.KorisnikService;

@RestController
@RequestMapping(path = "/korisnik", consumes = MediaType.APPLICATION_XML_VALUE)
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@PostMapping("/registracija")
	public ResponseEntity<HttpStatus> registracija(@RequestBody KorisnikRegistracijaDto korisnikDTO) {
		System.out.println("jjj");
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setImeIPrezime(korisnikDTO.getImeIPrezime());
		korisnik.setLozinka(korisnikDTO.getLozinka());
		korisnik.setUloga("K");
		String userXML = null;

		try {

			XmlMapper mapper = new XmlMapper();
			userXML = mapper.writeValueAsString(korisnik);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean ok = this.korisnikService.registruj(korisnik.getEmail(), userXML);

		if (ok)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

	}
	
	@GetMapping("/test")
	public ResponseEntity<HttpStatus> test(){
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
