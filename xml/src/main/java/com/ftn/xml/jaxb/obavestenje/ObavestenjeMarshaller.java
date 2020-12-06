package com.ftn.xml.jaxb.obavestenje;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.ftn.jaxb.util.MyValidationEventHandler;
import com.ftn.jaxb.util.NSPrefixMapper;
import com.ftn.xml.jaxb.resenje.ResenjeObrazac;

public class ObavestenjeMarshaller {

	public void test() throws Exception {
		try {

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.obavestenje");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(new File("./data/obavestenje.xsd"));

			TObavestenje obavestenje = (TObavestenje) unmarshaller.unmarshal(new File("./data/obavestenje.xml"));

			unmarshaller.setSchema(schema);
			unmarshaller.setEventHandler(new MyValidationEventHandler());

			printResenje(obavestenje);
			
			Marshaller marshaller = context.createMarshaller();
			
			// PodeÅ¡avanje marshaller-a
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NSPrefixMapper());
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			// Umesto System.out-a, moÅ¾e se koristiti FileOutputStream
			FileOutputStream os = new FileOutputStream(new File("./data/resenje_marshall.xml"));
			marshaller.marshal(obavestenje, os);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
