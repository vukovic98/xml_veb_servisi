package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.DodajResenjeDTO;
import com.ftn.xml.dto.IspitajObrazlozenjeDTO;
import com.ftn.xml.dto.ObrazlozenjeResponse;
import com.ftn.xml.dto.ResenjeDTO;
import com.ftn.xml.dto.ResenjeNaprednaDTO;
import com.ftn.xml.helper.DodajResenjeMapper;
import com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje.OdgovorZahtevZaIzjasnjenje;
import com.ftn.xml.model.resenje.ListaResenja;
import com.ftn.xml.model.resenje.Resenje;
import com.ftn.xml.service.BrojacService;
import com.ftn.xml.service.OdgovorZahtevZaIzjasnjenjeService;
import com.ftn.xml.service.ResenjeService;

@RestController
@RequestMapping(path = "/resenja", produces = MediaType.APPLICATION_XML_VALUE)
public class ResenjeController {

	@Autowired
	ResenjeService resenjeService;

	@Autowired
	private OdgovorZahtevZaIzjasnjenjeService odgovorService;
	
	@Autowired
	private BrojacService brojacService;
	
	@Autowired
	private DodajResenjeMapper mapper;

	@GetMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ResenjeDTO>> getAll() throws XMLDBException, JAXBException {

		ArrayList<ResenjeDTO> resenja = this.resenjeService.getAll();
		if (resenja.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else
			return new ResponseEntity<>(resenja, HttpStatus.OK);

	}

	@GetMapping(path = "/user", consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ArrayList<ResenjeDTO>> getAllByUser() {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		try {
			ArrayList<ResenjeDTO> resenja = this.resenjeService.getAllForUser(email);
			return new ResponseEntity<ArrayList<ResenjeDTO>>(resenja, HttpStatus.OK);
		} catch (XMLDBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<ResenjeDTO> pronadjiResenjePoId(@PathVariable("id") long id) {
		Resenje r = this.resenjeService.pronadjiResenjePoId(id);

		if (r != null) {
			ResenjeDTO n = new ResenjeDTO();

			String[] params = r.getAbout().split("/");

			n.setId(id);
			n.setBroj_resenja(r.getBroj());
			n.setBroj_zalbe(r.getBrojZalbe().getValue().toString());
			n.setIme_i_prezime(r.getSadrzaj().getUvod().getPodnosilac().getContent());
			n.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
			n.setDatum_zahteva(r.getSadrzaj().getUvod().getDatumZahteva().getValue().toString());
			n.setDatum_resenja(r.getOsnovniPodaci().getDatum().getValue().toString());
			n.setIshod(r.getIshod().getValue());
			n.setEmail(r.getOsnovniPodaci().getKorisnikEmail().getContent());
			
			
			return new ResponseEntity<>(n, HttpStatus.OK);
		} else
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

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<HttpStatus> kreirajResenje(@RequestBody DodajResenjeDTO resenjeDTO) {

		try {

			int index = this.brojacService.dobaviIdResenjaNoIncrement();

			Resenje r = mapper.dtoUKlasu(resenjeDTO, index);

			this.resenjeService.dodajResenje(r);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(path = "/tekst",method = RequestMethod.POST)
	public ResponseEntity<Boolean> kreirajResenjeIzTeksta(@RequestBody String resenje) {

		boolean ok = false;
		try {
			ok = this.resenjeService.dodajResenjeIzTeksta(resenje);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(ok)
			return new ResponseEntity<>(ok, HttpStatus.OK);
		else
			return new ResponseEntity<>(ok, HttpStatus.BAD_REQUEST);

	}

	@RequestMapping(path = "/ispitajObrazlozenje",method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ObrazlozenjeResponse> ispitajObrazlozenje(@RequestBody IspitajObrazlozenjeDTO obrazlozenjeDTO) {
		
		
		Instant currTime = Instant.now().plus(1, ChronoUnit.HOURS);
		
		System.out.println(currTime.toString());
		
		Instant zahtevIzjasnjenjeTime = Instant.parse(obrazlozenjeDTO.getVreme());
		
		System.out.println(zahtevIzjasnjenjeTime.toString());
		
		OdgovorZahtevZaIzjasnjenje odgovor = this.odgovorService.dobaviPoZalbi(obrazlozenjeDTO.getId_zalbe(), obrazlozenjeDTO.getTip());
		
		System.out.println(obrazlozenjeDTO.getId_zalbe());
		System.out.println(obrazlozenjeDTO.getTip());
		
		ObrazlozenjeResponse response = new ObrazlozenjeResponse();
				
		if(odgovor != null) {
			
			response.setSadrzaj(odgovor.getSadrzaj());
			System.out.println(response.getSadrzaj());
			System.out.println("ovaj ovde if");
			return new ResponseEntity<ObrazlozenjeResponse>(response, HttpStatus.OK);
			
		}
		
		if(zahtevIzjasnjenjeTime.plus(1,ChronoUnit.MINUTES).isAfter(currTime)) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
		else {
			response.setSadrzaj("");
			return new ResponseEntity<ObrazlozenjeResponse>(response, HttpStatus.OK);
		}
	
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

		if (!lista.isEmpty())
			return new ResponseEntity<>(lista, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
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
}
