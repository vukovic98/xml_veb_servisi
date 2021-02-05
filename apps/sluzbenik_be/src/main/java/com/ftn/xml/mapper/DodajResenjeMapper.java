package com.ftn.xml.mapper;

import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.model.resenje.Resenje;

public class DodajResenjeMapper {

	
	public ResenjeFusekiDTO klasaUFusekiDTO(Resenje r) {
		
		ResenjeFusekiDTO dto = new ResenjeFusekiDTO();
		
		dto.setBroj(r.getBroj());
		dto.setDatum(r.getOsnovniPodaci().getDatum().toString());
		dto.setDatum_zahteva(r.getSadrzaj().getUvod().getDatumZahteva().toString());
		dto.setNaslov(r.getOsnovniPodaci().getNaslov().getContent());
		dto.setKorisnik_email(r.getOsnovniPodaci().getKorisnikEmail().getContent());
		dto.setOrgan(r.getSadrzaj().getUvod().getOrgan().getContent());
		dto.setPodnosilac(r.getSadrzaj().getUvod().getPodnosilac().getContent());
		dto.setUstanova(r.getSadrzaj().getUvod().getUstanova().getNaziv().getValue());
		dto.setDatum_zahteva(r.getSadrzaj().getUvod().getDatumZahteva().toString());
		dto.setTrazeni_dokument(r.getSadrzaj().getDonetoResenje().getTrazeniDokument().getContent());
		dto.setTekst_resenja(r.getSadrzaj().getDonetoResenje().getTekstResenja());
		dto.setTekst_obrazlozenja(r.getSadrzaj().getObrazlozenje().getTekstObrazlozenja());
		dto.setIshod(r.getIshod().getValue());
		dto.setPoverenik(r.getPoverenik().getContent());
		dto.setSud(r.getSadrzaj().getObrazlozenje().getSud());
		dto.setTaksa(r.getSadrzaj().getObrazlozenje().getTaksa().getValue());
		
		return dto;
		
		
		
		
	}
	
	
}
