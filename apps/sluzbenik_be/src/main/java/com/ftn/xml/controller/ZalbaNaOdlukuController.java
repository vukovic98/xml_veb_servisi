package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.service.ZalbaNaOdlukuService;


@RestController
@RequestMapping("/zalba_na_odluku")
public class ZalbaNaOdlukuController {
	
	@Autowired
	private ZalbaNaOdlukuService zalbaService;
	
	@GetMapping("/generisiPDF/{zalba_na_odluku_id}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("zalba_na_odluku_id") long zalba_id) throws XMLDBException {

		String file_path = this.zalbaService.generisiPDF(zalba_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generisiHTML/{zalba_na_odluku_id}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("zalba_na_odluku_id") long zalba_id) throws XMLDBException {

		String file_path = this.zalbaService.generisiHTML(zalba_id);

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
