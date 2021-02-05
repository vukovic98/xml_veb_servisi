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
import com.ftn.xml.helper.ZahtevZaIzjasnjenjeCutanjeMapper;
import com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje.ZahtevZaIzjasnjenjeCutanje;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeCutanjeService;

@RestController
@RequestMapping(path = "/zahtev_za_izjasnjenje_cutanje", produces = MediaType.APPLICATION_XML_VALUE)
public class ZahtevZaIzjasnjenjeCutanjeController {

	@Autowired
	private ZahtevZaIzjasnjenjeCutanjeService zahtevService;
	
	@Autowired
	private ZahtevZaIzjasnjenjeCutanjeMapper mapper;
	
	@GetMapping(path = "/{id_zalbe}")
	public ResponseEntity<ZahtevIzjasnjenjeCutanjeFusekiDTO> pronadjiZahtevPoIdZalbe(@PathVariable("id_zalbe") long id_zalbe) throws XMLDBException {
		ZahtevZaIzjasnjenjeCutanje z = this.zahtevService.pronadjiPoIdZalbeCutanje(id_zalbe);
		
		ZahtevIzjasnjenjeCutanjeFusekiDTO dto = new ZahtevIzjasnjenjeCutanjeFusekiDTO();
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
	public ResponseEntity<HttpStatus> kreirajZahtev(@RequestBody ZahtevIzjasnjenjeCutanjeFusekiDTO zahtevDto) {
		
		
		try {

			ZahtevZaIzjasnjenjeCutanje z = mapper.zahtevDtoUKlasu(zahtevDto);

			this.zahtevService.dodajOdgovorZahtevZaIzjasnjenje(z);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
}
