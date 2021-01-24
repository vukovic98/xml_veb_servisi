package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DodajZahtevDTO {
	
//	<dodajZahtevDTO>
//    <naziv_organa>gbhunjmk</naziv_organa>
//    <mesto_organa>gbhnjmk</mesto_organa>
//    <opis_informacije>gbhnjmk</opis_informacije>
//    <mesto_trazioca>gbhnjmk</mesto_trazioca>
//    <ulica_trazioca>gybhunjimk</ulica_trazioca>
//    <broj_trazioca>gbhunjimk,</broj_trazioca>
//    <kontakt>gbhnjmk</kontakt>
//    <opis_zahteva>zahtev_dostava</opis_zahteva>
//    <nacin_dostave>drugi</nacin_dostave>
//    <drugi_nacin>sfdgfdfhj</drugi_nacin>
//</dodajZahtevDTO>

	private String naziv_organa;
	private String mesto_organa;
	private String opis_informacije;
	private String mesto_trazioca;
	private String ulica_trazioca;
	private int broj_trazioca;
	private String kontakt;
	private String drugi_nacin;
	private OpisZahteva opis_zahteva;
	private NacinDostave nacin_dostave;

	public DodajZahtevDTO() {
		super();
	}

	public DodajZahtevDTO(String naziv_organa, String mesto_organa, String opis_informacije, String mesto_trazioca,
			String ulica_trazioca, int broj_trazioca, String kontakt, String drugi_nacin, OpisZahteva opis_zahteva,
			NacinDostave nacin_dostave) {
		super();
		this.naziv_organa = naziv_organa;
		this.mesto_organa = mesto_organa;
		this.opis_informacije = opis_informacije;
		this.mesto_trazioca = mesto_trazioca;
		this.ulica_trazioca = ulica_trazioca;
		this.broj_trazioca = broj_trazioca;
		this.kontakt = kontakt;
		this.drugi_nacin = drugi_nacin;
		this.opis_zahteva = opis_zahteva;
		this.nacin_dostave = nacin_dostave;
	}

	public String getNaziv_organa() {
		return naziv_organa;
	}

	public void setNaziv_organa(String naziv_organa) {
		this.naziv_organa = naziv_organa;
	}

	public String getMesto_organa() {
		return mesto_organa;
	}

	public void setMesto_organa(String mesto_organa) {
		this.mesto_organa = mesto_organa;
	}

	public String getOpis_informacije() {
		return opis_informacije;
	}

	public void setOpis_informacije(String opis_informacije) {
		this.opis_informacije = opis_informacije;
	}

	public String getMesto_trazioca() {
		return mesto_trazioca;
	}

	public void setMesto_trazioca(String mesto_trazioca) {
		this.mesto_trazioca = mesto_trazioca;
	}

	public String getUlica_trazioca() {
		return ulica_trazioca;
	}

	public void setUlica_trazioca(String ulica_trazioca) {
		this.ulica_trazioca = ulica_trazioca;
	}

	public int getBroj_trazioca() {
		return broj_trazioca;
	}

	public void setBroj_trazioca(int broj_trazioca) {
		this.broj_trazioca = broj_trazioca;
	}

	public String getKontakt() {
		return kontakt;
	}

	public void setKontakt(String kontakt) {
		this.kontakt = kontakt;
	}

	public String getDrugi_nacin() {
		return drugi_nacin;
	}

	public void setDrugi_nacin(String drugi_nacin) {
		this.drugi_nacin = drugi_nacin;
	}

	public OpisZahteva getOpis_zahteva() {
		return opis_zahteva;
	}

	public void setOpis_zahteva(OpisZahteva opis_zahteva) {
		this.opis_zahteva = opis_zahteva;
	}

	public NacinDostave getNacin_dostave() {
		return nacin_dostave;
	}

	public void setNacin_dostave(NacinDostave nacin_dostave) {
		this.nacin_dostave = nacin_dostave;
	}

}
