package com.ftn.xml.jaxb.zalba_cutanje;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zalba_cutanje")
public class ZalbaCutanjeController {

	@GetMapping("/test")
	public ResponseEntity<String> test() throws Exception{
		
		return new ResponseEntity<String>(ZalbaCutanjeMarshaller.test(), HttpStatus.OK);
		
		
	}
	
	
}
