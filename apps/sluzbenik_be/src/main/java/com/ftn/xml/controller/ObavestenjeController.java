package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

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

import com.ftn.xml.dto.ObavestenjeDTO;
import com.ftn.xml.model.obavestenje.ListaObavestenja;
import com.ftn.xml.model.obavestenje.Obavestenje;
import com.ftn.xml.service.ObavestenjeService;

@RestController
@RequestMapping(path = "/obavestenje", produces = MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {

	@Autowired
	private ObavestenjeService obavestenjeService;

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
}