package com.ftn.xml.email.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OdbijenZahtevDTO {

	private String to;
	private String naslov;
	private String sadrzaj;

	public OdbijenZahtevDTO() {
		super();
	}

	public OdbijenZahtevDTO(String to, String naslov, String sadrzaj) {
		super();
		this.to = to;
		this.naslov = naslov;
		this.sadrzaj = sadrzaj;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

}
