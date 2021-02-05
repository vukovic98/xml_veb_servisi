package com.ftn.xml.repository;

import java.util.ArrayList;
import java.util.List;

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
	
	public static final String SPARQL_FILE = "src/main/resources/static/sparql/obavestenje/";

	@Autowired
	private ExistManager existManager;
	
	@Autowired
	private FusekiManager fusekiManager;
	
	public List<String> naprednaPretraga(String predmet, String zahtev, String ime, boolean and) throws Exception {
		List<String> ids = new ArrayList<>();

		if (and) {
			// predmet + zahtev + ime

			if (predmet != null && zahtev != null && ime != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(predmet);
				params.add(zahtev);
				params.add(ime);

				ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_sve_and.rq", params);
				return ids;
			} else {
				if (predmet != null) {
					if (zahtev != null) {
						// predmet + zahtev
						ArrayList<String> params = new ArrayList<>();
						params.add(predmet);
						params.add(zahtev);

						ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet_zahtev_and.rq", params);
						return ids;
					} else {
						if (ime != null) {
							// predmet + ime
							ArrayList<String> params = new ArrayList<>();
							params.add(predmet);
							params.add(ime);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet_ime_and.rq", params);
							return ids;
						} else {
							// predmet
							ArrayList<String> params = new ArrayList<>();
							params.add(predmet);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (zahtev != null) {
						if (ime != null) {
							// zahtev + ime
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(ime);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_zahtev_ime_and.rq",
									params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_zahtev.rq", params);
							return ids;
						}
					} else {
						// ime
						ArrayList<String> params = new ArrayList<>();
						params.add(ime);

						ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_ime.rq", params);
						return ids;
					}
				}
			}
		} else {
			// predmet + zahtev + ime

			if (predmet != null && zahtev != null && ime != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(predmet);
				params.add(zahtev);
				params.add(ime);

				ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_sve_or.rq", params);
				return ids;
			} else {
				if (predmet != null) {
					if (zahtev != null) {
						// predmet + zahtev
						ArrayList<String> params = new ArrayList<>();
						params.add(predmet);
						params.add(zahtev);

						ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet_zahtev_or.rq", params);
						return ids;
					} else {
						if (ime != null) {
							// predmet + ime
							ArrayList<String> params = new ArrayList<>();
							params.add(predmet);
							params.add(ime);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet_ime_or.rq", params);
							return ids;
						} else {
							// predmet
							ArrayList<String> params = new ArrayList<>();
							params.add(predmet);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_predmet.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (zahtev != null) {
						if (ime != null) {
							// zahtev + ime
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(ime);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_zahtev_ime_or.rq",
									params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_zahtev.rq", params);
							return ids;
						}
					} else {
						// ime
						ArrayList<String> params = new ArrayList<>();
						params.add(ime);

						ids = this.fusekiManager.query("/obavestenja", SPARQL_FILE + "obavestenje_ime.rq", params);
						return ids;
					}
				}
			}
		}
	}
	
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
