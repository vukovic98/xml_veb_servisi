package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "korisnik")
@JsonRootName(value = "korisnik")
public class KorisnikDto {
	private String ime_i_prezime;
	private String email;
	private String lozinka;
	private String uloga;

	public KorisnikDto() {
		super();
	}

	public KorisnikDto(String ime_i_prezime, String email, String lozinka, String uloga) {
		super();
		this.ime_i_prezime = ime_i_prezime;
		this.email = email;
		this.lozinka = lozinka;
		this.uloga = uloga;
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

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

}
