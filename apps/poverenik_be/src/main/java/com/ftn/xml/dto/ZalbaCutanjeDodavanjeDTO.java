package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name = "zalba_cutanje")
@JsonRootName(value = "zalba_cutanje")
public class ZalbaCutanjeDodavanjeDTO {
	public long id;
	public long broj_zahteva;

	
	public String naziv_primaoca;
	public String adresa_ulica_primaoca;
	public String adresa_broj_primaoca;
	public String adresa_mesto_primaoca;

	public String naziv_organa;

	public String razlog_zalbe;
	public String datum_zahteva;
	public String podaci_o_zahtevu_i_informacijama;
	
	public String adresa_ulica_podnosioca;
	public String adresa_broj_podnosioca;
	public String adresa_mesto_podnosioca;
	
	public String drugi_podaci;
	public String mesto_zalbe;
	public String datum_zalbe;

	public ZalbaCutanjeDodavanjeDTO(long id, long broj_zahteva, String naziv_primaoca, String adresa_ulica_primaoca,
			String adresa_broj_primaoca, String adresa_mesto_primaoca, String naziv_organa, String razlog_zalbe,
			String datum_zahteva, String podaci_o_zahtevu_i_informacijama, String adresa_ulica_podnosioca,
			String adresa_broj_podnosioca, String adresa_mesto_podnosioca, String drugi_podaci, String mesto_zalbe,
			String datum_zalbe) {
		super();
		this.id = id;
		this.broj_zahteva = broj_zahteva;
		this.naziv_primaoca = naziv_primaoca;
		this.adresa_ulica_primaoca = adresa_ulica_primaoca;
		this.adresa_broj_primaoca = adresa_broj_primaoca;
		this.adresa_mesto_primaoca = adresa_mesto_primaoca;
		this.naziv_organa = naziv_organa;
		this.razlog_zalbe = razlog_zalbe;
		this.datum_zahteva = datum_zahteva;
		this.podaci_o_zahtevu_i_informacijama = podaci_o_zahtevu_i_informacijama;
		this.adresa_ulica_podnosioca = adresa_ulica_podnosioca;
		this.adresa_broj_podnosioca = adresa_broj_podnosioca;
		this.adresa_mesto_podnosioca = adresa_mesto_podnosioca;
		this.drugi_podaci = drugi_podaci;
		this.mesto_zalbe = mesto_zalbe;
		this.datum_zalbe = datum_zalbe;
	}



	public ZalbaCutanjeDodavanjeDTO() {
		super();
	}



	public String getNaziv_primaoca() {
		return naziv_primaoca;
	}



	public void setNaziv_primaoca(String naziv_primaoca) {
		this.naziv_primaoca = naziv_primaoca;
	}



	public String getAdresa_ulica_primaoca() {
		return adresa_ulica_primaoca;
	}



	public void setAdresa_ulica_primaoca(String adresa_ulica_primaoca) {
		this.adresa_ulica_primaoca = adresa_ulica_primaoca;
	}



	public String getAdresa_broj_primaoca() {
		return adresa_broj_primaoca;
	}



	public void setAdresa_broj_primaoca(String adresa_broj_primaoca) {
		this.adresa_broj_primaoca = adresa_broj_primaoca;
	}



	public String getAdresa_mesto_primaoca() {
		return adresa_mesto_primaoca;
	}



	public void setAdresa_mesto_primaoca(String adresa_mesto_primaoca) {
		this.adresa_mesto_primaoca = adresa_mesto_primaoca;
	}



	public String getRazlog_zalbe() {
		return razlog_zalbe;
	}



	public void setRazlog_zalbe(String razlog_zalbe) {
		this.razlog_zalbe = razlog_zalbe;
	}



	public String getDatum_zahteva() {
		return datum_zahteva;
	}



	public void setDatum_zahteva(String datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}



	public String getPodaci_o_zahtevu_i_informacijama() {
		return podaci_o_zahtevu_i_informacijama;
	}



	public void setPodaci_o_zahtevu_i_informacijama(String podaci_o_zahtevu_i_informacijama) {
		this.podaci_o_zahtevu_i_informacijama = podaci_o_zahtevu_i_informacijama;
	}



	public String getAdresa_ulica_podnosioca() {
		return adresa_ulica_podnosioca;
	}



	public void setAdresa_ulica_podnosioca(String adresa_ulica_podnosioca) {
		this.adresa_ulica_podnosioca = adresa_ulica_podnosioca;
	}



	public String getAdresa_broj_podnosioca() {
		return adresa_broj_podnosioca;
	}



	public void setAdresa_broj_podnosioca(String adresa_broj_podnosioca) {
		this.adresa_broj_podnosioca = adresa_broj_podnosioca;
	}



	public String getAdresa_mesto_podnosioca() {
		return adresa_mesto_podnosioca;
	}



	public void setAdresa_mesto_podnosioca(String adresa_mesto_podnosioca) {
		this.adresa_mesto_podnosioca = adresa_mesto_podnosioca;
	}



	public String getDrugi_podaci() {
		return drugi_podaci;
	}



	public void setDrugi_podaci(String drugi_podaci) {
		this.drugi_podaci = drugi_podaci;
	}



	public String getMesto_zalbe() {
		return mesto_zalbe;
	}



	public void setMesto_zalbe(String mesto_zalbe) {
		this.mesto_zalbe = mesto_zalbe;
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


	public String getDatum_zalbe() {
		return datum_zalbe;
	}

	public void setDatum_zalbe(String datum_zalbe) {
		this.datum_zalbe = datum_zalbe;
	}



}
