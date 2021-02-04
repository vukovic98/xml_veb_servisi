
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ftn.xml.soap.odgovor;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.xml.model.odgovor_zahtev_za_izjasnjenje.OdgovorZahtevZaIzjasnjenje;
import com.ftn.xml.service.OdgovorZahtevZaIzjasnjenjeService;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-03T17:37:51.798+01:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "OdgovorZahtevZaIzjasnjenjeService",
                      portName = "OdgovorZahtevZaIzjasnjenjeServiceSoapBinding",
                      targetNamespace = "http://ftn.uns.ac.rs/izjasnjenje/odgovor",
                      wsdlLocation = "src/main/resources/static/wsdl/odgovor_zahtev_za_izjasnjenje.wsdl",
                      endpointInterface = "com.ftn.xml.soap.odgovor.OdgovorZahtevZaIzjasnjenjeServicePortType")
@Service              
public class OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl implements OdgovorZahtevZaIzjasnjenjeServicePortType {

    private static final Logger LOG = Logger.getLogger(OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl.class.getName());

    @Autowired
    private OdgovorZahtevZaIzjasnjenjeService odgovorService;
    
    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.izjasnjenje.odgovor.OdgovorZahtevZaIzjasnjenjeServicePortType#izbrisiOdgovorZahtevZaIzjasnjenje(java.lang.String idZalbe)*
     */
    public java.lang.String izbrisiOdgovorZahtevZaIzjasnjenje(java.lang.String idZalbe) { 
        LOG.info("Executing operation izbrisiOdgovorZahtevZaIzjasnjenje");
        System.out.println(idZalbe);
        try {
        	this.odgovorService.izbrisiOdgovorZahtevZaIzjasnjenje(idZalbe);
            java.lang.String _return = "OK";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.izjasnjenje.odgovor.OdgovorZahtevZaIzjasnjenjeServicePortType#dodajOdgovorZahtevZaIzjasnjenje(rs.ac.uns.ftn.izjasnjenje.odgovor.OdgovorZahtevZaIzjasnjenje odgovorZahtevZaIzjasnjenje)*
     */
    public java.lang.String dodajOdgovorZahtevZaIzjasnjenje(OdgovorZahtevZaIzjasnjenje odgovorZahtevZaIzjasnjenje) { 
        LOG.info("Executing operation dodajOdgovorZahtevZaIzjasnjenje");
        System.out.println(odgovorZahtevZaIzjasnjenje);
        try {
        	this.odgovorService.dodajOdgovorZahtevZaIzjasnjenje(odgovorZahtevZaIzjasnjenje);
            java.lang.String _return = "OK";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
