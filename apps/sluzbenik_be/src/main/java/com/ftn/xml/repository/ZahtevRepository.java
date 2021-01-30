package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;

@Repository
public class ZahtevRepository {
	
	private String collectionId = "/db/sluzbenik";
	private String documentId = "zahtev.xml";
	
	private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/zahtev";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zahtev/";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	@Autowired
	private ExistManager existManager;

	public ResourceSet pronadjiSveZahteve() {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet pronadjiZahteveZaKorisnika(String email) {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama"
				+ "[podnozje/informacije_o_traziocu/korisnik_email='" + email + "']";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			
			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet pronadjiPoId(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[@about='" + id_Str + "']";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean sacuvajZahtev(String z) {
		try {
			this.existManager.append(collectionId, documentId, "/lista_zahteva_za_pristup_informacijama", z, APPEND);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
