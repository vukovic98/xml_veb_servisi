package com.ftn.xml.repository;

import java.util.ArrayList;
import java.util.List;

import org.exist.xupdate.XUpdateProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.db.ExistManager;
import com.ftn.xml.db.FusekiManager;
import com.ftn.xml.dto.ZahtevFusekiDTO;
import com.itextpdf.text.log.SysoCounter;

@Repository
public class ZahtevRepository {

	private String collectionId = "/db/sluzbenik";
	private String documentId = "zahtev.xml";

	private static final String TARGET_NAMESPACE = "http://www.ftn.uns.ac.rs/zahtev";
	private static final String ID_STRING = "http://www.ftn.uns.ac.rs/rdf/examples/zahtev/";

	public static final String APPEND = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:append select=\"%1$s\" child=\"last()\">%2$s</xu:append>"
			+ "</xu:modifications>";

	public static final String UPDATE = "<xu:modifications version=\"1.0\" xmlns:xu=\"" + XUpdateProcessor.XUPDATE_NS
			+ "\" xmlns=\"" + TARGET_NAMESPACE + "\">" + "<xu:update select=\"%1$s\">%2$s</xu:update>"
			+ "</xu:modifications>";

	public static final String SPARQL_FILE = "src/main/resources/static/sparql/zahtev/";

	@Autowired
	private ExistManager existManager;

	@Autowired
	private FusekiManager fusekiManager;

	public long ukupanBrojZahteva() {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set.getSize();
		} catch (Exception e) {
			return 0;
		}
	}

	public List<String> naprednaPretraga(String ime, String mail, String organ, boolean and) throws Exception {
		List<String> ids = new ArrayList<>();

		if (and) {
			// IME + MAIL + ORGAN

			if (ime != null && mail != null && organ != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(ime);
				params.add(mail);
				params.add(organ);

				ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_sve_and.rq", params);
				return ids;
			} else {
				if (ime != null) {
					if (mail != null) {
						// IME + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(ime);
						params.add(mail);

						ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime_mail_and.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// IME + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(ime);
							params.add(organ);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime_organ_and.rq", params);
							return ids;
						} else {
							// IME
							ArrayList<String> params = new ArrayList<>();
							params.add(ime);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (mail != null) {
						if (organ != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);
							params.add(organ);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_mail_organ_and.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_mail.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_organ.rq", params);
						return ids;
					}
				}
			}
		} else {
			// IME + MAIL + ORGAN

			if (ime != null && mail != null && organ != null) {
				ArrayList<String> params = new ArrayList<>();
				params.add(ime);
				params.add(mail);
				params.add(organ);

				ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_sve_or.rq", params);
				return ids;
			} else {
				if (ime != null) {
					if (mail != null) {
						// IME + MAIL
						ArrayList<String> params = new ArrayList<>();
						params.add(ime);
						params.add(mail);

						ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime_mail_or.rq", params);
						return ids;
					} else {
						if (organ != null) {
							// IME + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(ime);
							params.add(organ);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime_organ_or.rq", params);
							return ids;
						} else {
							// IME
							ArrayList<String> params = new ArrayList<>();
							params.add(ime);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_ime.rq", params);
							return ids;
						}
					}
				} else {
					// IME = NULL
					if (mail != null) {
						if (organ != null) {
							// MAIL + ORGAN
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);
							params.add(organ);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_mail_organ_or.rq",
									params);
							return ids;
						} else {
							// MAIL
							ArrayList<String> params = new ArrayList<>();
							params.add(mail);

							ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_mail.rq", params);
							return ids;
						}
					} else {
						// ORGAN
						ArrayList<String> params = new ArrayList<>();
						params.add(organ);

						ids = this.fusekiManager.query("/zahtevi", SPARQL_FILE + "zahtev_organ.rq", params);
						return ids;
					}
				}
			}
		}
	}

	public long ukupanBrojOdbijenihZahteva() {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[status=\"ODBIJEN\"]";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set.getSize();
		} catch (Exception e) {
			return 0;
		}
	}

	public ResourceSet pretraga(String text) {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[podaci_o_organu/naziv[contains(., '"
				+ text + "')]" + " or podaci_o_organu/sediste[contains(., '" + text + "')]"
				+ " or sadrzaj/opis_trazene_informacije[contains(., '" + text + "')]"
				+ " or podnozje/informacije_o_traziocu/ime_i_prezime[contains(., '" + text + "')]]";
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean odobriZahtev(String id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[@about='" + id_Str
				+ "']/status";

		try {
			this.existManager.update(collectionId, documentId, xPath, "ODOBREN", UPDATE);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean odbijZahtev(String id) {
		String id_Str = ID_STRING + id;
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[@about='" + id_Str
				+ "']/status";

		try {
			this.existManager.update(collectionId, documentId, xPath, "ODBIJEN", UPDATE);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ResourceSet pronadjiSveZahteve() {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet pronadjiZahteveZaKorisnika(String email) {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama"
				+ "[podnozje/informacije_o_traziocu/korisnik_email='" + email + "']";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			return null;
		}
	}

	public ResourceSet pronadjiOdbijeneZahteveZaKorisnika(String email) {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama"
				+ "[podnozje/informacije_o_traziocu/korisnik_email='" + email + "' and status=\"ODBIJEN\"]";
		
		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);
			return set;
		} catch (Exception e) {		
			return null;
		}
	}

	public ResourceSet pronadjiNeodgovoreneZahteveZaKorisnika(String email) {
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama"
				+ "[podnozje/informacije_o_traziocu/korisnik_email='" + email + "' and status=\"CEKANJE\"]";

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
		String xPath = "/lista_zahteva_za_pristup_informacijama/zahtev_za_pristup_informacijama[@about='" + id_Str
				+ "']";

		ResourceSet set;
		try {
			set = this.existManager.retrieve(collectionId, xPath, TARGET_NAMESPACE);

			return set;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean sacuvajZahtev(String z, ZahtevFusekiDTO dto, int index) {
		try {
			this.existManager.append(collectionId, documentId, "/lista_zahteva_za_pristup_informacijama", z, APPEND);
			this.fusekiManager.dodajZahtev(index + "", dto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
