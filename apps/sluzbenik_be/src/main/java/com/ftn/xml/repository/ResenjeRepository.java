package com.ftn.xml.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.dto.ResenjeFusekiDTO;

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
	
	public static final String SPARQL_FILE = "src/main/resources/static/sparql/resenje/";

	@Autowired
	private ExistManager existManager;
	
	@Autowired
	private FusekiManager fusekiManager;
	
	public List<String> naprednaPretraga(String zalba, String ishod, String korisnik, boolean and) throws Exception {
		List<String> ids = new ArrayList<>();

		if (and) {
			// IME + MAIL + ORGAN

			if (zalba != null && ishod != null && korisnik != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(zalba);
				params.add(ishod);
				params.add(korisnik);

				ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_sve_and.rq", params);
				return ids;
			} else {
				if (zalba != null) {
					if (ishod != null) {
						// IME + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zalba);
						params.add(ishod);

						ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba_ishod_and.rq", params);
						return ids;
					} else {
						if (korisnik != null) {
							// IME + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zalba);
							params.add(korisnik);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba_korisnik_and.rq", params);
							return ids;
						} else {
							// IME
							ArrayList<String> params = new ArrayList<>();
							params.add(zalba);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (ishod != null) {
						if (korisnik != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(ishod);
							params.add(korisnik);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_ishod_korisnik_and.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(ishod);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_ishod.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(korisnik);

						ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_korisnik.rq", params);
						return ids;
					}
				}
			}
		} else {
			// IME + MAIL + ORGAN

			if (zalba != null && ishod != null && korisnik != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(zalba);
				params.add(ishod);
				params.add(korisnik);

				ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_sve_or.rq", params);
				return ids;
			} else {
				if (zalba != null) {
					if (ishod != null) {
						// IME + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zalba);
						params.add(ishod);

						ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba_ishod_or.rq", params);
						return ids;
					} else {
						if (korisnik != null) {
							// IME + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zalba);
							params.add(korisnik);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba_korisnik_or.rq", params);
							return ids;
						} else {
							// IME
							ArrayList<String> params = new ArrayList<>();
							params.add(zalba);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_zalba.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (ishod != null) {
						if (korisnik != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(ishod);
							params.add(korisnik);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_ishod_korisnik_or.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(ishod);

							ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_ishod.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(korisnik);

						ids = this.fusekiManager.query("/resenja", SPARQL_FILE + "resenje_korisnik.rq", params);
						return ids;
					}
				}
			}
		}
	}

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
	
	public long ukupanBrojResenja() {
		String xPath = "/lista_resenja/resenje";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set.getSize();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean sacuvajResenje(String z, ResenjeFusekiDTO dto, int index) {
		try {
			this.existManager.append(collectionId, documentId, "/lista_resenja", z, APPEND);
			this.fusekiManager.dodajResenje(index + "", dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ResourceSet getAll() {
		String xPath = "/lista_resenja/resenje";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
}
