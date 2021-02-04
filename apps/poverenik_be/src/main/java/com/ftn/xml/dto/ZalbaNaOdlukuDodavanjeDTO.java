package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name="zalba_na_odluku")
@JsonRootName(value = "zalba_na_odluku")
public class ZalbaNaOdlukuDodavanjeDTO {
	public long id;
	public long broj_zahteva;
	
	public String naziv_primaoca;
	public String adresa_podnosioca;
	public String sediste_zalioca;
	public String naziv_organa;
	
	public XMLGregorianCalendar datum_zahteva;
	
	public String mesto_zalbe;
	public XMLGregorianCalendar datum_zalbe;
	public String drugi_podaci;
	
	public ZalbaNaOdlukuDodavanjeDTO(long id, long broj_zahteva, String naziv_primaoca, String adresa_podnosioca,
			String sediste_zalioca, String naziv_organa, XMLGregorianCalendar datum_zahteva, String mesto_zalbe,
			XMLGregorianCalendar datum_zalbe, String drugi_podaci) {
		super();
		this.id = id;
		this.broj_zahteva = broj_zahteva;
		this.naziv_primaoca = naziv_primaoca;
		this.adresa_podnosioca = adresa_podnosioca;
		this.sediste_zalioca = sediste_zalioca;
		this.naziv_organa = naziv_organa;
		this.datum_zahteva = datum_zahteva;
		this.mesto_zalbe = mesto_zalbe;
		this.datum_zalbe = datum_zalbe;
		this.drugi_podaci = drugi_podaci;
	}

	public String getSediste_zalioca() {
		return sediste_zalioca;
	}

	public void setSediste_zalioca(String sediste_zalioca) {
		this.sediste_zalioca = sediste_zalioca;
	}
	
	public String getMesto_zalbe() {
		return mesto_zalbe;
	}

	public void setMesto_zalbe(String mesto_zalbe) {
		this.mesto_zalbe = mesto_zalbe;
	}

	public XMLGregorianCalendar getDatum_zalbe() {
		return datum_zalbe;
	}

	public void setDatum_zalbe(XMLGregorianCalendar datum_zalbe) {
		this.datum_zalbe = datum_zalbe;
	}

	public String getDrugi_podaci() {
		return drugi_podaci;
	}

	public void setDrugi_podaci(String drugi_podaci) {
		this.drugi_podaci = drugi_podaci;
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

	public String getNaziv_primaoca() {
		return naziv_primaoca;
	}

	public void setNaziv_primaoca(String naziv_primaoca) {
		this.naziv_primaoca = naziv_primaoca;
	}

	public String getAdresa_podnosioca() {
		return adresa_podnosioca;
	}

	public void setAdresa_podnosioca(String adresa_podnosioca) {
		this.adresa_podnosioca = adresa_podnosioca;
	}

	public String getNaziv_organa() {
		return naziv_organa;
	}

	public void setNaziv_organa(String naziv_organa) {
		this.naziv_organa = naziv_organa;
	}

	public XMLGregorianCalendar getDatum_zahteva() {
		return datum_zahteva;
	}

	public void setDatum_zahteva(XMLGregorianCalendar datum_zahteva) {
		this.datum_zahteva = datum_zahteva;
	}
	

}
