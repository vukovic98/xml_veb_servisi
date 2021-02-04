package com.ftn.xml.helper;

import org.springframework.stereotype.Component;

import com.ftn.xml.dto.ZalbaNaOdlukuDTO;
import com.ftn.xml.dto.ZalbaNaOdlukuDodavanjeDTO;
import com.ftn.xml.model.zalba_na_odluku.BrojZahteva;
import com.ftn.xml.model.zalba_na_odluku.DatumZakljuckaZalbe;
import com.ftn.xml.model.zalba_na_odluku.KorisnikEmail;
import com.ftn.xml.model.zalba_na_odluku.MestoZakljuckaZalbe;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
import com.ftn.xml.model.zalba_na_odluku.ZaliocAdresa;
import com.ftn.xml.model.zalba_na_odluku.ZaliocIme;
import com.ftn.xml.model.zalba_na_odluku.ZaliocPrezime;
import com.ftn.xml.model.zalba_na_odluku.ZaliocSediste;

@Component
public class DodajZalbuNaOdlukuMapper {
	
	public ZalbaNaOdluku dtoUKlasu(ZalbaNaOdlukuDodavanjeDTO dto,String email,String ime, String prezime) {
		
		ZalbaNaOdluku zalba = new ZalbaNaOdluku();
		
		ZaliocIme imeZ = new ZaliocIme();
		ZaliocPrezime prezimeZ = new ZaliocPrezime();
		KorisnikEmail emailKor = new KorisnikEmail();
		
		imeZ.setValue(ime);
		prezimeZ.setValue(prezime);
		emailKor.setContent(email);
		
		ZaliocAdresa adresa = new ZaliocAdresa();
		ZaliocSediste sediste = new ZaliocSediste();
		
		adresa.setContent(dto.getAdresa_podnosioca());
		sediste.setContent(dto.getSediste_zalioca());
		
		DatumZakljuckaZalbe datum = new DatumZakljuckaZalbe();
		MestoZakljuckaZalbe mesto = new MestoZakljuckaZalbe();
		datum.setValue(dto.getDatum_zalbe());
		mesto.setContent(dto.getMesto_zalbe());
		
		return zalba;
	}

}
