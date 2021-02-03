package com.ftn.xml.dto;

public class ObavestenjeFusekiDTO {
	private String adresa_podnosioca;
	private String br_predmeta;
	private String broj_zahteva;
	private String datum_zahteva;
	private String godina_zahteva;
	private String ime_podnosioca;
	private String naziv_podnosioca;
	private String naziv_ustanove;
	private String opis;
	private String sediste_ustanove;

	public ObavestenjeFusekiDTO() {
		super();
	}

	public ObavestenjeFusekiDTO(String adresa_podnosioca, String br_predmeta, String broj_zahteva, String datum_zahteva,
			String godina_zahteva, String ime_podnosioca, String naziv_podnosioca, String naziv_ustanove, String opis,
			String sediste_ustanove) {
		super();
		this.adresa_podnosioca = adresa_podnosioca;
		this.br_predmeta = br_predmeta;
		this.broj_zahteva = broj_zahteva;
		this.datum_zahteva = datum_zahteva;
		this.godina_zahteva = godina_zahteva;
		this.ime_podnosioca = ime_podnosioca;
		this.naziv_podnosioca = naziv_podnosioca;
		this.naziv_ustanove = naziv_ustanove;
		this.opis = opis;
		this.sediste_ustanove = sediste_ustanove;
	}

	public String getAdresa_podnosioca() {
		return adresa_podnosioca;
	}

	public void setAdresa_podnosioca(String adresa_podnosioca) {
		this.adresa_podnosioca = adresa_podnosioca;
	}

	public String getBr_predmeta() {
		return br_predmeta;
	}

	public void setBr_predmeta(String br_predmeta) {
		this.br_predmeta = br_predmeta;
	}

	public String getBroj_zahteva() {
		return broj_zahteva;
	}

	public void setBroj_zahteva(String broj_zahteva) {
		this.broj_zahteva = broj_zahteva;
	}

	public String getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}

	public String getGodina_zahteva() {
		return godina_zahteva;
	}

	public void setGodina_zahteva(String godina_zahteva) {
		this.godina_zahteva = godina_zahteva;
	}

	public String getIme_podnosioca() {
		return ime_podnosioca;
	}

	public void setIme_podnosioca(String ime_podnosioca) {
		this.ime_podnosioca = ime_podnosioca;
	}

	public String getNaziv_podnosioca() {
		return naziv_podnosioca;
	}

	public void setNaziv_podnosioca(String naziv_podnosioca) {
		this.naziv_podnosioca = naziv_podnosioca;
	}

	public String getNaziv_ustanove() {
		return naziv_ustanove;
	}

	public void setNaziv_ustanove(String naziv_ustanove) {
		this.naziv_ustanove = naziv_ustanove;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSediste_ustanove() {
		return sediste_ustanove;
	}

	public void setSediste_ustanove(String sediste_ustanove) {
		this.sediste_ustanove = sediste_ustanove;
	}

}
