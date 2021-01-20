package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class KorisnikRepository {

	private String collectionId = "/db/sluzbenik";
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
	
	public ResourceSet prijavaKorisnika(String mail, String password) {
		String xPath = "/lista_korisnika/korisnik[email = '" + mail + "' and lozinka = '" + password + "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean postojiPoMejlu(String email) {
		String xPath = "/lista_korisnika/korisnik[email = '" + email + "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE).getSize() != 0;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean registracija(String korisnik) {
		
		try {
			this.existManager.append(collectionId, documentId, "/lista_korisnika", korisnik, APPEND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

}
