package com.ftn.xml.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;

@Repository
public class ZalbaCutanjeRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "zalba_cutanje.xml";
	
	private static final String TARGET_NAMESPACE = "http://ftn.uns.ac.rs/zalba_cutanje";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";
	
	public static final String SPARQL_FILE = "src/main/resources/static/sparql/zalba_cutanje/";
	
	@Autowired
	private ExistManager existManager;
	
	@Autowired
	private FusekiManager fusekiManager;
	
	public List<String> naprednaPretraga(String zahtev, String mail, String organ, boolean and) throws Exception {
		List<String> ids = new ArrayList<>();

		if (and) {
			// zahtev + MAIL + ORGAN

			if (zahtev != null && mail != null && organ != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(zahtev);
				params.add(mail);
				params.add(organ);

				ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_sve_and.rq", params);
				return ids;
			} else {
				if (zahtev != null) {
					if (mail != null) {
						// zahtev + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zahtev);
						params.add(mail);

						ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev_mejl_and.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// zahtev + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(organ);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev_organ_and.rq", params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev.rq", params);
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

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_mejl_organ_and.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_mejl.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_organ.rq", params);
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

				ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_sve_or.rq", params);
				return ids;
			} else {
				if (zahtev != null) {
					if (mail != null) {
						// zahtev + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(zahtev);
						params.add(mail);

						ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev_mejl_or.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// zahtev + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);
							params.add(organ);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev_organ_or.rq", params);
							return ids;
						} else {
							// zahtev
							ArrayList<String> params = new ArrayList<>();
							params.add(zahtev);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_zahtev.rq", params);
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

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_mejl_organ_or.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_mejl.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zalbe_cutanje", SPARQL_FILE + "cutanje_organ.rq", params);
						return ids;
					}
				}
			}
		}
	}
	
	public long ukupanBrojZalbiNaCutanje() {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set.getSize();
		} catch (Exception e) {
			return 0;
		}
	}
	
	public void dodajZalbuIzTeksta(String zalba, ZalbaCutanje z) throws Exception {
		String contextXPath = "/lista_zalbi_cutanje";
		this.existManager.append(collectionId, documentId, contextXPath, zalba, APPEND);
		this.fusekiManager.dodajZalbuCutanje(z);
	}
	
	public ResourceSet getAll() {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet pretraga(String text) {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje[zaglavlje/primalac_zalbe/naziv_primaoca[contains(., '"+text+"')]" + 
				" or sadrzaj/naziv_organa[contains(.,'"+text+"')] or sadrzaj/podaci_o_zahtevu_i_informacijama[contains(.,'"+text+"')]" + 
				" or podnozje/podnosilac_zalbe/ime_i_prezime[contains(., '"+text+"')]]";
		
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet getAllByUserEmail(String email) {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje/podnozje/podnosilac_zalbe[korisnik_email='" + email + "']";
		try {
			return this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
		} catch (Exception e) {
			return null;
		}
	}
	
	public ResourceSet pronadjiPoId(long id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje[@about='" + id_Str + "']";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean postojiZalbaNaZahtev(long id) throws XMLDBException {
		String xPath = "/lista_zalbi_cutanje/zalba_cutanje[broj_zahteva=" + id + "]";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			System.out.println(xPath + set.toString());
			if (set.getSize() != 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
