package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KorisnikRegistracijaDto {

	private String imeIPrezime;
	private String email;
	private String lozinka;
	
	public KorisnikRegistracijaDto() {
		super();
	}
	
	
	public KorisnikRegistracijaDto(String imeIPrezime, String email, String lozinka) {
		super();
		this.imeIPrezime = imeIPrezime;
		this.email = email;
		this.lozinka = lozinka;
	}


	public String getImeIPrezime() {
		return imeIPrezime;
	}
	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
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
