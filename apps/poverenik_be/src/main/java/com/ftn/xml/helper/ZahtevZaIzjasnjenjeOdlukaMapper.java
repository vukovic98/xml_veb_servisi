package com.ftn.xml.helper;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.ftn.xml.dto.ZahtevZaIzjasnjenjeOdlukaFusekiDTO;
import com.ftn.xml.model.zahtev_za_izjasnjenje_odluka.ZahtevZaIzjasnjenjeOdluka;

@Component
public class ZahtevZaIzjasnjenjeOdlukaMapper {

	public ZahtevZaIzjasnjenjeOdluka zahtevDtoUKlasu(ZahtevZaIzjasnjenjeOdlukaFusekiDTO dto) {
		
		ZahtevZaIzjasnjenjeOdluka z = new ZahtevZaIzjasnjenjeOdluka();
		
		z.setIdZalbe(BigInteger.valueOf(dto.getId_zalbe()));
		z.setVreme(dto.getVreme());
		
		return z;
		
		
	}
	
}
