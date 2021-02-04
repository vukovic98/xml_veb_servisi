package com.ftn.xml.helper;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.ftn.xml.dto.ZahtevIzjasnjenjeCutanjeFusekiDTO;
import com.ftn.xml.model.zahtev_za_izjasnjenje_cutanje.ZahtevZaIzjasnjenjeCutanje;

@Component
public class ZahtevZaIzjasnjenjeCutanjeMapper {

	public ZahtevZaIzjasnjenjeCutanje zahtevDtoUKlasu(ZahtevIzjasnjenjeCutanjeFusekiDTO dto) {
		
		ZahtevZaIzjasnjenjeCutanje z = new ZahtevZaIzjasnjenjeCutanje();
		
		z.setIdZalbe(BigInteger.valueOf(dto.getId_zalbe()));
		z.setVreme(dto.getVreme());
		
		return z;
		
		
	}
	
}
