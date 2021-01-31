package com.ftn.xml.soap.endpoint;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ftn.xml.soap.zahtev.ZahtevServiceSoapBindingImpl;

@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    ZahtevServiceSoapBindingImpl zahtevServiceSoapBindingImpl;

    @Bean(name="zahtevEndpointBean")
    public Endpoint zahtevEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zahtevServiceSoapBindingImpl);
        endpoint.publish("/zahtev");
        return endpoint;
    }
}
