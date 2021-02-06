package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResenjeDTO {

	private long id;
	private String broj_resenja;
	private String broj_zalbe;
	private String ime_i_prezime;
	private String ustanova;
	private String datum_zahteva;
	private String datum_resenja;
	private String ishod;
	
	private String email;

	public ResenjeDTO() {
		super();
	}

	public ResenjeDTO(long id, String broj_resenja, String broj_zalbe, String ime_i_prezime, String ustanova,
			String datum_zahteva, String datum_resenja, String ishod) {
		super();
		this.id = id;
		this.broj_resenja = broj_resenja;
		this.broj_zalbe = broj_zalbe;
		this.ime_i_prezime = ime_i_prezime;
		this.ustanova = ustanova;
		this.datum_zahteva = datum_zahteva;
		this.datum_resenja = datum_resenja;
		this.ishod = ishod;
	}

	public String getBroj_resenja() {
		return broj_resenja;
	}

	public void setBroj_resenja(String broj_resenja) {
		this.broj_resenja = broj_resenja;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIme_i_prezime() {
		return ime_i_prezime;
	}

	public void setIme_i_prezime(String ime_i_prezime) {
		this.ime_i_prezime = ime_i_prezime;
	}

	public String getUstanova() {
		return ustanova;
	}

	public void setUstanova(String ustanova) {
		this.ustanova = ustanova;
	}

	public String getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}

	public String getDatum_resenja() {
		return datum_resenja;
	}

	public void setDatum_resenja(String datum_resenja) {
		this.datum_resenja = datum_resenja;
	}

	public String getIshod() {
		return ishod;
	}

	public void setIshod(String ishod) {
		this.ishod = ishod;
	}

	public String getBroj_zalbe() {
		return broj_zalbe;
	}

	public void setBroj_zalbe(String broj_zalbe) {
		this.broj_zalbe = broj_zalbe;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
