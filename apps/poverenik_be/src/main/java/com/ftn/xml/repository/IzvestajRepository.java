package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class IzvestajRepository {
	private String collectionId = "/db/poverenik";
	private String documentId = "izvestaj.xml";

	private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/izvestaj";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	public ResourceSet dobaviSveIzvestaje() {
		String xPath = "/lista_izvestaja/izvestaj";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResourceSet pronadjiPoDatumu(String date) {
		String xPath = "/lista_izvestaja/izvestaj[@datum='" + date + "']";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean dodajIzvestaj(String xml) {
		String xPath = "/lista_izvestaja";

		try {
			this.existManager.append(collectionId, documentId, xPath, xml, APPEND);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
