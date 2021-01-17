package com.ftn.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ftn.xml.service.KorisnikService;

@Controller
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
}
