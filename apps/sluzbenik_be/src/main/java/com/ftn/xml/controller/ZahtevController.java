package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ftn.xml.dto.ZahtevKorisnikaDTO;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.Zahtev;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.service.ObavestenjeService;
import com.ftn.xml.service.ZahtevService;

@RestController
@RequestMapping(path = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private ObavestenjeService obavestenjeService;

	@GetMapping(path = "/ulogovanKorisnik")
	public ResponseEntity<ArrayList<ZahtevKorisnikaDTO>> pronadjiZahteveZaKorisnika() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		if (email != null) {
			ArrayList<ZahtevKorisnikaDTO> zahteviDTO = new ArrayList<>();

			ListaZahtevaZaPristupInformacijama lista = this.zahtevService.pronadjiZahteveZaKorisnika(email);

			if (lista != null) {

				for (ZahtevZaPristupInformacijama z : lista.getZahtevZaPristupInformacijama()) {
					ZahtevKorisnikaDTO n = new ZahtevKorisnikaDTO();

					String[] params = z.getAbout().split("/");
					long id = Long.parseLong(params[params.length - 1]);

					n.setId(id);
					n.setOdobren(this.obavestenjeService.proveraPotvrdeZahteva(id));

					XMLGregorianCalendar xmlDate = z.getPodnozje().getMestoIDatum().getDatumZahteva().getValue();
					String date = xmlDate.getYear() + "-" + xmlDate.getMonth() + "-" + xmlDate.getDay();
					n.setDatum_zahteva(date);
					n.setKontakt(z.getPodnozje().getInformacijeOTraziocu().getKontakt().getValue().toString());
					n.setNaziv_ustanove(z.getPodaciOOrganu().getNaziv().getContent());
					n.setOpis_trazene_informacije(z.getSadrzaj().getOpisTrazeneInformacije().getContent());

					for (Object o : z.getSadrzaj().getZahtevi().getContent()) {

						if (o instanceof Zahtev) {
							Zahtev oZ = (Zahtev) o;

							n.setOpis_zahteva(oZ.getOpisZahteva());
						}
					}

					zahteviDTO.add(n);
				}

				return new ResponseEntity<>(zahteviDTO, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/generisiPDF/{zahtev_id}")
	public ResponseEntity<byte[]> generisiPDF(@PathVariable("zahtev_id") long zahtev_id) {

		String file_path = this.zahtevService.generisiPDF(zahtev_id);
		
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
