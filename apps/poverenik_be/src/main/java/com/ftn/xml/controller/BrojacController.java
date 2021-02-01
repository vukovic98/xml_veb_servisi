package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.service.BrojacService;

@RestController
@RequestMapping(value = "/brojac", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class BrojacController {

	@Autowired
	private BrojacService brojacService;

	@GetMapping("/zalbaCutanje")
	public ResponseEntity<String> dobaviBrojacZalbeCutanje() {
		String brojac = String.valueOf(this.brojacService.dobaviIdZalbeCutanje());
		return new ResponseEntity<String>(brojac, HttpStatus.OK);
	}

	@GetMapping("/zalbaOdluka")
	public ResponseEntity<String> dobaviBrojacZalbeOdluka() {
		String brojac = String.valueOf(this.brojacService.dobaviIdZalbeOdluka());
		return new ResponseEntity<String>(brojac, HttpStatus.OK);
	}

	@GetMapping("/resenje")
	public ResponseEntity<String> dobaviBrojacResenje() {
		String brojac = String.valueOf(this.brojacService.dobaviIdResenja());
		return new ResponseEntity<String>(brojac, HttpStatus.OK);
	}
}
