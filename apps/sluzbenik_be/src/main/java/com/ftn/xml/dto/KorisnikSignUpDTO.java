package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KorisnikSignUpDTO {

	private String ime_i_prezime;
	private String email;
	private String lozinka;

	public KorisnikSignUpDTO() {
		super();
	}

	public KorisnikSignUpDTO(String ime_i_prezime, String email, String lozinka) {
		super();
		this.ime_i_prezime = ime_i_prezime;
		this.email = email;
		this.lozinka = lozinka;
	}

	public String getIme_i_prezime() {
		return ime_i_prezime;
	}

	public void setIme_i_prezime(String ime_i_prezime) {
		this.ime_i_prezime = ime_i_prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

}
