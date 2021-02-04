package com.ftn.xml.dto;

public class ZahtevIzjasnjenjeCutanjeFusekiDTO {

	private long id_zalbe;
	private String vreme;
	public ZahtevIzjasnjenjeCutanjeFusekiDTO() {
		super();
	}
	public ZahtevIzjasnjenjeCutanjeFusekiDTO(long id_zalbe, String vreme) {
		super();
		this.id_zalbe = id_zalbe;
		this.vreme = vreme;
	}
	public long getId_zalbe() {
		return id_zalbe;
	}
	public void setId_zalbe(long id_zalbe) {
		this.id_zalbe = id_zalbe;
	}
	public String getVreme() {
		return vreme;
	}
	public void setVreme(String vreme) {
		this.vreme = vreme;
	}
	
	
	
}
