package com.ftn.xml.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ftn.xml.dto.KorisnikPrijavaDto;
import com.ftn.xml.dto.KorisnikRegistracijaDto;
import com.ftn.xml.dto.UserTokenStateDTO;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.security.TokenUtils;
import com.ftn.xml.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnik", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(path = "/registracija")
	public ResponseEntity<HttpStatus> registracija(@RequestBody KorisnikRegistracijaDto korisnikDTO) {
		Korisnik korisnik = new Korisnik();
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setImeIPrezime(korisnikDTO.getIme_i_prezime());
		korisnik.setLozinka(korisnikDTO.getLozinka());
		korisnik.setUloga("K");
		String userXML = null;

		try {

			XmlMapper mapper = new XmlMapper();
			userXML = mapper.writeValueAsString(korisnik);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean ok = this.korisnikService.registruj(korisnik.getEmail(), userXML);

		if (ok)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

	}

	@RequestMapping(path = "/prijava", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<UserTokenStateDTO> prijava(@RequestBody KorisnikPrijavaDto authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authRequest.getEmail(), authRequest.getLozinka()));

			// Ubaci korisnika u trenutni security kontekst
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token za tog korisnika
			Korisnik user = (Korisnik) authentication.getPrincipal();

			String email = user.getEmail();
			String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
