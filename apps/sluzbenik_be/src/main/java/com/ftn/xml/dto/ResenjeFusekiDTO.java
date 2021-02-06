package com.ftn.xml.dto;

public class ResenjeFusekiDTO {

	private String naslov;
	private String datum;
	private String korisnik_email;
	private String organ;
	private String podnosilac;
	private String ustanova;
	private String datum_zahteva;
	private String trazeni_dokument;
	private String tekst_resenja;
	private String tekst_obrazlozenja;
	private String sud;
	private double taksa;
	private String poverenik;
	private String ishod;
	private String broj;

	public ResenjeFusekiDTO() {
		super();
	}

	public ResenjeFusekiDTO(String naslov, String datum, String korisnik_email, String organ, String podnosilac,
			String ustanova, String datum_zahteva, String trazeni_dokument, String tekst_resenja,
			String tekst_obrazlozenja, String sud, double taksa, String poverenik, String ishod, String broj) {
		super();
		this.naslov = naslov;
		this.datum = datum;
		this.korisnik_email = korisnik_email;
		this.organ = organ;
		this.podnosilac = podnosilac;
		this.ustanova = ustanova;
		this.datum_zahteva = datum_zahteva;
		this.trazeni_dokument = trazeni_dokument;
		this.tekst_resenja = tekst_resenja;
		this.tekst_obrazlozenja = tekst_obrazlozenja;
		this.sud = sud;
		this.taksa = taksa;
		this.poverenik = poverenik;
		this.ishod = ishod;
		this.broj = broj;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getKorisnik_email() {
		return korisnik_email;
	}

	public void setKorisnik_email(String korisnik_email) {
		this.korisnik_email = korisnik_email;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getPodnosilac() {
		return podnosilac;
	}

	public void setPodnosilac(String podnosilac) {
		this.podnosilac = podnosilac;
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

	public String getTrazeni_dokument() {
		return trazeni_dokument;
	}

	public void setTrazeni_dokument(String trazeni_dokument) {
		this.trazeni_dokument = trazeni_dokument;
	}

	public String getTekst_resenja() {
		return tekst_resenja;
	}

	public void setTekst_resenja(String tekst_resenja) {
		this.tekst_resenja = tekst_resenja;
	}

	public String getTekst_obrazlozenja() {
		return tekst_obrazlozenja;
	}

	public void setTekst_obrazlozenja(String tekst_obrazlozenja) {
		this.tekst_obrazlozenja = tekst_obrazlozenja;
	}

	public String getSud() {
		return sud;
	}

	public void setSud(String sud) {
		this.sud = sud;
	}

	public double getTaksa() {
		return taksa;
	}

	public void setTaksa(double taksa) {
		this.taksa = taksa;
	}

	public String getPoverenik() {
		return poverenik;
	}

	public void setPoverenik(String poverenik) {
		this.poverenik = poverenik;
	}

	public String getIshod() {
		return ishod;
	}

	public void setIshod(String ishod) {
		this.ishod = ishod;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

}
