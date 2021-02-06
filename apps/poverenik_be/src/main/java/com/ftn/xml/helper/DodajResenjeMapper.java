package com.ftn.xml.helper;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;import org.apache.batik.apps.svgbrowser.JSVGViewerFrame.AboutAction;
import org.springframework.stereotype.Component;

import com.ftn.xml.dto.DodajResenjeDTO;
import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.model.resenje.BrojZalbe;
import com.ftn.xml.model.resenje.Datum;
import com.ftn.xml.model.resenje.DatumZahteva;
import com.ftn.xml.model.resenje.DonetoResenje;
import com.ftn.xml.model.resenje.Ishod;
import com.ftn.xml.model.resenje.KorisnikEmail;
import com.ftn.xml.model.resenje.Naslov;
import com.ftn.xml.model.resenje.Naziv;
import com.ftn.xml.model.resenje.Obrazlozenje;
import com.ftn.xml.model.resenje.Organ;
import com.ftn.xml.model.resenje.OsnovniPodaci;
import com.ftn.xml.model.resenje.Podnosilac;
import com.ftn.xml.model.resenje.Poverenik;
import com.ftn.xml.model.resenje.Resenje;
import com.ftn.xml.model.resenje.Sadrzaj;
import com.ftn.xml.model.resenje.Taksa;
import com.ftn.xml.model.resenje.TrazeniDokument;
import com.ftn.xml.model.resenje.Ustanova;
import com.ftn.xml.model.resenje.Uvod;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;



@Component
public class DodajResenjeMapper {

	private static final String PROPERTY = "pred:";
	private static final String DATATYPE = "xs:";
	
	private static final String ABOUT = "http://www.ftn.uns.ac.rs/rdf/examples/resenje/";
	private static final String VOCAB = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";
	
	public ResenjeFusekiDTO klasaUFusekiDTO(Resenje r) {
		
		ResenjeFusekiDTO dto = new ResenjeFusekiDTO();
		
		dto.setBroj(r.getBroj());
		dto.setDatum(r.getOsnovniPodaci().getDatum().toString());
		dto.setDatum_zahteva(r.getSadrzaj().getUvod().getDatumZahteva().toString());
		dto.setNaslov(r.getOsnovniPodaci().getNaslov().getContent());
		dto.setKorisnik_email(r.getOsnovniPodaci().getKorisnikEmail().getContent());
		dto.setOrgan(r.getSadrzaj().getUvod().getOrgan().getContent());
		dto.setPodnosilac(r.getSadrzaj().getUvod().getPodnosilac().getContent());
		dto.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
		dto.setDatum_zahteva(r.getSadrzaj().getUvod().getDatumZahteva().toString());
		dto.setTrazeni_dokument(r.getSadrzaj().getDonetoResenje().getTrazeniDokument().getContent());
		dto.setTekst_resenja(r.getSadrzaj().getDonetoResenje().getTekstResenja());
		dto.setTekst_obrazlozenja(r.getSadrzaj().getObrazlozenje().getTekstObrazlozenja());
		dto.setSud(r.getSadrzaj().getObrazlozenje().getSud());
		dto.setTaksa(r.getSadrzaj().getObrazlozenje().getTaksa().getValue().doubleValue());
		dto.setIshod(r.getIshod().getValue());
		dto.setPoverenik(r.getPoverenik().getContent());
		dto.setBroj(r.getBroj());
		
		return dto;
		
		
		
		
	}
	
	public Resenje dtoUKlasu(DodajResenjeDTO dto, int index) {
		Resenje resenje = new Resenje();
		
		OsnovniPodaci osnovni_podaci = new OsnovniPodaci();
		Naslov naslov = new Naslov();
		naslov.setContent(dto.getNaslov());
		naslov.setDatatype(DATATYPE + "string"); 
		naslov.setProperty(PROPERTY + "naslov");
		osnovni_podaci.setNaslov(naslov);
		
		Datum datum = new Datum();

		XMLGregorianCalendar datum_resenja = XMLGregorianCalendarImpl.parse(dto.getDatum_resenja());
		datum.setValue(datum_resenja);
		datum.setDatatype(DATATYPE + "date");
		datum.setProperty(PROPERTY + "datum_resenja");
		osnovni_podaci.setDatum(datum);
		
		KorisnikEmail email = new KorisnikEmail();
		email.setDatatype(DATATYPE + "string");
		email.setProperty(PROPERTY + "korisnik");
		email.setContent(dto.getKorisnik_email());
		osnovni_podaci.setKorisnikEmail(email);
		
		
		Sadrzaj sadrzaj = new Sadrzaj();
		
		Uvod uvod = new Uvod();
		
		Organ organ = new Organ();
		organ.setContent("Повереник за информације од јавног значаја и заштиту података о личности");
		organ.setDatatype(DATATYPE + "string");
		organ.setProperty(PROPERTY + "organ");
		uvod.setOrgan(organ);
		
		Podnosilac podnosilac = new Podnosilac();
		podnosilac.setDatatype(DATATYPE + "string");
		podnosilac.setProperty(PROPERTY + "podnosilac");
		podnosilac.setContent(dto.getPodnosilac());
		uvod.setPodnosilac(podnosilac);
		
		Ustanova ustanova = new Ustanova();
		ustanova.setUlica(dto.getAdresa_ustanove());
		Naziv naziv = new Naziv();
		naziv.setValue(dto.getNaziv_ustanove());
		naziv.setDatatype(DATATYPE + "string");
		naziv.setProperty(PROPERTY + "naziv_ustanove");
		ustanova.setNaziv(naziv);
		uvod.setUstanova(ustanova);
		
		DatumZahteva datum_zahteva = new DatumZahteva();
		datum_zahteva.setDatatype(DATATYPE + "date");
		datum_zahteva.setProperty(PROPERTY + "datum_zahteva");
		XMLGregorianCalendar datum_zahteva_xml = XMLGregorianCalendarImpl.parse(dto.getDatum_zahteva());
		datum_zahteva.setValue(datum_zahteva_xml);
		uvod.setDatumZahteva(datum_zahteva);
		sadrzaj.setUvod(uvod);
		
		Obrazlozenje obrazlozenje = new Obrazlozenje();
		
		
		obrazlozenje.setTekstObrazlozenja(dto.getTekst_obrazlozenja());
		obrazlozenje.setSud(dto.getSud());
		Taksa taksa = new Taksa();
		taksa.setDatatype(DATATYPE + "double");
		taksa.setProperty(PROPERTY + "taksa");
		taksa.setValue(BigDecimal.valueOf(dto.getTaksa()));
		obrazlozenje.setTaksa(taksa);
		
		
		sadrzaj.setObrazlozenje(obrazlozenje);
		
		
		DonetoResenje doneto = new DonetoResenje();
		doneto.setTekstResenja(dto.getTekst_resenja());
		
		TrazeniDokument trazeni = new TrazeniDokument();
		trazeni.setDatatype(DATATYPE + "string");
		trazeni.setProperty(PROPERTY + "trazeni_dokument");
		trazeni.setContent(dto.getTrazeni_dokument());
		doneto.setTrazeniDokument(trazeni);
		
		
		sadrzaj.setDonetoResenje(doneto);
		
		resenje.setSadrzaj(sadrzaj);
		
		Ishod ishod = new Ishod();
		ishod.setValue(dto.getIshod());
		ishod.setDatatype(DATATYPE + "string");
		ishod.setProperty(PROPERTY + "ishod");
		resenje.setIshod(ishod);
		
		
		
		resenje.setBroj("071-01-1114/2020-03");
		
		Poverenik poverenik = new Poverenik();
		poverenik.setContent("Милан Мариновић");
		poverenik.setDatatype(DATATYPE + "string");
		poverenik.setProperty(PROPERTY + "poverenik");
		resenje.setPoverenik(poverenik);
		
		BrojZalbe brojZalbe = new BrojZalbe();
		brojZalbe.setValue(BigInteger.valueOf(dto.getBroj_zalbe()));
		brojZalbe.setDatatype(DATATYPE + "string");
		brojZalbe.setProperty(PROPERTY + "broj_zalbe");
		resenje.setBrojZalbe(brojZalbe);
		
		
		resenje.setAbout(ABOUT + index );
		resenje.setVocab(VOCAB);
		
		return resenje;
	}
	
	
}
