package com.ftn.xml.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ftn.xml.dto.KorisnikDB;
import com.ftn.xml.dto.KorisnikLoginDTO;
import com.ftn.xml.dto.KorisnikSignUpDTO;
import com.ftn.xml.dto.TrenutnoUlogovanKorisnikDTO;
import com.ftn.xml.dto.UserTokenStateDTO;
import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.security.TokenUtils;
import com.ftn.xml.service.CustomUserDetailsService;
import com.ftn.xml.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnik", produces = MediaType.APPLICATION_XML_VALUE)
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;
	

	@RequestMapping(path = "/prijava", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<UserTokenStateDTO> prijava(@RequestBody KorisnikLoginDTO authenticationRequest)
			throws Exception {

		try {

			boolean verified = true;

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getLozinka()));

			// Ubaci korisnika u trenutni security kontekst
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token za tog korisnika
			Korisnik user = (Korisnik) authentication.getPrincipal();

			String email = user.getEmail();
			String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email, verified));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/ulogovanKorisnik")
	public ResponseEntity<TrenutnoUlogovanKorisnikDTO> ulogovanKorisnik() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		if (req.getSession().getAttribute("email") != null) {
			String email = (String) req.getSession().getAttribute("email");

			Korisnik k = this.korisnikService.pronadjiPoMejlu(email);

			if (k != null) {
				TrenutnoUlogovanKorisnikDTO dto = new TrenutnoUlogovanKorisnikDTO(k.getEmail(), k.getUloga());

				return new ResponseEntity<>(dto, HttpStatus.OK);
			} else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PostMapping(path = "/registracija")
	public ResponseEntity<HttpStatus> registracija(@RequestBody KorisnikSignUpDTO entitet) {

		KorisnikDB k = new KorisnikDB();
		k.setEmail(entitet.getEmail());
		k.setIme_i_prezime(entitet.getIme_i_prezime());
		
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		k.setLozinka(enc.encode(entitet.getLozinka()));
		
		k.setUloga("K");

		String userXML = null;

		try {

			XmlMapper mapper = new XmlMapper();
			userXML = mapper.writeValueAsString(k);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean ok = this.korisnikService.registrujKorisnika(k.getEmail(), userXML);

		if (ok)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

}
