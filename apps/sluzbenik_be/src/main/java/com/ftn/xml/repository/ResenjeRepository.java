package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class ResenjeRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "resenje.xml";

	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/resenje/";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/resenje";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	public ResourceSet pretraga(String text) {
		String xPath = "/lista_resenja/resenje[sadrzaj/uvod/organ[contains(., '"+text+"')]"
				+ " or sadrzaj/uvod/podnosilac[contains(., '"+text+"')]"
				+ " or sadrzaj/uvod/ustanova/naziv[contains(., '"+text+"')]"
				+ " or sadrzaj/doneto_resenje/trazeni_dokument[contains(., '"+text+"')]"
				+ " or poverenik[contains(., '"+text+"')] or ishod[contains(., '"+text+"')]]";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean proveriDaLiJeZalbaResena(long id) {
		String xPath = "/lista_resenja/resenje/.[broj_zalbe=" + id + "]";
	

		ResourceSet set;

		try {
			set =  this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			if(set.getSize()!=0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;

		}
	}
	
	public ResourceSet findById(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_resenja/resenje[@about='" + id_Str + "']";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
