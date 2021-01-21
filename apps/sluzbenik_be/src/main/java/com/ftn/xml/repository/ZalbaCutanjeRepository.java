package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class ZalbaCutanjeRepository {

	private String collectionId = "/db/poverenik";
	private String documentId = "zalba_cutanje.xml";
	
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_cutanje";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	@Autowired
	private ExistManager existManager;
	
	public ResourceSet getAll() {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet getAllByUserEmail(String email) {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje/podnozje/podnosilac_zalbe[korisnik_email='" + email + "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
}
