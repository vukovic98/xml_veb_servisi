package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.dto.ObavestenjeFusekiDTO;

@Repository
public class ObavestenjeRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "obavestenje.xml";

	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/obavestenje";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/obavestenje/";

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
	
	public ResourceSet pretraga(String text) {
		String xPath = "/lista_obavestenja/obavestenje[osnovni_podaci/podaci_o_organu/naziv[contains(., '"+text+"')]" + 
				"or osnovni_podaci/podaci_o_organu/sediste[contains(., '"+text+"')]"
				+ " or osnovni_podaci/podaci_o_organu/broj_predmeta[contains(., '"+text+"')]"
				+ " or osnovni_podaci/podaci_o_podnosiocu/ime_i_prezime[contains(., '"+text+"')]"
				+ " or sadrzaj/opis_trazene_informacije[contains(., '"+text+"')] or broj_zahteva[contains(., '"+text+"')]]";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean proveraPotvrdeZahteva(long zahtev_id) {
		String xPath = "/lista_obavestenja/obavestenje[broj_zahteva=" + zahtev_id + "]";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			if (set.getSize() != 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean dodajObavestenje(String xml, ObavestenjeFusekiDTO dto, String id) {
		String xPath = "/lista_obavestenja";
		
		try {
			this.existManager.append(collectionId, documentId, xPath, xml, APPEND);
			this.fusekiManager.dodajObavestenje(id, dto);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ResourceSet pronadjiSvaObavestenja() {
		String xPath = "/lista_obavestenja/obavestenje";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet pronadjiPoId(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_obavestenja/obavestenje[@about='" + id_Str + "']";
		
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
