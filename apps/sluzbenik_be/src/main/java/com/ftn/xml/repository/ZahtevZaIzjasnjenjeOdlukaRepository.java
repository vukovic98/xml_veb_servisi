package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class ZahtevZaIzjasnjenjeOdlukaRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "zahtev_za_izjasnjenje_odluka.xml";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_na_odluku";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	public static final String REMOVE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:remove select=\"%1$s\"/>" + "</xu:modifications>";
	
	
	@Autowired
	private ExistManager existManager;

	public boolean dodajZahtevZaIzjasnjenjeOdluka(String zahtev) {
		String xPath = "/zahtevi_za_izjasnjenje_odluka";

		try {
			this.existManager.append(collectionId, documentId, xPath, zahtev, APPEND);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean izbrisiZahtevZaIzjasnjenjeOdluka(String id) {
		String id_zalbe = ID_STRING + id;
		String xPath = "/zahtevi_za_izjasnjenje_odluka/zalba_na_odluku[@about='" + id_zalbe + "']";

		try {
			this.existManager.remove(collectionId, documentId, xPath, REMOVE);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public ResourceSet dobaviSve() {
		String xPath = "/zahtevi_za_izjasnjenje_odluka/zalba_na_odluku";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			return null;
		}
	}
}
