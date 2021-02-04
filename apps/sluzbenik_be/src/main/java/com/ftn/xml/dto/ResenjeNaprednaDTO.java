package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResenjeNaprednaDTO {

	private String zalba;
	private String ishod;
	private String korisnik;
	private boolean and;

	public ResenjeNaprednaDTO() {
		super();
	}

	public ResenjeNaprednaDTO(String zalba, String ishod, String korisnik, boolean and) {
		super();
		this.zalba = zalba;
		this.ishod = ishod;
		this.korisnik = korisnik;
		this.and = and;
	}

	public String getZalba() {
		return zalba;
	}

	public void setZalba(String zalba) {
		this.zalba = zalba;
	}

	public String getIshod() {
		return ishod;
	}

	public void setIshod(String ishod) {
		this.ishod = ishod;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

}
