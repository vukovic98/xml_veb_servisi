package com.ftn.xml.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ftn.xml.model.korisnik.Korisnik;
import com.ftn.xml.service.KorisnikService;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private KorisnikService korisnikService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		Korisnik user = this.korisnikService.pronadjiPoMejlu(username);

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		

		if (enc.matches((String)authentication.getCredentials(), user.getPassword()))
			return user;
		else
			throw new BadCredentialsException("Password incorrect");
			
	}

}
