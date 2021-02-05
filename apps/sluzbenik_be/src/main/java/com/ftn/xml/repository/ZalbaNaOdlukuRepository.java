package com.ftn.xml.repository;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;

@Repository
public class ZalbaNaOdlukuRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "zalba_na_odluku.xml";

	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/";
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_na_odluku";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	@Autowired
	private ExistManager existManager;

	public long ukupanBrojZalbiNaOdluku() {
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public ResourceSet dobaviPoId(long id) {
		String str = ID_STRING + id;
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku[@about='" + str + "']";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ResourceSet pretraga(String text) {
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku[osnovni_podaci/podaci_o_zaliocu/zalioc_ime[contains(., '"
				+ text + "')]" + " or osnovni_podaci/podaci_o_zaliocu/zalioc_prezime[contains(., '" + text + "')] or "
				+ "osnovni_podaci/podaci_o_organu/naziv[contains(., '" + text
				+ "')] or sadrzaj/broj_zalbe[contains(., '" + text + "')]]";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public boolean postojiZalbaNaZahtev(long id) {
		String xPath = "/lista_zalbi_na_odluku/zalba_na_odluku/broj_zahteva=" +id;
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			if(set.getSize()!= 0) 
				return true;
			else 
				return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
