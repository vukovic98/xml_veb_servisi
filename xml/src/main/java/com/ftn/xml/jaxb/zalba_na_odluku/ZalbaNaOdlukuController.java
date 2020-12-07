package com.ftn.xml.jaxb.zalba_na_odluku;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zalba_na_odluku")
public class ZalbaNaOdlukuController {
	
	@GetMapping("/test")
	public ResponseEntity<String> test() throws Exception {
		
		
		return new ResponseEntity<String>(ZalbaNaOdlukuMarshaller.test(), HttpStatus.OK);
		
	}
	
}
