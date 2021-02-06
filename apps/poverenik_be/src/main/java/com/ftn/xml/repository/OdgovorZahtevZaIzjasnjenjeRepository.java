package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class OdgovorZahtevZaIzjasnjenjeRepository {

	private String collectionId = "/db/poverenik";
	private String documentId = "odgovor_zahtev_za_izjasnjenje.xml";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/izjasnjenje/odgovor";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/izjasnjenje/odgovor";

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
	
	public boolean dodajOdgovorZahtevZaIzjasnjenje(String odgovor) {
		System.out.println("REPO: " + odgovor);
		String xPath = "/odgovori_zahtev_za_izjasnjenje";

		try {
			this.existManager.append(collectionId, documentId, xPath, odgovor, APPEND);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean izbrisiOdgovorZahtevZaIzjasnjenje(String id) {
		String id_zalbe = ID_STRING + id;
		String xPath = "/odgovori_zahtev_za_izjasnjenje/odgovor_zahtev_za_izjasnjenje[id_zalbe='" + id_zalbe + "']";

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
		String xPath = "/odgovori_zahtev_za_izjasnjenje/odgovor_zahtev_za_izjasnjenje";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet dobaviPoZalbi(String id_zalbe, String tip) {
		String xPath = "/odgovori_zahtev_za_izjasnjenje/odgovor_zahtev_za_izjasnjenje[id_zalbe = '"+id_zalbe+"' and tip = '"+tip+"']";
		
		System.out.println(xPath);
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			System.out.println("Nasao!");
			return set;
		} catch (Exception e) {
			return null;
		}
	}
	
}
