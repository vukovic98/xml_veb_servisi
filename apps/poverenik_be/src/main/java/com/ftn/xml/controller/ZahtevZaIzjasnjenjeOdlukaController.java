package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.dto.ZahtevIzjasnjenjeCutanjeFusekiDTO;
import com.ftn.xml.dto.ZahtevZaIzjasnjenjeOdlukaFusekiDTO;
import com.ftn.xml.helper.ZahtevZaIzjasnjenjeCutanjeMapper;
import com.ftn.xml.helper.ZahtevZaIzjasnjenjeOdlukaMapper;
import com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje.ZahtevZaIzjasnjenjeCutanje;
import com.ftn.xml.model.zahtev_za_izjasnjenje_odluka.ZahtevZaIzjasnjenjeOdluka;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeCutanjeService;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeOdlukaService;

@RestController
@RequestMapping(path = "/zahtev_za_izjasnjenje_odluka", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevZaIzjasnjenjeOdlukaController {

	@Autowired
	private ZahtevZaIzjasnjenjeOdlukaService zahtevService;
	
	@Autowired
	private ZahtevZaIzjasnjenjeOdlukaMapper mapper;
	
	@GetMapping(path = "/{id_zalbe}")
	public ResponseEntity<ZahtevZaIzjasnjenjeOdlukaFusekiDTO> pronadjiZahtevPoIdZalbe(@PathVariable("id_zalbe") long id_zalbe) throws XMLDBException {
		ZahtevZaIzjasnjenjeOdluka z = this.zahtevService.pronadjiPoIdZalbeOdluka(id_zalbe);
		
		ZahtevZaIzjasnjenjeOdlukaFusekiDTO dto = new ZahtevZaIzjasnjenjeOdlukaFusekiDTO();
		if(z != null) {
			dto.setId_zalbe(id_zalbe);
			dto.setVreme(z.getVreme());
		}else {
			dto.setId_zalbe(0L);
			dto.setVreme("");
		}
		return ResponseEntity.ok().body(dto);
		
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<HttpStatus> kreirajZahtev(@RequestBody ZahtevZaIzjasnjenjeOdlukaFusekiDTO zahtevDto) {
		
		
		try {

			ZahtevZaIzjasnjenjeOdluka z = mapper.zahtevDtoUKlasu(zahtevDto);

			this.zahtevService.dodajOdgovorZahtevZaIzjasnjenje(z);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
