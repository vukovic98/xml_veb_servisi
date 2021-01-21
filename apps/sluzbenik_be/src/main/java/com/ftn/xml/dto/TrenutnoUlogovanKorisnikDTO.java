package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrenutnoUlogovanKorisnikDTO {

	private String email;
	private String uloga;

	public TrenutnoUlogovanKorisnikDTO() {
		super();
	}

	public TrenutnoUlogovanKorisnikDTO(String email, String uloga) {
		super();
		this.email = email;
		this.uloga = uloga;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

}
