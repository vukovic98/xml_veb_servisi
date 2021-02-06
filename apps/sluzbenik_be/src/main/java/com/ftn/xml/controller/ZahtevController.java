package com.ftn.xml.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.DodajZahtevDTO;
import com.ftn.xml.dto.ZahtevKorisnikaDTO;
import com.ftn.xml.dto.ZahtevNaprednaDTO;
import com.ftn.xml.dto.ZahtevSluzbenikaDTO;
import com.ftn.xml.mapper.DodajZahtevMaper;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.Zahtev;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.BrojacRepository;
import com.ftn.xml.service.BrojacService;
import com.ftn.xml.service.KorisnikService;
import com.ftn.xml.service.ObavestenjeService;
import com.ftn.xml.service.ZahtevService;

@RestController
@RequestMapping(path = "/zahtev", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevController {

	@Autowired
	private ZahtevService zahtevService;

	@Autowired
	private ObavestenjeService obavestenjeService;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private BrojacService brojacService;

	@Autowired
	private DodajZahtevMaper mapper;

	@PostMapping("/napredna-pretraga")
	public ResponseEntity<ArrayList<ZahtevZaPristupInformacijama>> naprednaPretraga(
			@RequestBody ZahtevNaprednaDTO dto) {
		String ime = !dto.getIme().equalsIgnoreCase("null") ? "\"" + dto.getIme() + "\"" : null;
		String mail = !dto.getMail().equalsIgnoreCase("null") ? "\"" + dto.getMail() + "\"" : null;
		String organ = !dto.getOrgan().equalsIgnoreCase("null") ? "\"" + dto.getOrgan() + "\"" : null;

		ListaZahtevaZaPristupInformacijama lista = this.zahtevService.naprednaPretraga(ime, mail, organ, dto.isAnd());

		if (!lista.getZahtevZaPristupInformacijama().isEmpty())
			return new ResponseEntity<>((ArrayList<ZahtevZaPristupInformacijama>)lista.getZahtevZaPristupInformacijama(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/pretraga/{text}")
	public ResponseEntity<ArrayList<ZahtevZaPristupInformacijama>> pretraga(@PathVariable("text") String text) {
		ListaZahtevaZaPristupInformacijama lista = this.zahtevService.pretraga(text);

		if (!lista.getZahtevZaPristupInformacijama().isEmpty())
			return new ResponseEntity<>(
					(ArrayList<ZahtevZaPristupInformacijama>) lista.getZahtevZaPristupInformacijama(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping
	public ResponseEntity<ArrayList<ZahtevSluzbenikaDTO>> pronadjiSveZahteve() {
		ArrayList<ZahtevSluzbenikaDTO> zahteviDTO = new ArrayList<>();

		ListaZahtevaZaPristupInformacijama lista = this.zahtevService.pronadjiSveZahteve();

		if (lista != null) {

			for (ZahtevZaPristupInformacijama z : lista.getZahtevZaPristupInformacijama()) {
				ZahtevSluzbenikaDTO n = new ZahtevSluzbenikaDTO();

				String[] params = z.getAbout().split("/");
				long id = Long.parseLong(params[params.length - 1]);

				n.setId(id);
				n.setOdobren(this.obavestenjeService.proveraPotvrdeZahteva(id));

				String xmlDate = z.getPodnozje().getMestoIDatum().getDatumZahteva().getValue();
				n.setDatum_zahteva(xmlDate);
				n.setKontakt(z.getPodnozje().getInformacijeOTraziocu().getKontakt().getValue().toString());
				n.setNaziv_ustanove(z.getPodaciOOrganu().getNaziv().getContent());
				n.setOpis_trazene_informacije(z.getSadrzaj().getOpisTrazeneInformacije().getContent());
				n.setKorisnik(z.getPodnozje().getInformacijeOTraziocu().getImeIPrezime().getContent());
				n.setSediste_ustanove(z.getPodaciOOrganu().getSediste().getContent());
				n.setNaziv_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getImeIPrezime().getContent());
				n.setGrad_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getAdresa().getMesto().getContent());
				n.setUlica_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getAdresa().getUlica().getContent());
				n.setBroj_ulice_podnosioca(
						z.getPodnozje().getInformacijeOTraziocu().getAdresa().getBroj().getValue() + "");
				n.setKorisnik_email(z.getPodnozje().getInformacijeOTraziocu().getKorisnikEmail().getContent());
				n.setStatus(z.getStatus());

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

	@GetMapping(path = "/{id}")
	public ResponseEntity<ZahtevSluzbenikaDTO> pronadjiZahtevPoId(@PathVariable("id") long id) {
		ZahtevZaPristupInformacijama z = this.zahtevService.pronadjiZahtevPoId(id);

		if (z != null) {
			ZahtevSluzbenikaDTO n = new ZahtevSluzbenikaDTO();

			String[] params = z.getAbout().split("/");

			n.setId(id);
			n.setOdobren(this.obavestenjeService.proveraPotvrdeZahteva(id));

			String xmlDate = z.getPodnozje().getMestoIDatum().getDatumZahteva().getValue();
			n.setDatum_zahteva(xmlDate);
			n.setKontakt(z.getPodnozje().getInformacijeOTraziocu().getKontakt().getValue().toString());
			n.setNaziv_ustanove(z.getPodaciOOrganu().getNaziv().getContent());
			n.setOpis_trazene_informacije(z.getSadrzaj().getOpisTrazeneInformacije().getContent());
			n.setKorisnik(z.getPodnozje().getInformacijeOTraziocu().getImeIPrezime().getContent());
			n.setSediste_ustanove(z.getPodaciOOrganu().getSediste().getContent());
			n.setGrad_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getAdresa().getMesto().getContent());
			n.setUlica_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getAdresa().getUlica().getContent());
			n.setBroj_ulice_podnosioca(z.getPodnozje().getInformacijeOTraziocu().getAdresa().getBroj().getValue() + "");
			n.setKorisnik_email(z.getPodnozje().getInformacijeOTraziocu().getKorisnikEmail().getContent());

			for (Object o : z.getSadrzaj().getZahtevi().getContent()) {

				if (o instanceof Zahtev) {
					Zahtev oZ = (Zahtev) o;

					n.setOpis_zahteva(oZ.getOpisZahteva());
				}
			}
			return new ResponseEntity<>(n, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(path = "/ulogovanKorisnik")
	public ResponseEntity<ArrayList<ZahtevKorisnikaDTO>> pronadjiZahteveZaKorisnika() {

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

					String xmlDate = z.getPodnozje().getMestoIDatum().getDatumZahteva().getValue();
					n.setDatum_zahteva(xmlDate);
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

	@GetMapping("/generisiHTML/{zahtev_id}")
	public ResponseEntity<byte[]> generisiHTML(@PathVariable("zahtev_id") long zahtev_id) {

		String file_path = this.zahtevService.generisiHTML(zahtev_id);

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
	public ResponseEntity<HttpStatus> kreirajZahtev(@RequestBody DodajZahtevDTO zahtevDto) {

		try {
			String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

			Korisnik k = this.korisnikService.pronadjiPoMejlu(email);
			int index = this.brojacService.dobaviIdZahteva();

			ZahtevZaPristupInformacijama z = mapper.dtoUKlasu(zahtevDto, k.getEmail(), k.getImeIPrezime(), index);

			this.zahtevService.dodajZahtev(z, index);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<HttpStatus> odbijZahtev(@PathVariable("id") String id) {
		boolean ok = this.zahtevService.odbijZahtev(id);

		if (ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/generisiJSON/{zahtev_id}")
	public ResponseEntity<byte[]> generisiJSON(@PathVariable("zahtev_id") long zahtev_id) throws XMLDBException {

		String filePath = "src/main/resources/static/json/zahtev_" + zahtev_id + ".json";

		try {
			this.zahtevService.generisiJSON(zahtev_id);
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(fileInputStream), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}
	
	@GetMapping("/generisiRDF/{zahtev_id}")
	public ResponseEntity<byte[]> generisiRDF(@PathVariable("zahtev_id") long zahtev_id) throws XMLDBException {

		String filePath = "src/main/resources/static/rdf/zahtev_" + zahtev_id + ".rdf";
		try {
			this.zahtevService.generisiRDF(zahtev_id);
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
