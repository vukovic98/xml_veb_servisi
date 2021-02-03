package com.ftn.xml.mapper;

import com.ftn.xml.dto.ObavestenjeFusekiDTO;
import com.ftn.xml.model.obavestenje.Obavestenje;

public class DodajObavestenjeMapper {
	
	public ObavestenjeFusekiDTO objectToFusekiDTO(Obavestenje o) {
		ObavestenjeFusekiDTO dto = new ObavestenjeFusekiDTO();
		
		dto.setAdresa_podnosioca(o.getOsnovniPodaci().getPodaciOPodnosiocu().getAdresaPodnosioca().getContent());
		dto.setBr_predmeta(o.getOsnovniPodaci().getPodaciOOrganu().getBrojPredmeta().getValue()+"");
		dto.setBroj_zahteva(o.getBrojZahteva().getValue()+"");
		dto.setDatum_zahteva(o.getOsnovniPodaci().getPodaciOOrganu().getDatumZahteva().getValue());
		dto.setGodina_zahteva(o.getSadrzaj().getGodinaZahteva().getValue()+"");
		dto.setIme_podnosioca(o.getOsnovniPodaci().getPodaciOPodnosiocu().getImeIPrezime().getContent());
		dto.setNaziv_podnosioca(o.getOsnovniPodaci().getPodaciOPodnosiocu().getNazivPodnosioca().getContent());
		dto.setNaziv_ustanove(o.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getContent());
		dto.setOpis(o.getSadrzaj().getOpisTrazeneInformacije().getContent());
		dto.setSediste_ustanove(o.getOsnovniPodaci().getPodaciOOrganu().getSediste().getContent());
		
		return dto;
	}

}
