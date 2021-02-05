package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.model.izvestaj.Izvestaj;
import com.ftn.xml.model.izvestaj.ListaIzvestaja;
import com.ftn.xml.service.IzvestajService;
import com.ftn.xml.service.ZahtevService;
import com.ftn.xml.service.ZalbaCutanjeService;
import com.ftn.xml.service.ZalbaNaOdlukuService;

@RestController
@RequestMapping(path = "/izvestaj", produces = MediaType.APPLICATION_XML_VALUE)
public class IzvestajController {
	
	@Autowired
	private IzvestajService izvestajService;
	
	@GetMapping()
	public ResponseEntity<ArrayList<Izvestaj>> dobaviSveIzvestaje() {
		ListaIzvestaja lista = new ListaIzvestaja();
		
		try{
			lista = this.izvestajService.dobaviSve();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!lista.getIzvestaj().isEmpty())
			return new ResponseEntity<>((ArrayList<Izvestaj>) lista.getIzvestaj(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/generisiPDF/{datum}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("datum") String datum) {

		String file_path = this.izvestajService.generisiPDF(datum);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/generisiHTML/{datum}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("datum") String datum) {

		String file_path = this.izvestajService.generisiHTML(datum);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

}
