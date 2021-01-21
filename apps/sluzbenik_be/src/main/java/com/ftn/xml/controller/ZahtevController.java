package com.ftn.xml.controller;

import java.util.ArrayList;

import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.xml.dto.ZahtevKorisnikaDTO;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.Zahtev;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.service.ZahtevService;

@RestController
@RequestMapping(path = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

	@Autowired
	private ZahtevService zahtevService;

	@GetMapping(path = "/{email}")
	public ResponseEntity<ArrayList<ZahtevKorisnikaDTO>> pronadjiZahteveZaKorisnika(
			@PathVariable("email") String email) {

		ArrayList<ZahtevKorisnikaDTO> zahteviDTO = new ArrayList<>();

		ListaZahtevaZaPristupInformacijama lista = this.zahtevService.pronadjiZahteveZaKorisnika(email);

		if (lista != null) {

			for (ZahtevZaPristupInformacijama z : lista.getZahtevZaPristupInformacijama()) {
				ZahtevKorisnikaDTO n = new ZahtevKorisnikaDTO();

				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);

				n.setId(id);

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
	}

}
