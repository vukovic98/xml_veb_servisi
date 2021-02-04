package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.ObavestenjeFusekiDTO;
import com.ftn.xml.jaxb.util.XSLFOTransformerObavestenje;
import com.ftn.xml.mapper.DodajObavestenjeMapper;
import com.ftn.xml.model.obavestenje.ListaObavestenja;
import com.ftn.xml.model.obavestenje.Obavestenje;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.ObavestenjeRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ObavestenjeService {

	@Autowired
	private ObavestenjeRepository obavestenjeRepository;

	@Autowired
	private ZahtevService zahtevService;

	public boolean proveraPotvrdeZahteva(long zahtev_id) {
		return this.obavestenjeRepository.proveraPotvrdeZahteva(zahtev_id);
	}
	
	public ListaObavestenja naprednaPretraga(String predmet, String zahtev, String ime, boolean and) {
		List<String> ids = new ArrayList<>();
		try {
			ids = this.obavestenjeRepository.naprednaPretraga(predmet, zahtev, ime, and);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ids = (ArrayList<String>) ids;
		
		ListaObavestenja lista = new ListaObavestenja();
		
		for(String i : ids) {
			Obavestenje z = this.pronadjiObavestenjePoId(Long.parseLong(i));
			
			lista.getObavestenje().add(z);
		}
		
		return lista;
		
	}
	
	public Obavestenje pronadjiObavestenjePoId(long id) {
		ResourceSet set = this.obavestenjeRepository.pronadjiPoId(id);

		try {
			if (set.getSize() == 1) {

				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.obavestenje");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Resource res = set.getResource(0);

				Obavestenje obavestenje = (Obavestenje) unmarshaller
						.unmarshal(((XMLResource) res).getContentAsDOM());

				return obavestenje;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}

	}
	
	public ListaObavestenja pretraga(String text) {
		ResourceSet set = this.obavestenjeRepository.pretraga(text);
		
		ListaObavestenja lista = new ListaObavestenja();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.obavestenje");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					Obavestenje o = (Obavestenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getObavestenje().add(o);

				} finally {
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean dodajObavestenje(String xml, Obavestenje o) {
		DodajObavestenjeMapper mapper = new DodajObavestenjeMapper();
		
		ObavestenjeFusekiDTO dto = mapper.objectToFusekiDTO(o);
		
		String[] about = o.getAbout().split("/");
		
		boolean ok = this.obavestenjeRepository.dodajObavestenje(xml, dto, about[about.length-1]);
		
		if(ok) {
			this.zahtevService.odobriZahtev(o.getBrojZahteva().getValue()+"");
			
			return true;
		}
		else
			return false;
	}

	public ListaObavestenja pronadjiSvaObavestenja() {
		ResourceSet set = this.obavestenjeRepository.pronadjiSvaObavestenja();

		ListaObavestenja lista = new ListaObavestenja();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.obavestenje");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					Obavestenje o = (Obavestenje) unmarshaller.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getObavestenje().add(o);

				} finally {
					try {
						((EXistResource) res).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public ListaObavestenja pronadjiSvaObavestenjaZaKorisnika(String mail) {

		ListaObavestenja obavestenja = this.pronadjiSvaObavestenja();
		ListaZahtevaZaPristupInformacijama zahtevi = this.zahtevService.pronadjiZahteveZaKorisnika(mail);
		ListaObavestenja obavestenja_korisnika = new ListaObavestenja();
		
		ArrayList<Integer> id_zahteva = new ArrayList<>();
		
		for (ZahtevZaPristupInformacijama z : zahtevi.getZahtevZaPristupInformacijama()) {
			String[] id = z.getAbout().split("/");
			id_zahteva.add(Integer.parseInt(id[id.length - 1]));
		}
		
		for (Obavestenje o : obavestenja.getObavestenje()) {
			int value = o.getBrojZahteva().getValue();
			if(id_zahteva.contains(value)) {
				obavestenja_korisnika.getObavestenje().add(o);
			}
		}
		
		return obavestenja_korisnika;

	}
	
	public String pronadjiObavestenjePoId_Raw(long id) {
		ResourceSet set = this.obavestenjeRepository.pronadjiPoId(id);

		try {
			if (set.getSize() == 1) {

				Resource res = set.getResource(0);

				String result = (String) res.getContent();

				String finalRes = this.removeNamespace(result);

				return finalRes;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}
	
	public String removeNamespace(String xml) {
		try {
			VTDGen vg = new VTDGen();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			vg.setDoc(xml.getBytes());
			vg.parse(false);
			VTDNav vn = vg.getNav();
			AutoPilot ap = new AutoPilot(vn);
			XMLModifier xm = new XMLModifier(vn);
			ap.selectXPath("//@xmlns");
			int i = 0;
			while ((i = ap.evalXPath()) != -1) {
				xm.remove();
			}
			xm.output(baos);

			return baos.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String generisiPDF(long id) {
		XSLFOTransformerObavestenje transformer = null;

		try {
			transformer = new XSLFOTransformerObavestenje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiObavestenjePoId_Raw(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/obavestenje_" + id + ".pdf";

		try {
			ok = transformer.generatePDF(zahtev, pdf_path);
			if (ok)
				return pdf_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String generisiHTML(long zahtev_id) {
		XSLFOTransformerObavestenje transformer = null;

		try {
			transformer = new XSLFOTransformerObavestenje();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiObavestenjePoId_Raw(zahtev_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/obavestenje_" + zahtev_id + ".html";

		try {
			ok = transformer.generateHTML(zahtev, html_path);
			if (ok)
				return html_path;
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
