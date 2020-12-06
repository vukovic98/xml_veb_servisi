package com.ftn.xml.jaxb.resenje;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/resenje")
public class ResenjeController {

	@GetMapping("/test")
	public ResponseEntity<String> test() throws Exception{
		
		return new ResponseEntity<String>(ResenjeMarshaller.test(), HttpStatus.OK);
		
		
	}
	
}
