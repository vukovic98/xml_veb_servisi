package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.exist.xmldb.EXistResource;
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
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.ObavestenjeDTO;
import com.ftn.xml.dto.ObavestenjeNaprednaDTO;
import com.ftn.xml.dto.ZahtevNaprednaDTO;
import com.ftn.xml.model.obavestenje.ListaObavestenja;
import com.ftn.xml.model.obavestenje.Obavestenje;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.service.ObavestenjeService;

@RestController
@RequestMapping(path = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {

	@Autowired
	private ObavestenjeService obavestenjeService;

	@GetMapping(path = "/pretraga/{text}")
	public ResponseEntity<ArrayList<Obavestenje>> pretraga(@PathVariable("text") String text) {
		ListaObavestenja lista = this.obavestenjeService.pretraga(text);
		
		if(!lista.getObavestenje().isEmpty())
			return new ResponseEntity<>((ArrayList<Obavestenje>)lista.getObavestenje(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<Obavestenje>> naprednaPretraga(
			@RequestBody ObavestenjeNaprednaDTO dto) {
		String predmet = !dto.getPredmet().equalsIgnoreCase("null") ? "\"" + dto.getPredmet() + "\"" : null;
		String zahtev = !dto.getZahtev().equalsIgnoreCase("null") ? "\"" + dto.getZahtev() + "\"" : null;
		String ime = !dto.getIme().equalsIgnoreCase("null") ? "\"" + dto.getIme() + "\"" : null;

		ListaObavestenja lista = this.obavestenjeService.naprednaPretraga(predmet, zahtev, ime, dto.isAnd());

		if (!lista.getObavestenje().isEmpty())
			return new ResponseEntity<>((ArrayList<Obavestenje>)lista.getObavestenje(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<ArrayList<ObavestenjeDTO>> pronadjiSvaObavestenja() {
		ListaObavestenja lista = this.obavestenjeService.pronadjiSvaObavestenja();
		ArrayList<ObavestenjeDTO> dtos = new ArrayList<>();

		for (Obavestenje o : lista.getObavestenje()) {
			ObavestenjeDTO od = new ObavestenjeDTO();

			String[] about = o.getAbout().split("/");
			String id = about[about.length - 1];

			od.setId(id);

			od.setBrojKancelarije(o.getSadrzaj().getBrojKancelarije() + "");
			od.setBrojPredmeta(o.getOsnovniPodaci().getPodaciOOrganu().getBrojPredmeta().getValue() + "");
			od.setBrojZahteva(o.getBrojZahteva().getValue() + "");
			od.setDatumUvida(o.getSadrzaj().getDatumUvida());
			od.setDatumZahteva(o.getOsnovniPodaci().getPodaciOOrganu().getDatumZahteva().getValue());
			od.setImeTrazioca(o.getOsnovniPodaci().getPodaciOPodnosiocu().getImeIPrezime().getContent());
			od.setNazivOrgana(o.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getContent());
			od.setOpisInformacije(o.getSadrzaj().getOpisTrazeneInformacije().getContent());

			dtos.add(od);
		}

		if (!dtos.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/ulogovanKorisnik")
	public ResponseEntity<ArrayList<ObavestenjeDTO>> pronadjiSvaObavestenjaZaKorisnika() {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		if (email != null) {

			ListaObavestenja lista = this.obavestenjeService.pronadjiSvaObavestenjaZaKorisnika(email);
			ArrayList<ObavestenjeDTO> dtos = new ArrayList<>();

			for (Obavestenje o : lista.getObavestenje()) {
				ObavestenjeDTO od = new ObavestenjeDTO();

				String[] about = o.getAbout().split("/");
				String id = about[about.length - 1];

				od.setId(id);
				od.setBrojKancelarije(o.getSadrzaj().getBrojKancelarije() + "");
				od.setBrojPredmeta(o.getOsnovniPodaci().getPodaciOOrganu().getBrojPredmeta().getValue() + "");
				od.setBrojZahteva(o.getBrojZahteva().getValue() + "");
				od.setDatumUvida(o.getSadrzaj().getDatumUvida());
				od.setDatumZahteva(o.getOsnovniPodaci().getPodaciOOrganu().getDatumZahteva().getValue());
				od.setImeTrazioca(o.getOsnovniPodaci().getPodaciOPodnosiocu().getImeIPrezime().getContent());
				od.setNazivOrgana(o.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getContent());
				od.setOpisInformacije(o.getSadrzaj().getOpisTrazeneInformacije().getContent());

				dtos.add(od);
			}

			if (!dtos.isEmpty())
				return new ResponseEntity<>(dtos, HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/generisiPDF/{obavestenje_id}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("obavestenje_id") long obavestenje_id) {

		String file_path = this.obavestenjeService.generisiPDF(obavestenje_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("/generisiHTML/{obavestenje_id}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("obavestenje_id") long obavestenje_id) {

		String file_path = this.obavestenjeService.generisiHTML(obavestenje_id);

		try {
			File file = new File(file_path);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(path = "/getAll")
	public ResponseEntity<ListaObavestenja> nadjisva() {
		return new ResponseEntity<>(this.obavestenjeService.pronadjiSvaObavestenja(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpStatus> kreirajObavestenje(@RequestBody String xml) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.obavestenje");
			Unmarshaller unmarshaller = context.createUnmarshaller();

			StringReader res = new StringReader(xml);
			Obavestenje o = (Obavestenje) unmarshaller.unmarshal(res);
			
			this.obavestenjeService.dodajObavestenje(xml, o);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}