package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObavestenjeNaprednaDTO {

	private String predmet;
	private String zahtev;
	private String ime;
	private boolean and;

	public ObavestenjeNaprednaDTO() {
		super();
	}

	public ObavestenjeNaprednaDTO(String predmet, String zahtev, String ime, boolean and) {
		super();
		this.predmet = predmet;
		this.zahtev = zahtev;
		this.ime = ime;
		this.and = and;
	}

	public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	public String getZahtev() {
		return zahtev;
	}

	public void setZahtev(String zahtev) {
		this.zahtev = zahtev;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

}
