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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ZalbaNaOdlukuDTO;
import com.ftn.xml.dto.ZalbaOdlukaNaprednaDTO;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
import com.ftn.xml.service.ZalbaNaOdlukuService;

@RestController
@RequestMapping(value = "/zalbaNaOdluku" , produces = MediaType.APPLICATION_XML_VALUE)
public class ZalbaNaOdlukuController {
	
	@Autowired
	private ZalbaNaOdlukuService zalbaService;
	
	@GetMapping(path = "/neresene")
	public ResponseEntity<ArrayList<ZalbaNaOdlukuDTO>> dobaviSveNeresene() throws XMLDBException, JAXBException {
		ArrayList<ZalbaNaOdlukuDTO> zalbe = this.zalbaService.dobaviSveNereseneZalbe();
		
		if (zalbe.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(zalbe, HttpStatus.OK);
		
	}
	
	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ZalbaNaOdlukuDTO>> dobaviSve() throws XMLDBException, JAXBException {
		ArrayList<ZalbaNaOdlukuDTO> zalbe = this.zalbaService.dobaviSve();
		if (zalbe.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(zalbe, HttpStatus.OK);

	}
	
	@GetMapping(path = "/korisnik")
	public ResponseEntity<ArrayList<ZalbaNaOdlukuDTO>> dobaviSvePoEmailu() {
		// dobavi ulogovanog korisnika
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		try {
			ArrayList<ZalbaNaOdlukuDTO> zalbe = this.zalbaService.dobaviSvePoEmailu(email);
			return new ResponseEntity<>(zalbe, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}
	
	@GetMapping(path = "/dobaviRaw/{id}")
	public ResponseEntity<String> dobaviRaw(@PathVariable("id") long id) throws XMLDBException{
		
		String zalbaRaw = this.zalbaService.pronadjiZalbuPoId_Raw(id);
		
		return ResponseEntity.ok().body(zalbaRaw);
		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ZalbaNaOdlukuDTO> pronadjiZalbuPoId(@PathVariable("id") long id) {
		ZalbaNaOdluku z = this.zalbaService.pronadjiZalbuPoId(id);

		if (z != null) {
			ZalbaNaOdlukuDTO n = new ZalbaNaOdlukuDTO();

			String[] params = z.getAbout().split("/");

			n.setId(id);
			
			n.setBroj_zahteva((z.getBrojZahteva().getValue().longValue()));
			
			
			String date = z.getPodnozje().getDatumZakljuckaZalbe().getValue();
			n.setDatum(date);

			n.setIme(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());
			n.setPrezime(z.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
			n.setNaziv_organa(z.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());
			n.setEmail(z.getOsnovniPodaci().getPodaciOZaliocu().getKorisnikEmail().getContent());
			n.setDatum_zahteva(z.getSadrzaj().getDatumOdbijenogZahteva().getValue());
			
			return new ResponseEntity<>(n, HttpStatus.OK);
		} else
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

	@GetMapping("/generisiJSON/{zalba_na_odluku_id}")
	public ResponseEntity<byte[]> generisiJSON(@PathVariable("zalba_na_odluku_id") long zalba_id) throws XMLDBException {

		String filePath = "src/main/resources/static/json/zalba_na_odluku_" + zalba_id + ".json";

		try {
			this.zalbaService.generisiJSON(zalba_id);
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generisiRDF/{zalba_na_odluku_id}")
	public ResponseEntity<byte[]> generisiRDF(@PathVariable("zalba_na_odluku_id") long zalba_id) throws XMLDBException {

		String filePath = "src/main/resources/static/rdf/zalba_na_odluku_" + zalba_id + ".rdf";
		try {
			this.zalbaService.generisiRDF(zalba_id);
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
	
	@PostMapping
	public ResponseEntity<Boolean> kreirajZahtev(@RequestBody String zalba) {
		boolean ok = false;
		try {
			ok = this.zalbaService.dodajZalbuIzTeksta(zalba);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		if(ok)
			return new ResponseEntity<>(ok, HttpStatus.OK);
		else
			System.out.println("OVDE JE GRESKA");
			return new ResponseEntity<>(ok, HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> odustaniOdZalbe(@PathVariable("id") long id) {
		boolean ok = this.zalbaService.odustaniOdZalbe(id);
		if(ok)
			return new ResponseEntity<>(ok, HttpStatus.OK);
		else
			return new ResponseEntity<>(ok, HttpStatus.BAD_REQUEST);	

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
	
	@PostMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<ZalbaNaOdluku>> naprednaPretraga(
			@RequestBody ZalbaOdlukaNaprednaDTO dto) {
		String ime = !dto.getZahtev().equalsIgnoreCase("null") ? "\"" + dto.getZahtev() + "\"" : null;
		String mail = !dto.getMejl().equalsIgnoreCase("null") ? "\"" + dto.getMejl() + "\"" : null;
		String organ = !dto.getOrgan().equalsIgnoreCase("null") ? "\"" + dto.getOrgan() + "\"" : null;

		ArrayList<ZalbaNaOdluku> lista = this.zalbaService.naprednaPretraga(ime, mail, organ, dto.isAnd());
		System.out.println(lista);
		if (!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
		{
			System.out.println("USLO");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
	}

}
