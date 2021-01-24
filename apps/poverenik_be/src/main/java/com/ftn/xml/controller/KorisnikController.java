package com.ftn.xml.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ftn.xml.dto.KorisnikDto;
import com.ftn.xml.dto.KorisnikPrijavaDto;
import com.ftn.xml.dto.KorisnikRegistracijaDto;
import com.ftn.xml.dto.UserTokenStateDTO;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.security.TokenUtils;
import com.ftn.xml.service.CustomUserDetailsService;
import com.ftn.xml.service.KorisnikService;


@RestController
@RequestMapping(value = "/korisnik", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(path = "/registracija")
	public ResponseEntity<HttpStatus> registracija(@RequestBody KorisnikRegistracijaDto korisnikDTO) {
		KorisnikDto korisnik = new KorisnikDto();
		korisnik.setEmail(korisnikDTO.getEmail());
		korisnik.setIme_i_prezime(korisnikDTO.getIme_i_prezime());
		korisnik.setUloga("K");
		
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		korisnik.setLozinka(bc.encode(korisnikDTO.getLozinka()));
		
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
	public ResponseEntity<UserTokenStateDTO> prijava(@RequestBody KorisnikPrijavaDto authRequest) throws Exception{
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
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email, user.getUloga()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
