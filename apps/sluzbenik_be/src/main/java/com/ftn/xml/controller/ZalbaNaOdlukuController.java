package com.ftn.xml.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.TransformerException;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.exist.xmldb.EXistResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;

import com.ftn.xml.jaxb.util.AuthenticationUtilities;
import com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist;
import com.ftn.xml.jaxb.util.MetadataExtractor;
import com.ftn.xml.jaxb.util.SparqlUtil;
import com.ftn.xml.jaxb.util.XUpdateTemplateZalbaNaOdluku;
import com.ftn.xml.jaxb.util.AuthenticationUtilities.ConnectionProperties;
import com.ftn.xml.model.zalba_na_odluku.ListaZalbiNaOdluku;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;


@RestController
@RequestMapping("/zalba_na_odluku")
public class ZalbaNaOdlukuController {
	
	private static final String SPARQL_NAMED_GRAPH_URI = "/zalbe_na_odluku";
	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";
	
	private static com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist.ConnectionProperties conn;
	
	@PostMapping("/initializeRDF")
	public ResponseEntity<HttpStatus> initializeRDFDatabase() throws IOException, SAXException,TransformerException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		
		String xmlFilePath = "data/zalba_na_odluku.xml";
		String rdfFilePath = "gen/zalba_na_odluku.rdf";
		
		MetadataExtractor metadataExtractor = new MetadataExtractor();
		
		metadataExtractor.extractMetadata(new FileInputStream(new File(xmlFilePath)), 
				new FileOutputStream(new File(rdfFilePath)));
		
		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		model.write(out,SparqlUtil.NTRIPLES);
		
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));
		
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);
		
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();
		
		return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	
	@PostMapping(path = "/initializeEXIST")
	public ResponseEntity<HttpStatus> initializeExistDatabase() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, JAXBException {
		
		this.conn = AuthenticationUtilitiesExist.loadProperties();
		
		String collectionId = "/db/documents";
		String documentId = "zalba_na_odluku.xml";
		String filePath = "data/zalba_na_odluku.xml";
		
		Class<?> cl = Class.forName(conn.driver);
		
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database","true");
		
		DatabaseManager.registerDatabase(database);
		
		Collection col = null;
		XMLResource res = null;
		OutputStream os = new ByteArrayOutputStream();
		
		try {
			
			col = getOrCreateCollection(collectionId);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			
			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zalba_na_odluku");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			ListaZalbiNaOdluku listaZalbi = (ListaZalbiNaOdluku) unmarshaller.unmarshal(new File(filePath));
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(listaZalbi, os);
			res.setContent(os);
			col.storeResource(res);
		}finally {
			if(res != null) {
				try {
					((EXistResource)res).freeResources();
				}catch (XMLDBException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if(col != null) {
				try {
					col.close();
				}catch(XMLDBException e) {
					e.printStackTrace();
				}
			}	
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException{
		return getOrCreateCollection(collectionUri, 0);
	}
	
	private static Collection getOrCreateCollection(String collectionUri,int pathSegmentOffset) throws XMLDBException{
		
		Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user,conn.password);
		
		if(col == null) {
			if(collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}
			String pathSegments[] = collectionUri.split("/");
			
			if(pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();
				
				for(int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}
				
				Collection startCol = DatabaseManager.getCollection(conn.uri + path,conn.user,conn.password);
				
				if(startCol == null) {
					
					String parentPath = path.substring(0,path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath,conn.user,conn.password);
					
					CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
					
					System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);
					col.close();
					parentCol.close();
				}else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		}else {
			return col;
		}
		
	}
	
	@PutMapping()
	public ResponseEntity<HttpStatus> addEntity() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		
		this.conn = AuthenticationUtilitiesExist.loadProperties();
		
		String collectionId = "/db/documents";
		String documentId = "zalba_na_odluku.xml";
		
		Class<?> cl = Class.forName(conn.driver);
		
		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");
		
		DatabaseManager.registerDatabase(database);
		
		Collection col = null; 
		
		try {
			
			String contextXPath = "/lista_zalbi_na_odluku";
			
			String xmlFragment = "<zalba_na_odluku vocab=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" about=\"http://www.ftn.uns.ac.rs/rdf/examples/resenje/102\">" +
					"<osnovni_podaci>" +           
	    			"<podaci_o_zaliocu>"+
		    		"<zalioc_ime property=\"pred:ime_zalioca\" datatype=\"xs:string\">Nikola</zalioc_ime>" +
		    		"<zalioc_prezime property=\"pred:prezime_zalioca\" datatype=\"xs:string\">Petrovic</zalioc_prezime>" +
		    		"<zalioc_naziv_zalbe property=\"pred:naziv_zalbe\" datatype=\"xs:string\">Zalba1</zalioc_naziv_zalbe>" +
		    		"<zalioc_adresa property=\"pred:adresa_zalioca\" datatype=\"xs:string\">Mise Dimitrijevica 44</zalioc_adresa>" +
		    		"<zalioc_sediste property=\"pred:sediste_zalioca\" datatype=\"xs:string\">Novi Sad</zalioc_sediste>" +
		    		"</podaci_o_zaliocu>"+
		    		"<podaci_o_organu>"+
		    		"<naziv property=\"pred:naziv_organa\" datatype=\"xs:string\">FTN</naziv>"+
		    		"</podaci_o_organu> "+
		    		"</osnovni_podaci>"+   
		    		"<sadrzaj>"+
			   		"<broj_zalbe property=\"pred:broj_zalbe\" datatype=\"xs:integer\">101</broj_zalbe>" +
			   		"<godina_zalbe property=\"pred:godina_zalbe\" datatype=\"xs:string\">2020</godina_zalbe>"+
			   		"<datum_odbijenog_zahteva property=\"pred:datum_odbijenog_zahteva\" datatype=\"xs:string\">2020-12-04</datum_odbijenog_zahteva>"+
			   		"<odluka_organa_vlasti property=\"pred:odluka\" datatype=\"xs:string\">Odluka organa vlasti</odluka_organa_vlasti>"+
			   		"</sadrzaj>"+
			   		"<podnozje>"+
			   		"<mesto_zakljucka_zalbe property=\"pred:mesto_zakljucka_zalbe\" datatype=\"xs:string\"></mesto_zakljucka_zalbe>"+
			   		"<datum_zakljucka_zalbe property=\"pred:datum_zakljucka_zalbe\" datatype=\"xs:string\"></datum_zakljucka_zalbe>"+
			   		"<podaci_o_zaliocu>"+
			    	"<zalioc_ime property=\"pred:ime_zalioca\" datatype=\"xs:string\">Nikola</zalioc_ime>"+
			    	"<zalioc_prezime property=\"pred:prezime_zalioca\" datatype=\"xs:string\">Petrovic</zalioc_prezime>"+
			    	"<zalioc_adresa property=\"pred:adresa_zalioca\" datatype=\"xs:string\">Mise Dimitrijevica 44</zalioc_adresa>"+
			    	"<drugi_podaci_za_kontakt property=\"pred:kontakt_zalioca\" datatype=\"xs:string\">petrovicnik96@gmail.com</drugi_podaci_za_kontakt>"+
			    	"<potpis_zalioca property=\"pred:potpis_zalioca\" datatype=\"xs:string\"></potpis_zalioca>"+
					"</podaci_o_zaliocu>"+
					"</podnozje>"+
					"</zalba_na_odluku>";
			
			col = DatabaseManager.getCollection(conn.uri+ collectionId , conn.user,conn.password);
			col.setProperty("indent", "yes");
			
			XUpdateQueryService xupadeteService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			xupadeteService.setProperty("indent", "yes");
			
			xupadeteService.updateResource(documentId, String.format(XUpdateTemplateZalbaNaOdluku.APPEND, contextXPath, xmlFragment));
		
		}finally {
			
			if(col != null) {
				try {
					col.close();
				}catch (XMLDBException e) {
					e.printStackTrace();
				}
			}
			
		}
		//add to rdf database
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);
		
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/102");
		
		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "ime_zalioca");
		Literal literal1 = model.createLiteral("Nikola");
		
		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "prezime_zalioca");
		Literal literal2 = model.createLiteral("Petrovic");
		
		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "naziv_zalbe");
		Literal literal3 = model.createLiteral("Zalba1");
		
		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "adresa_zalioca");
		Literal literal4 = model.createLiteral("Mise Dimitrijevica 44");

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "sediste_zalioca");
		Literal literal5 = model.createLiteral("Novi Sad");
		
		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "broj_zalbe");
		Literal literal6 = model.createLiteral("101");
		
		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "godina_zalbe");
		Literal literal7 = model.createLiteral("2020");
		
		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "datum_odbijenog_zahteva");
		Literal literal8 = model.createLiteral("2020-12-04");
		
		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "odluka");
		Literal literal9 = model.createLiteral("Odluka organa vlasti");
		
		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "mesto_zakljucka_zalbe");
		Literal literal10 = model.createLiteral("Novi Sad");
		
		Property property11 = model.createProperty(PREDICATE_NAMESPACE, "datum_zakljucka_zalbe");
		Literal literal11 = model.createLiteral("2021-01-12");
		
		Property property12 = model.createProperty(PREDICATE_NAMESPACE, "ime_zalioca");
		Literal literal12 = model.createLiteral("Nikola");
		
		Property property13 = model.createProperty(PREDICATE_NAMESPACE, "prezime_zalioca");
		Literal literal13 = model.createLiteral("Petrovic");
		
		Property property14 = model.createProperty(PREDICATE_NAMESPACE, "adresa_zalioca");
		Literal literal14 = model.createLiteral("Mise Dimitrijevica 44");
		
		Property property15 = model.createProperty(PREDICATE_NAMESPACE, "kontakt_zalioca");
		Literal literal15 = model.createLiteral("petrovicnik96@gmail.com");
		
		Property property16 = model.createProperty(PREDICATE_NAMESPACE, "potpis_zalioca");
		Literal literal16 = model.createLiteral("NikolaPetrovic");
		
		//adding the statements to the model
		Statement statement1 = model.createStatement(resource, property1, literal1);
		Statement statement2 = model.createStatement(resource, property2, literal2);
		Statement statement3 = model.createStatement(resource, property3, literal3);
		Statement statement4 = model.createStatement(resource, property4, literal4);
		Statement statement5 = model.createStatement(resource, property5, literal5);
		Statement statement6 = model.createStatement(resource, property6, literal6);
		Statement statement7 = model.createStatement(resource, property7, literal7);
		Statement statement8 = model.createStatement(resource, property8, literal8);
		Statement statement9 = model.createStatement(resource, property9, literal9);
		Statement statement10 = model.createStatement(resource, property10, literal10);
		Statement statement11 = model.createStatement(resource, property11, literal11);
		Statement statement12 = model.createStatement(resource, property12, literal12);
		Statement statement13 = model.createStatement(resource, property13, literal13);
		Statement statement14 = model.createStatement(resource, property14, literal14);
		Statement statement15 = model.createStatement(resource, property15, literal15);
		Statement statement16 = model.createStatement(resource, property16, literal16);
		
		model.add(statement1);
		model.add(statement2);
		model.add(statement3);
		model.add(statement4);
		model.add(statement5);
		model.add(statement6);
		model.add(statement7);
		model.add(statement8);
		model.add(statement9);
		model.add(statement10);
		model.add(statement11);
		model.add(statement12);
		model.add(statement13);
		model.add(statement14);
		model.add(statement15);
		model.add(statement16);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out,SparqlUtil.NTRIPLES);
		
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
		
		
		return new ResponseEntity<>(HttpStatus.OK);
			
	}
		
}
