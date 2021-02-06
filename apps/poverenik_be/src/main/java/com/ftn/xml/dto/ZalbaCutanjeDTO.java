package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZalbaCutanjeDTO {
	public long id;
	public long broj_zahteva;
	public String naziv_organa;
	public String ime_i_prezime;
	public String datum_zalbe;
	public String razresena;
	public String email;
	public String datum_zahteva;
	public String tip_zalbe = "cutanje";
	
	public ZalbaCutanjeDTO() {
		super();
	}

	public ZalbaCutanjeDTO(long id, long broj_zahteva, String naziv_organa, String ime_i_prezime, String datum_zalbe,
			String razresena) {
		super();
		this.id = id;
		this.broj_zahteva = broj_zahteva;
		this.naziv_organa = naziv_organa;
		this.ime_i_prezime = ime_i_prezime;
		this.datum_zalbe = datum_zalbe;
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

	public String getIme_i_prezime() {
		return ime_i_prezime;
	}

	public void setIme_i_prezime(String ime_i_prezime) {
		this.ime_i_prezime = ime_i_prezime;
	}

	public String getDatum_zalbe() {
		return datum_zalbe;
	}

	public void setDatum_zalbe(String datum_zalbe) {
		this.datum_zalbe = datum_zalbe;
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
