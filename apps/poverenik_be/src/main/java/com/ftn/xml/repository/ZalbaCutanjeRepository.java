package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;

@Repository
public class ZalbaCutanjeRepository {

	private String collectionId = "/db/poverenik";
	private String documentId = "zalba_cutanje.xml";

	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_cutanje";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	public ResourceSet dobaviSve() {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet dobaviSvePoEmailu(String email) {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje" + "[podnozje/podnosilac_zalbe/korisnik_email='" + email
				+ "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet pronadjiPoId(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje[@about='" + id_Str + "']";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean dodajZalbu(ZalbaCutanje zalba) {
		
		return false;
	}

	public void dodajZalbuIzTeksta(String zalba) throws Exception {
		String contextXPath = "/lista_zalbi_cutanje";
		this.existManager.append(collectionId, documentId, contextXPath, zalba, APPEND);
		
	}
}
