package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ftn.xml.db.ExistManager;

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
		// TODO Auto-generated method stub
		return true;
	}
}
