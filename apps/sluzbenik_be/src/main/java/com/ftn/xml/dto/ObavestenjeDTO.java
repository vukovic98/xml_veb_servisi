package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ObavestenjeDTO {
	private String id;
	private String nazivOrgana;
	private String brojPredmeta;
	private String datumZahteva;
	private String imeTrazioca;
	private String opisInformacije;
	private String datumUvida;
	private String brojKancelarije;
	private String brojZahteva;

	public ObavestenjeDTO() {
		super();
	}

	public ObavestenjeDTO(String id, String nazivOrgana, String brojPredmeta, String datumZahteva, String imeTrazioca,
			String opisInformacije, String datumUvida, String brojKancelarije, String brojZahteva) {
		super();
		this.id = id;
		this.nazivOrgana = nazivOrgana;
		this.brojPredmeta = brojPredmeta;
		this.datumZahteva = datumZahteva;
		this.imeTrazioca = imeTrazioca;
		this.opisInformacije = opisInformacije;
		this.datumUvida = datumUvida;
		this.brojKancelarije = brojKancelarije;
		this.brojZahteva = brojZahteva;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNazivOrgana() {
		return nazivOrgana;
	}

	public void setNazivOrgana(String nazivOrgana) {
		this.nazivOrgana = nazivOrgana;
	}

	public String getBrojPredmeta() {
		return brojPredmeta;
	}

	public void setBrojPredmeta(String brojPredmeta) {
		this.brojPredmeta = brojPredmeta;
	}

	public String getDatumZahteva() {
		return datumZahteva;
	}

	public void setDatumZahteva(String datumZahteva) {
		this.datumZahteva = datumZahteva;
	}

	public String getImeTrazioca() {
		return imeTrazioca;
	}

	public void setImeTrazioca(String imeTrazioca) {
		this.imeTrazioca = imeTrazioca;
	}

	public String getOpisInformacije() {
		return opisInformacije;
	}

	public void setOpisInformacije(String opisInformacije) {
		this.opisInformacije = opisInformacije;
	}

	public String getDatumUvida() {
		return datumUvida;
	}

	public void setDatumUvida(String datumUvida) {
		this.datumUvida = datumUvida;
	}

	public String getBrojKancelarije() {
		return brojKancelarije;
	}

	public void setBrojKancelarije(String brojKancelarije) {
		this.brojKancelarije = brojKancelarije;
	}

	public String getBrojZahteva() {
		return brojZahteva;
	}

	public void setBrojZahteva(String brojZahteva) {
		this.brojZahteva = brojZahteva;
	}

}
