package com.ftn.xml.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ftn.xml.soap.odgovor.OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl;


@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    OdgovorZahtevZaIzjasnjenjeServiceSoapBindingImpl odgovorZahtevSoapBindingImpl;
    
    @Bean(name="odgovorZahtevEndpointBean")
    public Endpoint odgovorEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, odgovorZahtevSoapBindingImpl);
        endpoint.publish("/odgovorZahtevZaIzjasnjenje");
        return endpoint;
    }
}
