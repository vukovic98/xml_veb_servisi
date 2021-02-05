package com.ftn.xml.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ZalbaCutanjeNaprednaDTO {

	private String zahtev;
	private String mejl;
	private String organ;
	private boolean and;

	public ZalbaCutanjeNaprednaDTO() {
		super();
	}

	public ZalbaCutanjeNaprednaDTO(String zahtev, String mejl, String organ, boolean and) {
		super();
		this.zahtev = zahtev;
		this.mejl = mejl;
		this.organ = organ;
		this.and = and;
	}

	public String getZahtev() {
		return zahtev;
	}

	public void setZahtev(String zahtev) {
		this.zahtev = zahtev;
	}

	public String getMejl() {
		return mejl;
	}

	public void setMejl(String mejl) {
		this.mejl = mejl;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public boolean isAnd() {
		return and;
	}

	public void setAnd(boolean and) {
		this.and = and;
	}

}
