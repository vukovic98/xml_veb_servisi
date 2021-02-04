package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZahtevNaprednaDTO {

	private String ime;
	private String mail;
	private String organ;
	private boolean and;

	public ZahtevNaprednaDTO() {
		super();
	}

	public ZahtevNaprednaDTO(String ime, String mail, String organ, boolean and) {
		super();
		this.ime = ime;
		this.mail = mail;
		this.organ = organ;
		this.and = and;
	}

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

}
