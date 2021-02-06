package com.ftn.xml.repository;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;

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
	
	public static final String SPARQL_FILE = "src/main/resources/static/sparql/zalba_odluka/";
	
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
	
	public ResourceSet pronadjiPoId(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zalbi_odluka/zalba_odluka[@about='" + id_Str + "']";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	
	public List<String> naprednaPretraga(String zahtev, String mail, String organ, boolean and) throws Exception {
		List<String> ids = new ArrayList<>();

		if (and) {
			// zahtev + MAIL + ORGAN

			if (zahtev != null && mail != null && organ != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(zahtev);
				params.add(mail);
				params.add(organ);

				ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_sve_and.rq", params);
				return ids;
			} else {
				if (zahtev != null) {
					if (mail != null) {
						// zahtev + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zahtev);
						params.add(mail);

						ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev_mejl_and.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// zahtev + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(organ);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev_organ_and.rq", params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev.rq", params);
							return ids;
						}
					}
				} else {
					// zahtev = NULL
					if (mail != null) {
						if (organ != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);
							params.add(organ);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_mejl_organ_and.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_mejl.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_organ.rq", params);
						return ids;
					}
				}
			}
		} else {
			// zahtev + MAIL + ORGAN

			if (zahtev != null && mail != null && organ != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(zahtev);
				params.add(mail);
				params.add(organ);

				ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_sve_or.rq", params);
				return ids;
			} else {
				if (zahtev != null) {
					if (mail != null) {
						// zahtev + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zahtev);
						params.add(mail);

						ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev_mejl_or.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// zahtev + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(organ);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev_organ_or.rq", params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_zahtev.rq", params);
							return ids;
						}
					}
				} else {
					// zahtev = NULL
					if (mail != null) {
						if (organ != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);
							params.add(organ);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_mejl_organ_or.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_mejl.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zalba_na_odluku", SPARQL_FILE + "odluka_organ.rq", params);
						return ids;
					}
				}
			}
		}
	}
}
