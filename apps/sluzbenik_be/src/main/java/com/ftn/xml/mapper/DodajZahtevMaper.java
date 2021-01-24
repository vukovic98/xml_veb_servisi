package com.ftn.xml.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ftn.xml.dto.DodajZahtevDTO;
import com.ftn.xml.dto.NacinDostave;
import com.ftn.xml.dto.OpisZahteva;
import com.ftn.xml.model.zahtev.Adresa;
import com.ftn.xml.model.zahtev.Broj;
import com.ftn.xml.model.zahtev.DatumZahteva;
import com.ftn.xml.model.zahtev.ImeIPrezime;
import com.ftn.xml.model.zahtev.InformacijeOTraziocu;
import com.ftn.xml.model.zahtev.Kontakt;
import com.ftn.xml.model.zahtev.KorisnikEmail;
import com.ftn.xml.model.zahtev.Mesto;
import com.ftn.xml.model.zahtev.MestoIDatum;
import com.ftn.xml.model.zahtev.Nacin;
import com.ftn.xml.model.zahtev.NaciniDostave;
import com.ftn.xml.model.zahtev.Naziv;
import com.ftn.xml.model.zahtev.OpisTrazeneInformacije;
import com.ftn.xml.model.zahtev.PodaciOOrganu;
import com.ftn.xml.model.zahtev.Podnozje;
import com.ftn.xml.model.zahtev.Sadrzaj;
import com.ftn.xml.model.zahtev.Sediste;
import com.ftn.xml.model.zahtev.Ulica;
import com.ftn.xml.model.zahtev.Zahtev;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.model.zahtev.Zahtevi;

@Component
public class DodajZahtevMaper {
	
	private static final String PROPERTY = "pred:";
	private static final String DATATYPE = "xs:";
	
	private static final String ABOUT = "http://www.ftn.uns.ac.rs/rdf/examples/zahtev/4";
	private static final String VOCAB = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";
	
	private static final String OPIS_OBAVESTENJE = "обавештење да ли поседује тражену информацију";
    private static final String OPIS_UVID = "увид у документ који садржи тражену информацију";
    private static final String OPIS_KOPIJA = "копију документа који садржи тражену информацију";
    private static final String OPIS_DOSTAVA = "достављање копије документа који садржи тражену информацију";
    
    private static final String OPIS_POSTA = "поштом";
    private static final String OPIS_E_POSTA = "електронском поштом";
    private static final String OPIS_FAKS = "факсом";
    private static final String OPIS_DRUGI = "на други начин:";

	public ZahtevZaPristupInformacijama dtoUKlasu(DodajZahtevDTO dto, String mail, String ime_i_prezime) {
		ZahtevZaPristupInformacijama zahtev = new ZahtevZaPristupInformacijama();
		
		//------------ORGAN----------------
		PodaciOOrganu organ = new PodaciOOrganu();
		
		Naziv n = new Naziv();
		n.setContent(dto.getNaziv_organa());
		n.setDatatype(DATATYPE + "string"); 
		n.setProperty(PROPERTY + "naziv_ustanove");
		organ.setNaziv(n);
		
		Sediste s = new Sediste();
		s.setContent(dto.getMesto_organa());
		s.setDatatype(DATATYPE + "string");
		s.setProperty(PROPERTY + "sediste_ustanove");
		organ.setSediste(s);
		
		zahtev.setPodaciOOrganu(organ);
		
		//----------------------------------
		
		
		//-------------SADRZAJ---------------------
		
		Sadrzaj sadrzaj = new Sadrzaj();
		
		Zahtevi zi = new Zahtevi();
		
		Zahtev z1 = new Zahtev();
		z1.setNaciniDostave(null);
		z1.setId(OpisZahteva.zahtev_obavestenje.toString());
		z1.setOpisZahteva(OPIS_OBAVESTENJE);
		z1.setOtkaceno(dto.getOpis_zahteva().toString().equalsIgnoreCase("zahtev_obavestenje"));
		zi.getContent().add(z1);
		
		Zahtev z2 = new Zahtev();
		z2.setNaciniDostave(null);
		z2.setId(OpisZahteva.zahtev_uvid.toString());
		z2.setOpisZahteva(OPIS_UVID);
		z2.setOtkaceno(dto.getOpis_zahteva().toString().equalsIgnoreCase("zahtev_uvid"));
		zi.getContent().add(z2);
		
		Zahtev z3 = new Zahtev();
		z3.setNaciniDostave(null);
		z3.setId(OpisZahteva.zahtev_kopija.toString());
		z3.setOpisZahteva(OPIS_KOPIJA);
		z3.setOtkaceno(dto.getOpis_zahteva().toString().equalsIgnoreCase("zahtev_kopija"));
		zi.getContent().add(z3);
		
		Zahtev z4 = new Zahtev();
		z4.setId(OpisZahteva.zahtev_dostava.toString());
		z4.setOpisZahteva(OPIS_DOSTAVA);
		z4.setOtkaceno(dto.getOpis_zahteva().toString().equalsIgnoreCase("zahtev_dostava"));
		
		NaciniDostave ni = new NaciniDostave();
		
		Nacin n1 = new Nacin();
		n1.setId(NacinDostave.posta.toString());
		n1.setOpisNacina(OPIS_POSTA);
		n1.setOtkaceno(dto.getNacin_dostave().toString().equalsIgnoreCase("posta"));
		n1.setDrugiNacin(null);
		ni.getNacin().add(n1);
		
		Nacin n2 = new Nacin();
		n2.setId(NacinDostave.e_posta.toString());
		n2.setOpisNacina(OPIS_E_POSTA);
		n2.setOtkaceno(dto.getNacin_dostave().toString().equalsIgnoreCase("e_posta"));
		n2.setDrugiNacin(null);
		ni.getNacin().add(n2);
		
		Nacin n3 = new Nacin();
		n1.setId(NacinDostave.faks.toString());
		n1.setOpisNacina(OPIS_FAKS);
		n1.setOtkaceno(dto.getNacin_dostave().toString().equalsIgnoreCase("faks"));
		n1.setDrugiNacin(null);
		ni.getNacin().add(n3);
		
		Nacin n4 = new Nacin();
		n1.setId(NacinDostave.drugi.toString());
		n1.setOpisNacina(OPIS_DRUGI);
		n1.setOtkaceno(dto.getNacin_dostave().toString().equalsIgnoreCase("drugi"));
		n1.setDrugiNacin(dto.getDrugi_nacin());
		ni.getNacin().add(n4);
		
		z4.setNaciniDostave(ni);
		
		zi.getContent().add(z4);
		
		sadrzaj.setZahtevi(zi);
		
		OpisTrazeneInformacije opis = new OpisTrazeneInformacije();
		
		opis.setContent(dto.getOpis_informacije());
		opis.setDatatype(DATATYPE + "string");
		opis.setProperty(PROPERTY + "opis_informacije");
		
		sadrzaj.setOpisTrazeneInformacije(opis);
		
		zahtev.setSadrzaj(sadrzaj);
		
		//----------------------------------
		
		//----------------PODNOZJE------------------
		
		Podnozje podnozje = new Podnozje();
		
		InformacijeOTraziocu inf = new InformacijeOTraziocu();
		
		inf.setPotpis(null);
		
		Adresa a = new Adresa();
		Broj br = new Broj();
		br.setDatatype(DATATYPE + "integer");
		br.setProperty(PROPERTY + "broj_kuce_trazioca");
		br.setValue(dto.getBroj_trazioca());
		
		a.setBroj(br);
		
		Mesto m = new Mesto();
		m.setContent(dto.getMesto_trazioca());
		m.setDatatype(DATATYPE + "string");
		m.setProperty(PROPERTY + "mesto_trazioca");
		
		a.setMesto(m);
		
		Ulica u = new Ulica();
		
		u.setContent(dto.getUlica_trazioca());
		u.setDatatype(DATATYPE + "string");
		u.setProperty(PROPERTY + "ulica_trazioca");
		
		a.setUlica(u);
		
		inf.setAdresa(a);
		
		ImeIPrezime iP = new ImeIPrezime();
		
		iP.setContent(ime_i_prezime);
		iP.setDatatype(DATATYPE + "string");
		iP.setProperty(PROPERTY + "ime_trazioca");
		
		inf.setImeIPrezime(iP);
		
		Kontakt k = new Kontakt();
		
		k.setDatatype(DATATYPE + "string");
		k.setProperty(PROPERTY + "kontakt_trazioca");
		k.setValue(dto.getKontakt());
		
		inf.setKontakt(k);
		
		KorisnikEmail kE = new KorisnikEmail();
		
		kE.setContent(mail);
		kE.setDatatype(DATATYPE + "string");
		kE.setProperty(PROPERTY + "korisnik");
		
		inf.setKorisnikEmail(kE);
		
		podnozje.setInformacijeOTraziocu(inf);
		
		MestoIDatum mD = new MestoIDatum();
		
		Mesto mS = new Mesto();
		
		mS.setContent(dto.getMesto_trazioca());
		mS.setDatatype(DATATYPE + "string");
		mS.setProperty(PROPERTY + "mesto_zahteva");
		
		mD.setMesto(mS);
		
		DatumZahteva dz = new DatumZahteva();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		
		dz.setValue(f.format(new Date()));
		dz.setProperty(PROPERTY + "datum_zahteva");
		dz.setDatatype(DATATYPE + "date");
		
		
		mD.setDatumZahteva(dz);
		
		podnozje.setMestoIDatum(mD);
		
		zahtev.setPodnozje(podnozje);
		
		zahtev.setAbout(ABOUT);
		zahtev.setVocab(VOCAB);
		
		
		//----------------------------------
		
		return zahtev;
	}
	
}
