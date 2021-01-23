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
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.repository.ZalbaCutanjeRepository;

@Service
public class ZalbaCutanjeService {

	@Autowired
	ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	public ArrayList<ZalbaCutanje> getAll() throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaCutanjeRepository.getAll();
		ResourceIterator i = result.getIterator();
        Resource res = null;
        ArrayList<ZalbaCutanje> zalbe = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");
        
        while(i.hasMoreResources()) {
            
        	try {
        		Unmarshaller unmarshaller = context.createUnmarshaller();
                res = i.nextResource();
                ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource)res).getContentAsDOM());
                zalbe.add(z);
            } finally {
                
            	// don't forget to cleanup resources
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
		return zalbe;
        
	}
	
	public ArrayList<ZalbaCutanje> getAllByUserEmail(String email) throws XMLDBException, JAXBException {
		ResourceSet result = this.zalbaCutanjeRepository.getAllByUserEmail(email);
		ResourceIterator i = result.getIterator();
        Resource res = null;
        ArrayList<ZalbaCutanje> zalbe = new ArrayList<>();
        JAXBContext context = JAXBContext.newInstance("com.ftn.xml.model.zalba_cutanje");
        
        while(i.hasMoreResources()) {
            
        	try {
        		Unmarshaller unmarshaller = context.createUnmarshaller();
                res = i.nextResource();
                ZalbaCutanje z = (ZalbaCutanje) unmarshaller.unmarshal(((XMLResource)res).getContentAsDOM());
                zalbe.add(z);
            } finally {
                
            	// don't forget to cleanup resources
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
		return zalbe;
        
	}

}
