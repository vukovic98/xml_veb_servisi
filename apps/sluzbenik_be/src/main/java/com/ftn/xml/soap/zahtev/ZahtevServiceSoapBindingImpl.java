
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ftn.xml.soap.zahtev;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.xml.service.ZahtevService;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-01-31T14:16:23.823+01:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "ZahtevService",
                      portName = "ZahtevServiceSoapBinding",
                      targetNamespace = "http://www.ftn.uns.ac.rs/zahtev",
                      wsdlLocation = "src/main/resources/static/wsdl/zahtev.wsdl",
                      endpointInterface = "com.ftn.xml.soap.zahtev.ZahtevServicePortType")
@Service          
public class ZahtevServiceSoapBindingImpl implements ZahtevServicePortType {

    private static final Logger LOG = Logger.getLogger(ZahtevServiceSoapBindingImpl.class.getName());
    
    @Autowired
    private ZahtevService zahtevService;

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.zahtev.ZahtevServicePortType#pronadjiOdbijeneZahteve(java.lang.String email)*
     */
    public com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama pronadjiOdbijeneZahteve(java.lang.String email) { 
        LOG.info("Executing operation pronadjiOdbijeneZahteve");
        System.out.println(email);
        try {
            return this.zahtevService.pronadjiOdbijeneZahteveZaKorisnika(email);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    public com.ftn.xml.model.zahtev.ListaZahtevaZaPristupInformacijama pronadjiNeodgovoreneZahteve(java.lang.String email) { 
        LOG.info("Executing operation pronadjiNeodgovoreneZahteve");
        System.out.println(email);
        try {
            return this.zahtevService.pronadjiNeodgovoreneZahteveZaKorisnika(email);
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
