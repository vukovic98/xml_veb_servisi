package com.ftn.xml.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.ResourceSet;

import com.ftn.xml.model.zalba_cutanje.ListaZalbiCutanje;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.repository.ZalbaCutanjeRepository;

@Service
public class ZalbaCutanjeService {

	@Autowired
	ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	public ListaZalbiCutanje getAll(){
		ResourceSet set = this.zalbaCutanjeRepository.getAll();
		ListaZalbiCutanje zalbe = new ListaZalbiCutanje();
		return zalbe;
	}
}
