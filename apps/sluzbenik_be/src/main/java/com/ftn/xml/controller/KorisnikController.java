package com.ftn.xml.controller;


import javax.servlet.http.HttpServletRequest;

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
import org.xmldb.api.base.ResourceSet;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ftn.xml.dto.KorisnikLoginDTO;
import com.ftn.xml.dto.KorisnikSignUpDTO;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnik", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;

	@PostMapping("/prijava")
	public ResponseEntity<ResourceSet> prijava(@RequestBody KorisnikLoginDTO korisnik, HttpServletRequest req) throws Exception {
		
		ResourceSet s = this.korisnikService.prijavaKorisnika(korisnik.getEmail(), korisnik.getLozinka());
		
		if(s.getSize() == 0) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		req.getSession().setAttribute("email", korisnik.getEmail());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test(HttpServletRequest req) {
		return new ResponseEntity<String>((String)req.getSession().getAttribute("email"), HttpStatus.OK);
	}

	@PostMapping(path = "/registracija")
	public ResponseEntity<HttpStatus> registracija(@RequestBody KorisnikSignUpDTO entitet) {

		Korisnik k = new Korisnik();
		k.setEmail(entitet.getEmail());
		k.setImeIPrezime(entitet.getIme_i_prezime());
		k.setLozinka(entitet.getLozinka());
		k.setUloga("K");
		
		String userXML = null;
		
		try {
			
			XmlMapper mapper = new XmlMapper();
			userXML = mapper.writeValueAsString(k);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean ok = this.korisnikService.registrujKorisnika(k.getEmail(), userXML);

		if(ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

}
