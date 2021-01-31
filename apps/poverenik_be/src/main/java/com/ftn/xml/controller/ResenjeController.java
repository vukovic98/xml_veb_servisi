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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ResenjeDTO;
import com.ftn.xml.service.ResenjeService;

@RestController
@RequestMapping(path = "/resenja", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

	@Autowired
	ResenjeService resenjeService;
	
	
	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ResenjeDTO>> getAll() throws XMLDBException, JAXBException {
		
		ArrayList<ResenjeDTO> resenja = this.resenjeService.getAll();
		if (resenja.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(resenja, HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/user",consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ResenjeDTO>> getAllByUser() throws XMLDBException, JAXBException {
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		ArrayList<ResenjeDTO> resenja = this.resenjeService.getAllForUser(email);
		if (resenja.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(resenja, HttpStatus.OK);
		
	}
	
	@GetMapping("/generatePDF/{resenje_id}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("resenje_id") long resenje_id) throws XMLDBException {

		String file_path = this.resenjeService.generatePDF(resenje_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generateHTML/{resenje_id}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("resenje_id") long resenje_id) throws XMLDBException {

		String file_path = this.resenjeService.generateHTML(resenje_id);

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
