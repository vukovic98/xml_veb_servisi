package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZahtevSluzbenikaDTO {
	public long id;
	public String naziv_ustanove;
	public String opis_zahteva;
	public String opis_trazene_informacije;
	public String datum_zahteva;
	public String kontakt;
	public String korisnik;
	public boolean odobren;
	public String sediste_ustanove;
	public String naziv_podnosioca;
	public String grad_podnosioca;
	public String ulica_podnosioca;
	public String broj_ulice_podnosioca;
	public String korisnik_email;
	public String status;

	public ZahtevSluzbenikaDTO() {
		super();
	}

	public ZahtevSluzbenikaDTO(long id, String naziv_ustanove, String opis_zahteva, String opis_trazene_informacije,
			String datum_zahteva, String kontakt, String korisnik, boolean odobren, String status) {
		super();
		this.id = id;
		this.naziv_ustanove = naziv_ustanove;
		this.opis_zahteva = opis_zahteva;
		this.opis_trazene_informacije = opis_trazene_informacije;
		this.datum_zahteva = datum_zahteva;
		this.kontakt = kontakt;
		this.korisnik = korisnik;
		this.odobren = odobren;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public String getKorisnik_email() {
		return korisnik_email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setKorisnik_email(String korisnik_email) {
		this.korisnik_email = korisnik_email;
	}

	public String getGrad_podnosioca() {
		return grad_podnosioca;
	}

	public void setGrad_podnosioca(String grad_podnosioca) {
		this.grad_podnosioca = grad_podnosioca;
	}

	public String getUlica_podnosioca() {
		return ulica_podnosioca;
	}

	public void setUlica_podnosioca(String ulica_podnosioca) {
		this.ulica_podnosioca = ulica_podnosioca;
	}

	public String getBroj_ulice_podnosioca() {
		return broj_ulice_podnosioca;
	}

	public void setBroj_ulice_podnosioca(String broj_ulice_podnosioca) {
		this.broj_ulice_podnosioca = broj_ulice_podnosioca;
	}

	public String getSediste_ustanove() {
		return sediste_ustanove;
	}

	public void setSediste_ustanove(String sediste_ustanove) {
		this.sediste_ustanove = sediste_ustanove;
	}

	public String getNaziv_podnosioca() {
		return naziv_podnosioca;
	}

	public void setNaziv_podnosioca(String naziv_podnosioca) {
		this.naziv_podnosioca = naziv_podnosioca;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv_ustanove() {
		return naziv_ustanove;
	}

	public void setNaziv_ustanove(String naziv_ustanove) {
		this.naziv_ustanove = naziv_ustanove;
	}

	public String getOpis_zahteva() {
		return opis_zahteva;
	}

	public void setOpis_zahteva(String opis_zahteva) {
		this.opis_zahteva = opis_zahteva;
	}

	public String getOpis_trazene_informacije() {
		return opis_trazene_informacije;
	}

	public void setOpis_trazene_informacije(String opis_trazene_informacije) {
		this.opis_trazene_informacije = opis_trazene_informacije;
	}

	public String getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(String korisnik) {
		this.korisnik = korisnik;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
	}

}
