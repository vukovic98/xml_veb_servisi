package com.ftn.xml.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ftn.xml.soap.izvestaj.IzvestajServiceSoapBindingImpl;
import com.ftn.xml.soap.odgovor.OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl;


@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl odgovorZahtevSoapBindingImpl;
    
    @Autowired
    private IzvestajServiceSoapBindingImpl izvestajServiceSoapBindingImpl;
    
    @Bean(name="izvestajEndpointBean")
    public Endpoint izvestajEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, izvestajServiceSoapBindingImpl);
        endpoint.publish("/izvestaj");
        return endpoint;
    }
    
    @Bean(name="odgovorZahtevEndpointBean")
    public Endpoint odgovorEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, odgovorZahtevSoapBindingImpl);
        endpoint.publish("/odgovorZahtevZaIzjasnjenje");
        return endpoint;
    }
}
