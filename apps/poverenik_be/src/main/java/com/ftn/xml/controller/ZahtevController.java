package com.ftn.xml.controller;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.service.ZahtevService;

@RestController
@RequestMapping(path = "/zahtevi",produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

	@Autowired
	private ZahtevService zahtevService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<ZahtevZaPristupInformacijama>> getAll() throws XMLDBException{
		try {
			ArrayList<ZahtevZaPristupInformacijama> zahtevi = this.zahtevService.getAll();
			return new ResponseEntity<>(zahtevi, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
}
