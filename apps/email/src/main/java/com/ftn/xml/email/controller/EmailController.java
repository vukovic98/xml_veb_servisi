package com.ftn.xml.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.email.dto.ObavestenjeDTO;
import com.ftn.xml.email.dto.OdbijenZahtevDTO;
import com.ftn.xml.email.service.EmailService;

@RestController
@RequestMapping(path = "/email", consumes = MediaType.APPLICATION_XML_VALUE)
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public ResponseEntity<HttpStatus> test(@RequestBody ObavestenjeDTO dto) {
		
		try {
			this.emailService.sendMail(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/odbijen-zahtev")
	public ResponseEntity<HttpStatus> odbijenZahtev(@RequestBody OdbijenZahtevDTO dto) {
		try {
			this.emailService.odbijenZahtev(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
