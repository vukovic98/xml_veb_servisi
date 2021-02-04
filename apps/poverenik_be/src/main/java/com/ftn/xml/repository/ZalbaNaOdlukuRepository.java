package com.ftn.xml.repository;


import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;

@Repository
public class ZalbaNaOdlukuRepository {
	
	private String collectionId = "/db/poverenik";
	private String documentId = "zalba_na_odluku.xml";
	
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_na_odluku";
	
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
	
	@Autowired
	private FusekiManager fusekiManager;
	
	public ResourceSet dobaviSve() {
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		}catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet dobaviSvePoEmailu(String email) {
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku" + "[osnovni_podaci/podaci_o_zaliocu/korisnik_email='" + email
				+ "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		}catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet dobaviPoId(long id) {
		String str = ID_STRING + id;
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku[@about='" + str + "']";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			
			return set;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean dodajZalbu(ZalbaNaOdluku zalba) {
		return false;
	}
	
	public void dodajZalbuIzTeksta(String zalba, ZalbaNaOdluku z) throws Exception {
		String contextXPath = "/lista_zalbi_na_odluku";
		this.existManager.append(collectionId, documentId, contextXPath, zalba, APPEND);
		this.fusekiManager.dodajZalbuNaOdluku(z);
		
	}
	
	public boolean odustaniOdZalbe(long id) {
		
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku[@about = \'http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/"+id+"\']";
		try {
			this.existManager.remove(collectionId, documentId, xPath, REMOVE);
			this.fusekiManager.obrisiZalbuNaOdluku(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
	}
}
