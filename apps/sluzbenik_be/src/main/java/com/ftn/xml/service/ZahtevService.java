package com.ftn.xml.service;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.dto.ZahtevFusekiDTO;
import com.ftn.xml.jaxb.util.XSLFOTransformerZahtev;
import com.ftn.xml.mapper.DodajZahtevMaper;
import com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.ZahtevRepository;
import com.ftn.xml.repository.ZalbaCutanjeRepository;
import com.ftn.xml.repository.ZalbaNaOdlukuRepository;
import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import com.ximpleware.XMLModifier;

@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	@Autowired
	private ZalbaNaOdlukuRepository zalbaNaOdlukuRepo;

	public boolean odobriZahtev(String id) {
		return this.zahtevRepository.odobriZahtev(id);
	}

	public boolean odbijZahtev(String id) {
		return this.zahtevRepository.odbijZahtev(id);
	}

	public long ukupanBrojZahteva() {
		return this.zahtevRepository.ukupanBrojZahteva();
	}

	public long ukupanBrojOdnijenihZahteva() {
		return this.zahtevRepository.ukupanBrojOdbijenihZahteva();
	}
	
	public ListaZahtevaZaPristupInformacijama naprednaPretraga(String ime, String mail, String organ, boolean and) {
		List<String> ids = new ArrayList<>();
		try {
			ids = this.zahtevRepository.naprednaPretraga(ime, mail, organ, and);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ids = (ArrayList<String>) ids;
		
		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();
		
		for(String i : ids) {
			ZahtevZaPristupInformacijama z = this.pronadjiZahtevPoId(Long.parseLong(i));
			
			lista.getZahtevZaPristupInformacijama().add(z);
		}
		
		return lista;
		
	}

	public ListaZahtevaZaPristupInformacijama pretraga(String text) {
		ResourceSet set = this.zahtevRepository.pretraga(text);

		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getZahtevZaPristupInformacijama().add(zahtev);

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

	public boolean dodajZahtev(ZahtevZaPristupInformacijama z, int index) {

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			Marshaller marshaller = context.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);

			StringWriter zahtevSW = new StringWriter();

			marshaller.marshal(z, zahtevSW);

			String zahtev = zahtevSW.toString();

			String changedZahtev = this.removeNamespace(zahtev);

			DodajZahtevMaper mapper = new DodajZahtevMaper();

			ZahtevFusekiDTO dto = mapper.klasaUFusekiDTO(z);

			return this.zahtevRepository.sacuvajZahtev(changedZahtev, dto, index);

		} catch (Exception e) {
			return false;
		}
	}

	public ListaZahtevaZaPristupInformacijama pronadjiSveZahteve() {
		ResourceSet set = this.zahtevRepository.pronadjiSveZahteve();

		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getZahtevZaPristupInformacijama().add(zahtev);

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

	public ListaZahtevaZaPristupInformacijama pronadjiZahteveZaKorisnika(String email) {
		ResourceSet set = this.zahtevRepository.pronadjiZahteveZaKorisnika(email);

		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					lista.getZahtevZaPristupInformacijama().add(zahtev);

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

	public ListaZahtevaZaPristupInformacijama pronadjiOdbijeneZahteveZaKorisnika(String email) throws XMLDBException {
		ResourceSet set = this.zahtevRepository.pronadjiOdbijeneZahteveZaKorisnika(email);
		System.out.println("IZ metode" + set.getSize());
		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator(); //null ?
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());
					
					String[] about = zahtev.getAbout().split("/");
					long id = Long.parseLong(about[about.length-1]);
					
					boolean postoji = this.zalbaNaOdlukuRepo.postojiZalbaNaZahtev(id);
					
//					if (!postoji)
//						lista.getZahtevZaPristupInformacijama().add(zahtev);
					
					lista.getZahtevZaPristupInformacijama().add(zahtev);

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

	public ListaZahtevaZaPristupInformacijama pronadjiNeodgovoreneZahteveZaKorisnika(String email) {
		//да не може више пута да се жали на исти захтев ДОДАТИ
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		ResourceSet set = this.zahtevRepository.pronadjiNeodgovoreneZahteveZaKorisnika(email);

		ListaZahtevaZaPristupInformacijama lista = new ListaZahtevaZaPristupInformacijama();

		ResourceIterator i;
		try {
			i = set.getIterator();
		} catch (XMLDBException e) {
			return null;
		}
		Resource res = null;

		try {
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

			while (i.hasMoreResources()) {

				try {
					Unmarshaller unmarshaller = context.createUnmarshaller();
					res = i.nextResource();

					ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
							.unmarshal(((XMLResource) res).getContentAsDOM());

					String[] about = zahtev.getAbout().split("/");
					long id = Long.parseLong(about[about.length-1]);
					// da li je proslo 5 min

					Date now = new Date();
					Date d = formatter.parse(zahtev.getPodnozje().getMestoIDatum().getDatumZahteva().getValue());

					long diffInMillies = Math.abs(now.getTime() - d.getTime());
					long diff = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
					
					boolean postojiZalba = this.zalbaCutanjeRepository.postojiZalbaNaZahtev(id);
					
					if (diff > 5 && !postojiZalba)
						lista.getZahtevZaPristupInformacijama().add(zahtev);

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

	public ZahtevZaPristupInformacijama pronadjiZahtevPoId(long id) {
		ResourceSet set = this.zahtevRepository.pronadjiPoId(id);

		try {
			if (set.getSize() == 1) {

				JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");

				Unmarshaller unmarshaller = context.createUnmarshaller();
				Resource res = set.getResource(0);

				ZahtevZaPristupInformacijama zahtev = (ZahtevZaPristupInformacijama) unmarshaller
						.unmarshal(((XMLResource) res).getContentAsDOM());

				return zahtev;
			} else
				return null;
		} catch (Exception e) {
			return null;
		}

	}

	public String pronadjiZahtevPoId_Raw(long id) {
		ResourceSet set = this.zahtevRepository.pronadjiPoId(id);

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
		XSLFOTransformerZahtev transformer = null;

		try {
			transformer = new XSLFOTransformerZahtev();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiZahtevPoId_Raw(id);

		boolean ok = false;
		String pdf_path = "src/main/resources/static/pdf/zahtev_" + id + ".pdf";

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
		XSLFOTransformerZahtev transformer = null;

		try {
			transformer = new XSLFOTransformerZahtev();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		String zahtev = this.pronadjiZahtevPoId_Raw(zahtev_id);

		boolean ok = false;
		String html_path = "src/main/resources/static/html/zahtev_" + zahtev_id + ".html";

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
