package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ZalbaCutanjeDTO;

import com.ftn.xml.dto.ZalbaCutanjeDodavanjeDTO;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;import com.ftn.xml.service.KorisnikService;
import com.ftn.xml.service.ZalbaCutanjeService;

@RestController
@RequestMapping(value = "/zalbaCutanje", produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaCutanjeController {

	@Autowired
	private ZalbaCutanjeService zalbaCutanjeService;
	
	@Autowired
	private KorisnikService korisnikService;

	@GetMapping(path = "/neresene")
	public ResponseEntity<ArrayList<ZalbaCutanjeDTO>> dobaviSveNeresene() throws XMLDBException, JAXBException {
		// String email = (String)
		// SecurityContextHolder.getContext().getAuthentication().getName();
		ArrayList<ZalbaCutanjeDTO> zalbe = this.zalbaCutanjeService.dobaviSveNeresene();
		if (zalbe.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(zalbe, HttpStatus.OK);

	}

	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ZalbaCutanjeDTO>> dobaviSve() throws XMLDBException, JAXBException {
		// String email = (String)
		// SecurityContextHolder.getContext().getAuthentication().getName();
		ArrayList<ZalbaCutanjeDTO> zalbe = this.zalbaCutanjeService.dobaviSve();
		if (zalbe.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(zalbe, HttpStatus.OK);

	}

	@GetMapping(path = "/korisnik")
	public ResponseEntity<ArrayList<ZalbaCutanjeDTO>> dobaviSvePoEmailu() {
		// dobavi ulogovanog korisnika
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			ArrayList<ZalbaCutanjeDTO> zalbe = this.zalbaCutanjeService.dobaviSvePoEmailu(email);
			return new ResponseEntity<>(zalbe, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/generisiPDF/{zalba_cutanje_id}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("zalba_cutanje_id") long zalba_id) throws XMLDBException {

		String file_path = this.zalbaCutanjeService.generisiPDF(zalba_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/generisiHTML/{zalba_cutanje_id}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("zalba_cutanje_id") long zalba_id) throws XMLDBException {

		String file_path = this.zalbaCutanjeService.generisiHTML(zalba_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	

	@PostMapping
	public ResponseEntity<Boolean> kreirajZahtev(@RequestBody String zalba) {
		boolean ok = false;
		try {
			ok = this.zalbaCutanjeService.dodajZalbuIzTeksta(zalba);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		if(ok)
			return new ResponseEntity<>(ok, HttpStatus.OK);
		else
			return new ResponseEntity<>(ok, HttpStatus.BAD_REQUEST);	
	}
}
