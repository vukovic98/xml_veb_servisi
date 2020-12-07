package com.ftn.xml.jaxb.zahtev;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zahtev")
public class ZahtevController {
	
	@GetMapping("/test")
	public ResponseEntity<String> test() throws Exception{
		return new ResponseEntity<String>(ZahtevMarshaller.test(),HttpStatus.OK);
	}
}
