package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;

@Repository
public class ZahtevZaIzjasnjenjeCutanjeRepository {

	private String collectionId = "/db/poverenik";
	private String documentId = "zahtev_za_izjasnjenje_cutanje.xml";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zahtev/cutanje";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zahtev/cutanje";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	@Autowired
	private FusekiManager fusekiManager;
	
	public ResourceSet pronadjiPoIdZalbeCutanje(long id_zalbe) {
		String xPath = "/zahtevi_za_izjasnjenje_cutanje/zahtev_za_izjasnjenje_cutanje[id_zalbe = "+id_zalbe+"]";

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
			this.existManager.append(collectionId, documentId, "/zahtevi_za_izjasnjenje_cutanje", z, APPEND);
			//TODO
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
