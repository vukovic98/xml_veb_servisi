package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KorisnikLoginDTO {
	private String email;
	private String lozinka;

	public KorisnikLoginDTO() {
		super();
	}

	public KorisnikLoginDTO(String email, String lozinka) {
		super();
		this.email = email;
		this.lozinka = lozinka;
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
