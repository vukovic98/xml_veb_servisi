package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class BrojacRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "brojac.xml";

	private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/brojac";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	public Resource dobaviBrojac() {
		String xPath = "/brojac";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set.getResource(0);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean sacuvajBrojac(String changedBrojac) {
		String xPath = "/brojac";
		try {
			this.existManager.update(collectionId, documentId, xPath, changedBrojac, UPDATE);

			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
