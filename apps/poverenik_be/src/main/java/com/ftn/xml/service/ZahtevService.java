package com.ftn.xml.service;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.ftn.xml.model.zahtev.ZahtevZaPristupInformacijama;
import com.ftn.xml.repository.ZahtevRepository;




@Service
public class ZahtevService {

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public ArrayList<ZahtevZaPristupInformacijama> getAll() throws XMLDBException, JAXBException {
		ResourceSet result = this.zahtevRepository.getAll();
		ResourceIterator i = result.getIterator();
        Resource res = null;
        ArrayList<ZahtevZaPristupInformacijama> zahtevi = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");
        
        while(i.hasMoreResources()) {
            
        	try {
        		Unmarshaller unmarshaller = context.createUnmarshaller();
                res = i.nextResource();
                ZahtevZaPristupInformacijama z = (ZahtevZaPristupInformacijama) unmarshaller.unmarshal(((XMLResource)res).getContentAsDOM());
                zahtevi.add(z);
            } finally {
                
            	// don't forget to cleanup resources
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
		return zahtevi;
        
	}
	
	public ArrayList<ZahtevZaPristupInformacijama> getAllForUser() throws XMLDBException, JAXBException {
		ResourceSet result = this.zahtevRepository.getAll();
		ResourceIterator i = result.getIterator();
        Resource res = null;
        ArrayList<ZahtevZaPristupInformacijama> zahtevi = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zahtev");
        
        while(i.hasMoreResources()) {
            
        	try {
        		Unmarshaller unmarshaller = context.createUnmarshaller();
                res = i.nextResource();
                ZahtevZaPristupInformacijama z = (ZahtevZaPristupInformacijama) unmarshaller.unmarshal(((XMLResource)res).getContentAsDOM());
                zahtevi.add(z);
            } finally {
                
            	// don't forget to cleanup resources
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
		return zahtevi;
        
	}
}
