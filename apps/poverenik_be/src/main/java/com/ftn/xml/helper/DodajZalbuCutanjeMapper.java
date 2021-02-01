package com.ftn.xml.helper;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import com.ftn.xml.dto.ZalbaCutanjeDodavanjeDTO;
import com.ftn.xml.model.zalba_cutanje.Adresa;
import com.ftn.xml.model.zalba_cutanje.Broj;
import com.ftn.xml.model.zalba_cutanje.BrojZahteva;
import com.ftn.xml.model.zalba_cutanje.DatumZalbe;
import com.ftn.xml.model.zalba_cutanje.ImeIPrezime;
import com.ftn.xml.model.zalba_cutanje.KorisnikEmail;
import com.ftn.xml.model.zalba_cutanje.Mesto;
import com.ftn.xml.model.zalba_cutanje.MestoIDatum;
import com.ftn.xml.model.zalba_cutanje.PodnosilacZalbe;
import com.ftn.xml.model.zalba_cutanje.Podnozje;
import com.ftn.xml.model.zalba_cutanje.Sadrzaj;
import com.ftn.xml.model.zalba_cutanje.Ulica;
import com.ftn.xml.model.zalba_cutanje.Zaglavlje;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;

@Component
public class DodajZalbuCutanjeMapper {

	public ZalbaCutanje dtoUKlasu(ZalbaCutanjeDodavanjeDTO dto, String email, String ime_i_prezime){
		ZalbaCutanje zalba = new ZalbaCutanje();

		//ZAGLAVLJE
		Zaglavlje zaglavlje = new Zaglavlje();

		//SADRZAJ
		Sadrzaj sadrzaj = new Sadrzaj();
		
		//PODNOZJE podnosilac mesto i datum
		Podnozje podnozje = new Podnozje();
		//podnosilac ime mejl adresa
		PodnosilacZalbe podnosilac = new PodnosilacZalbe();
		ImeIPrezime ime = new ImeIPrezime();
		KorisnikEmail emailK = new KorisnikEmail();
		//dodaj podatke za rdf
		ime.setContent(ime_i_prezime);
		emailK.setContent(email);
		podnozje.setPodnosilacZalbe(podnosilac);
		Adresa adresaPodnosioca = new Adresa();
		Ulica ulPodnosioca = new Ulica();
		ulPodnosioca.setContent(dto.getAdresa_ulica_podnosioca());
		adresaPodnosioca.setUlica(ulPodnosioca);
		Mesto mestoPodnosioca = new Mesto();
		mestoPodnosioca.setContent(dto.getAdresa_mesto_podnosioca());
		adresaPodnosioca.setMesto(mestoPodnosioca);
		Broj brojPodnosioca = new Broj();
		BigInteger br = new BigInteger(dto.getAdresa_broj_podnosioca());
		brojPodnosioca.setValue(br);
		adresaPodnosioca.setBroj(brojPodnosioca);
		podnosilac.setAdresa(adresaPodnosioca);
		//DATUM I MESTO ZALBE
		MestoIDatum mestoIDatum = new MestoIDatum();
		DatumZalbe datumZalbe = new DatumZalbe();
		mestoIDatum.setDatumZalbe(datumZalbe);
		Mesto mestoZalbe = new Mesto();
		mestoZalbe.setContent(dto.getMesto_zalbe());
		datumZalbe.setValue(dto.getDatum_zahteva());
		mestoIDatum.setMesto(mestoZalbe);
		podnozje.setMestoIDatum(mestoIDatum);
		
		//BROJ ZAHTEVA
		BrojZahteva brojZahteva = new BrojZahteva();
		
		
		return zalba;
	}
}
