package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ZahtevNaprednaDTO;
import com.ftn.xml.dto.ZalbaCutanjeDTO;
import com.ftn.xml.dto.ZalbaCutanjeNaprednaDTO;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.service.ZalbaCutanjeService;

@RestController
@RequestMapping("/zalba_cutanje")
public class ZalbaCutanjeController {

	@Autowired
	private ZalbaCutanjeService zalbaCutanjeService;
	
	@PostMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<ZalbaCutanje>> naprednaPretraga(
			@RequestBody ZalbaCutanjeNaprednaDTO dto) {
		String ime = !dto.getZahtev().equalsIgnoreCase("null") ? "\"" + dto.getZahtev() + "\"" : null;
		String mail = !dto.getMejl().equalsIgnoreCase("null") ? "\"" + dto.getMejl() + "\"" : null;
		String organ = !dto.getOrgan().equalsIgnoreCase("null") ? "\"" + dto.getOrgan() + "\"" : null;

		ArrayList<ZalbaCutanje> lista = this.zalbaCutanjeService.naprednaPretraga(ime, mail, organ, dto.isAnd());
		
		if (!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/pretraga/{text}")
	public ResponseEntity<ArrayList<ZalbaCutanjeDTO>> pretraga(@PathVariable("text") String text) {
		ArrayList<ZalbaCutanjeDTO> lista = new ArrayList<>();
		
		try {
			lista = this.zalbaCutanjeService.pretraga(text);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if(!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	
	@GetMapping("/generisiJSON/{zalba_cutanje_id}")
	public ResponseEntity<byte[]> generisiJSON(@PathVariable("zalba_cutanje_id") long zalba_id) throws XMLDBException {

		String filePath = "src/main/resources/static/json/zalba_cutanje_" + zalba_id + ".json";

		try {
			this.zalbaCutanjeService.generisiJSON(zalba_id);
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generisiRDF/{zalba_cutanje_id}")
	public ResponseEntity<byte[]> generisiRDF(@PathVariable("zalba_cutanje_id") long zalba_id) throws XMLDBException {

		String filePath = "src/main/resources/static/rdf/zalba_cutanje_" + zalba_id + ".rdf";
		try {
			this.zalbaCutanjeService.generisiRDF(zalba_id);
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
}
