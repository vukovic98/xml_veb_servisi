package com.ftn.xml.dto;

public class ZahtevFusekiDTO {
	private int broj_kuce_trazioca;
	private String datum_zahteva;
	private String ime_trazioca;
	private String kontakt_trazioca;
	private String korisnik;
	private String mesto_trazioca;
	private String mesto_zahteva;
	private String naziv_ustanove;
	private String opis_informacije;
	private String sediste_ustanove;
	private String ulica_trazioca;

	public ZahtevFusekiDTO() {
		super();
	}

	public ZahtevFusekiDTO(int broj_kuce_trazioca, String datum_zahteva, String ime_trazioca, String kontakt_trazioca,
			String korisnik, String mesto_trazioca, String mesto_zahteva, String naziv_ustanove,
			String opis_informacije, String sediste_ustanove, String ulica_trazioca) {
		super();
		this.broj_kuce_trazioca = broj_kuce_trazioca;
		this.datum_zahteva = datum_zahteva;
		this.ime_trazioca = ime_trazioca;
		this.kontakt_trazioca = kontakt_trazioca;
		this.korisnik = korisnik;
		this.mesto_trazioca = mesto_trazioca;
		this.mesto_zahteva = mesto_zahteva;
		this.naziv_ustanove = naziv_ustanove;
		this.opis_informacije = opis_informacije;
		this.sediste_ustanove = sediste_ustanove;
		this.ulica_trazioca = ulica_trazioca;
	}

	public int getBroj_kuce_trazioca() {
		return broj_kuce_trazioca;
	}

	public void setBroj_kuce_trazioca(int broj_kuce_trazioca) {
		this.broj_kuce_trazioca = broj_kuce_trazioca;
	}

	public String getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}

	public String getIme_trazioca() {
		return ime_trazioca;
	}

	public void setIme_trazioca(String ime_trazioca) {
		this.ime_trazioca = ime_trazioca;
	}

	public String getKontakt_trazioca() {
		return kontakt_trazioca;
	}

	public void setKontakt_trazioca(String kontakt_trazioca) {
		this.kontakt_trazioca = kontakt_trazioca;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public String getMesto_trazioca() {
		return mesto_trazioca;
	}

	public void setMesto_trazioca(String mesto_trazioca) {
		this.mesto_trazioca = mesto_trazioca;
	}

	public String getMesto_zahteva() {
		return mesto_zahteva;
	}

	public void setMesto_zahteva(String mesto_zahteva) {
		this.mesto_zahteva = mesto_zahteva;
	}

	public String getNaziv_ustanove() {
		return naziv_ustanove;
	}

	public void setNaziv_ustanove(String naziv_ustanove) {
		this.naziv_ustanove = naziv_ustanove;
	}

	public String getOpis_informacije() {
		return opis_informacije;
	}

	public void setOpis_informacije(String opis_informacije) {
		this.opis_informacije = opis_informacije;
	}

	public String getSediste_ustanove() {
		return sediste_ustanove;
	}

	public void setSediste_ustanove(String sediste_ustanove) {
		this.sediste_ustanove = sediste_ustanove;
	}

	public String getUlica_trazioca() {
		return ulica_trazioca;
	}

	public void setUlica_trazioca(String ulica_trazioca) {
		this.ulica_trazioca = ulica_trazioca;
	}

}
