package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ZalbaCutanjeNaprednaDTO;
import com.ftn.xml.dto.ZalbaNaOdlukuDTO;
import com.ftn.xml.dto.ZalbaOdlukaNaprednaDTO;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
import com.ftn.xml.service.ZalbaNaOdlukuService;


@RestController
@RequestMapping("/zalba_na_odluku")
public class ZalbaNaOdlukuController {
	
	@Autowired
	private ZalbaNaOdlukuService zalbaService;
	
	@GetMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<ZalbaNaOdluku>> naprednaPretraga(
			@RequestBody ZalbaOdlukaNaprednaDTO dto) {
		String ime = !dto.getZahtev().equalsIgnoreCase("null") ? "\"" + dto.getZahtev() + "\"" : null;
		String mail = !dto.getMejl().equalsIgnoreCase("null") ? "\"" + dto.getMejl() + "\"" : null;
		String organ = !dto.getOrgan().equalsIgnoreCase("null") ? "\"" + dto.getOrgan() + "\"" : null;

		ArrayList<ZalbaNaOdluku> lista = this.zalbaService.naprednaPretraga(ime, mail, organ, dto.isAnd());

		if (!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/pretraga/{text}")
	public ResponseEntity<ArrayList<ZalbaNaOdlukuDTO>> pretraga(@PathVariable("text") String text) {
		ArrayList<ZalbaNaOdlukuDTO> lista = new ArrayList<>();
		
		try {
			lista = this.zalbaService.pretraga(text);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
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
