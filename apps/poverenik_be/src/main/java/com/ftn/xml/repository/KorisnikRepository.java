package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.*;
import com.ftn.xml.dto.KorisnikPrijavaDto;
import com.ftn.xml.model.korisnik.Korisnik;



@Repository
public class KorisnikRepository {

	private String collectionId = "/db/poverenik";
	private String documentId = "korisnik.xml";
	
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/korisnik";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	@Autowired
	private ExistManager existManager;
	
	public boolean registruj(String korisnik) {
		try {
			this.existManager.append(collectionId, documentId, "/lista_korisnika", korisnik, APPEND);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean postojiPoMejlu(String email) {
		String xPath = "/lista_korisnika/korisnik[email='" + email + "']";
		try {
			ResourceSet s = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return s.getSize()!=0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean prijava(KorisnikPrijavaDto korisnik) {
		String xPath = "/lista_korisnika/korisnik[email='" + korisnik.getEmail() + "' and lozinka='"+korisnik.getLozinka()+"']";
		try {
			ResourceSet s = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return s.getSize()!=0;
		} catch (Exception e) {
			return false;
		}
	}

	public Resource pronadjiPoEmailu(String email) {
		String xPath = "/lista_korisnika/korisnik[email = '" + email + "']";
		try {
			ResourceSet set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			if(set.getSize() == 1)
				return set.getResource(0);
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}
}
