package com.ftn.xml.controller;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.service.ZalbaCutanjeService;

@RestController
@RequestMapping(value = "/zalbaCutanje", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaCutanjeController {

	@Autowired
	private ZalbaCutanjeService zalbaCutanjeService;

	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ZalbaCutanje>> getAll() {
		try {
			ArrayList<ZalbaCutanje> zalbe = this.zalbaCutanjeService.getAll();
			return new ResponseEntity<>(zalbe, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping(path = "/korisnik", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ZalbaCutanje>> getAllByUserEmail() {
		// dobavi ulogovanog korisnika
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			ArrayList<ZalbaCutanje> zalbe = this.zalbaCutanjeService.getAllByUserEmail(email);
			return new ResponseEntity<>(zalbe, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
}
