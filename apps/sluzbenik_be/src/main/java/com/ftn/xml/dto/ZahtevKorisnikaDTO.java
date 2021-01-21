package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZahtevKorisnikaDTO {

	public long id;
	public String naziv_ustanove;
	public String opis_zahteva;
	public String opis_trazene_informacije;
	public String datum_zahteva;
	public String kontakt;
	public boolean odobren;

	public ZahtevKorisnikaDTO() {
		super();
	}

	public ZahtevKorisnikaDTO(long id, String naziv_ustanove, String opis_zahteva, String opis_trazene_informacije,
			String datum_zahteva, String kontakt, boolean odobren) {
		super();
		this.id = id;
		this.naziv_ustanove = naziv_ustanove;
		this.opis_zahteva = opis_zahteva;
		this.opis_trazene_informacije = opis_trazene_informacije;
		this.datum_zahteva = datum_zahteva;
		this.kontakt = kontakt;
		this.odobren = odobren;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isOdobren() {
		return odobren;
	}

	public void setOdobren(boolean odobren) {
		this.odobren = odobren;
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

}
