package com.ftn.xml.soap.endpoint;

import javax.xml.ws.Endpoint;
import javax.xml.*;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ftn.xml.soap.resenje.ResenjeServiceSoapBindingImpl;
import com.ftn.xml.soap.zahtev.ZahtevServiceSoapBindingImpl;
import com.ftn.xml.soap.zahtev_za_izjasnjenje_cutnja.ZahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl;
import com.ftn.xml.soap.zahtev_za_izjasnjenje_odluka.ZahtevZaIzjasnjenjeOdlukaServiceSoapBindingImpl;

@Configuration
public class EndpointConfig {

    @Autowired
    private Bus bus;

    @Autowired
    ZahtevServiceSoapBindingImpl zahtevServiceSoapBindingImpl;
    
    @Autowired
    ZahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl zahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl;
    
    @Autowired
    ZahtevZaIzjasnjenjeOdlukaServiceSoapBindingImpl zahtevZaIzjasnjenjeOdlukaServiceSoapBindingImpl;
    
    @Autowired
    ResenjeServiceSoapBindingImpl resenjeServiceSoapBindingImpl;

    @Bean(name="resenjeEndpointBean")
    public Endpoint resenjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, resenjeServiceSoapBindingImpl);
        endpoint.publish("/resenje");
        return endpoint;
    }
    
    @Bean(name="zahtevEndpointBean")
    public Endpoint zahtevEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zahtevServiceSoapBindingImpl);
        endpoint.publish("/zahtev");
        return endpoint;
    }
    
    @Bean(name="zahtevZaIzjasnjenjeCutanjeEndpointBean")
    public Endpoint zahtevZaIzjasnjenjeCutanjeEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zahtevZaIzjasnjenjeCutnjaServiceSoapBindingImpl);
        endpoint.publish("/zahtev_za_izjasnjenje_cutanje");
        return endpoint;
    }
    
    @Bean(name="zahtevZaIzjasnjenjeOdlukaEndpointBean")
    public Endpoint zahtevZaIzjasnjenjeOdlukaEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, zahtevZaIzjasnjenjeOdlukaServiceSoapBindingImpl);
        endpoint.publish("/zahtev_za_izjasnjenje_odluka");
        return endpoint;
    }
}
