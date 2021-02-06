package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ResenjeDTO;
import com.ftn.xml.dto.ResenjeNaprednaDTO;
import com.ftn.xml.dto.ZahtevNaprednaDTO;
import com.ftn.xml.model.resenje.ListaResenja;
import com.ftn.xml.model.resenje.Resenje;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.service.ResenjeService;

@RestController
@RequestMapping("/resenje")
public class ResenjeController {

	@Autowired
	private ResenjeService resenjeService;
	
	@PostMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<Resenje>> naprednaPretraga(
			@RequestBody ResenjeNaprednaDTO dto) {
		String zalba = !dto.getZalba().equalsIgnoreCase("null") ? "\"" + dto.getZalba() + "\"" : null;
		String ishod = !dto.getIshod().equalsIgnoreCase("null") ? "\"" + dto.getIshod() + "\"" : null;
		String korisnik = !dto.getKorisnik().equalsIgnoreCase("null") ? "\"" + dto.getKorisnik() + "\"" : null;

		ListaResenja lista = this.resenjeService.naprednaPretraga(zalba, ishod, korisnik, dto.isAnd());

		if (!lista.getResenje().isEmpty())
			return new ResponseEntity<>((ArrayList<Resenje>)lista.getResenje(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/pretraga/{text}")
	public ResponseEntity<ArrayList<ResenjeDTO>> pretraga(@PathVariable("text") String text) {
		ArrayList<ResenjeDTO> lista = new ArrayList<>();
		try {
			lista = this.resenjeService.pretraga(text);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
	@GetMapping("/generisiJSON/{resenje_id}")
	public ResponseEntity<byte[]> generisiJSON(@PathVariable("resenje_id") long resenje_id) throws XMLDBException {

		String filePath = "src/main/resources/static/json/resenje_" + resenje_id + ".json";

		try {
			this.resenjeService.generisiJSON(resenje_id);
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generisiRDF/{resenje_id}")
	public ResponseEntity<byte[]> generisiRDF(@PathVariable("resenje_id") long resenje_id) throws XMLDBException {

		String filePath = "src/main/resources/static/rdf/resenje_" + resenje_id + ".rdf";
		try {
			this.resenjeService.generisiRDF(resenje_id);
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
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
	
	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ResenjeDTO>> getAll() throws XMLDBException, JAXBException {

		ArrayList<ResenjeDTO> resenja = this.resenjeService.getAll();
		if (resenja.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(resenja, HttpStatus.OK);

	}
}