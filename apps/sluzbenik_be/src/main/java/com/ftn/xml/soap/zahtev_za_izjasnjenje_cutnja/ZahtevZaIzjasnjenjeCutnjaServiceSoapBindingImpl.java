
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.ftn.xml.soap.zahtev_za_izjasnjenje_cutnja;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.service.ZahtevZaIzjasnjenjeCutnjaService;

/**
 * This class was generated by Apache CXF 3.2.1
 * 2021-02-02T19:23:12.768+01:00
 * Generated source version: 3.2.1
 * 
 */

@javax.jws.WebService(
                      serviceName = "ZahtevZaIzjasnjenjeCutnjaService",
                      portName = "ZahtevZaIzjasnjenjeCutnjaServiceSoapBinding",
                      targetNamespace = "http://ftn.uns.ac.rs/zalba_cutanje",
                      wsdlLocation = "src/main/resources/static/wsdl/zahtev_za_izjasnjenje_cutanje.wsdl",
                      endpointInterface = "com.ftn.xml.soap.zahtev_za_izjasnjenje_cutnja.ZahtevZaIzjasnjenjeCutnjaServicePortType")
@Service                     
public class ZahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl implements ZahtevZaIzjasnjenjeCutnjaServicePortType {

    private static final Logger LOG = Logger.getLogger(ZahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl.class.getName());

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.zalba_cutanje.ZahtevZaIzjasnjenjeCutnjaServicePortType#dodajZahtevZaIzjasnjenjeCutnje(rs.ac.uns.ftn.zalba_cutanje.ZalbaCutanje zalbaCutanje)*
     */
    @Autowired
    private ZahtevZaIzjasnjenjeCutnjaService zahtevservice;
    
    public java.lang.String dodajZahtevZaIzjasnjenjeCutnje(ZalbaCutanje zalbaCutanje) { 
        LOG.info("Executing operation dodajZahtevZaIzjasnjenjeCutnje");
        System.out.println(zalbaCutanje);
        try {
        	this.zahtevservice.dodajZahtevZaIzjasnjenjeCutnje(zalbaCutanje);
            java.lang.String _return = "OK";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see rs.ac.uns.ftn.zalba_cutanje.ZahtevZaIzjasnjenjeCutnjaServicePortType#izbrisiZahtevZaIzjasnjenjeCutanje(java.lang.String idZalbe)*
     */
    public java.lang.String izbrisiZahtevZaIzjasnjenjeCutanje(java.lang.String idZalbe) { 
        LOG.info("Executing operation izbrisiZahtevZaIzjasnjenjeCutanje");
        System.out.println(idZalbe);
        try {
        	this.zahtevservice.izbrisiZahtevZaIzjasnjenjeCutanje(idZalbe);
            java.lang.String _return = "OK";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}