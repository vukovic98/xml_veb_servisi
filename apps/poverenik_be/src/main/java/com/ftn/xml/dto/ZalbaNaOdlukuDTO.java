package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZalbaNaOdlukuDTO {
	
	public long id;
	public long broj_zahteva;
	public String naziv_organa;
	public String ime;
	public String prezime;
	public String datum;
	public String razresena;
	public String email;
	public String datum_zahteva;
	public String tip_zalbe = "odluka";
	
	public ZalbaNaOdlukuDTO() {
		super();
	}

	public ZalbaNaOdlukuDTO(long id, long broj_zahteva, String naziv_organa, String ime, String prezime, String datum,
			String razresena) {
		super();
		this.id = id;
		this.broj_zahteva = broj_zahteva;
		this.naziv_organa = naziv_organa;
		this.ime = ime;
		this.prezime = prezime;
		this.datum = datum;
		this.razresena = razresena;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBroj_zahteva() {
		return broj_zahteva;
	}

	public void setBroj_zahteva(long broj_zahteva) {
		this.broj_zahteva = broj_zahteva;
	}

	public String getNaziv_organa() {
		return naziv_organa;
	}

	public void setNaziv_organa(String naziv_organa) {
		this.naziv_organa = naziv_organa;
	}

	public String getDatum() {
		return datum;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getRazresena() {
		return razresena;
	}

	public void setRazresena(String razresena) {
		this.razresena = razresena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}
	
	

}
