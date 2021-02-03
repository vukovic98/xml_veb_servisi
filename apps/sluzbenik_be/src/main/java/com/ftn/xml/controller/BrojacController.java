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
@RequestMapping(value = "/brojac", produces = MediaType.APPLICATION_XML_VALUE)
public class BrojacController {
	
	@Autowired
	private BrojacService brojacService;
	
	@GetMapping(path = "/obavestenje")
	public ResponseEntity<Integer> dobaviBrojacZaObavestenje() {
		return new ResponseEntity<>(this.brojacService.dobaviIdObavestenja(), HttpStatus.OK);
	}
}
