package com.ftn.xml.controllers;



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
import com.ftn.xml.jaxb.util.XUpdateTemplateZahtev;
import com.ftn.xml.jaxb.util.AuthenticationUtilities;
import com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist;
import com.ftn.xml.jaxb.util.MetadataExtractor;
import com.ftn.xml.jaxb.util.SparqlUtil;
import com.ftn.xml.jaxb.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.jaxb.util.AuthenticationUtilities.ConnectionProperties;

@RestController
@RequestMapping("/zahtev")
public class ZahtevController {

	private static final String SPARQL_NAMED_GRAPH_URI = "/zalbe_cutanje";
	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";

	private static com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist.ConnectionProperties conn;

	@PostMapping("/initializeRDF")
	public ResponseEntity<HttpStatus> initializeRDFDatabase() throws IOException, SAXException, TransformerException {

		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String xmlFilePath = "data/zahtev.xml";

		String rdfFilePath = "gen/zahtev.rdf";

		MetadataExtractor metadataExtractor = new MetadataExtractor();

		metadataExtractor.extractMetadata(new FileInputStream(new File(xmlFilePath)),
				new FileOutputStream(new File(rdfFilePath)));

		Model model = ModelFactory.createDefaultModel();
		model.read(rdfFilePath);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		model.write(out, SparqlUtil.NTRIPLES);

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/initializeEXIST")
	public ResponseEntity<HttpStatus> initializeExistDatabase() throws XMLDBException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException {

		this.conn = AuthenticationUtilitiesExist.loadProperties();
		// initialize collection and document identifiers
		String collectionId = "/db/documents";
		String documentId = "zahtev.xml";
		String filePath = "data/zahtev.xml";

		Class<?> cl = Class.forName(conn.driver);

		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");

		// entry point for the API which enables you to get the Collection reference
		DatabaseManager.registerDatabase(database);

		// a collection of Resources stored within an XML database
		Collection col = null;
		XMLResource res = null;
		OutputStream os = new ByteArrayOutputStream();

		try {

			col = getOrCreateCollection(collectionId);

			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zahtev");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			ListaZahtevaZaPristupInformacijama listaZahteva = (ListaZahtevaZaPristupInformacijama) unmarshaller
					.unmarshal(new File(filePath));
//			bookstore.getBook().add(createTestBook());

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(listaZahteva, os);

			res.setContent(os);

			col.storeResource(res);

		} finally {

			// don't forget to cleanup
			if (res != null) {
				try {
					((EXistResource) res).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}

			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping()
	public ResponseEntity<HttpStatus> addEntity() throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {

		this.conn = AuthenticationUtilitiesExist.loadProperties();
		
		String collectionId = "/db/documents";
    	String documentId = "zahtev.xml";

        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        
        try { 

    		String contextXPath = "/lista_zahteva_za_pristup_informacijama";
    		
    		String xmlFragment ="<zahtev_za_pristup_informacijama vocab=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" about=\"http://www.ftn.uns.ac.rs/rdf/examples/zahtev/333\">" + 
    				" <podaci_o_organu>" + 
    				" <naziv property=\"pred:naziv_ustanove\" datatype=\"xs:string\">FPN</naziv>" + 
    				" <sediste property=\"pred:sediste_ustanove\" datatype=\"xs:string\">Novi Pazar</sediste>" + 
    				" </podaci_o_organu>" + 
    				" <sadrzaj>" + 
    				" <zahtevi>" + 
    				" <zahtev otkaceno = \"false\" id=\"s_1\" zahtev=\"обавештење да ли поседује тражену информацију\"  ></zahtev>;" + 
    			    " <zahtev otkaceno = \"false\" id=\"s_2\" zahtev=\"увид у документ који садржи тражену информацију\" ></zahtev>;" + 
    				" <zahtev otkaceno = \"false\" id=\"s_3\" zahtev=\"копију документа који садржи тражену информацију\" ></zahtev>;" + 
    				" <zahtev otkaceno = \"false\" id=\"s_4\" zahtev=\"достављање копије документа који садржи тражену информацију\">" + 
    				" <nacini_dostave>" + 
    				" <nacin otkaceno = \"false\" id=\"ps_1\" nacin=\"поштом\" ></nacin>" + 
    				" <nacin otkaceno = \"false\" id=\"ps_2\" nacin=\"електронском поштом\" ></nacin>" + 
    				" <nacin otkaceno = \"false\" id=\"ps_3\" nacin=\"факсом\"></nacin>" + 
    				" <nacin otkaceno = \"false\" id=\"ps_4\" nacin=\"на други начин:\">" + 
    				"  <drugi_nacin></drugi_nacin>" + 
    				"  </nacin>" + 
    				" </nacini_dostave>" + 
    				"  </zahtev>" + 
    				"  </zahtevi>" + 
    				" <opis_trazene_informacije property=\"pred:opis_informacije\" datatype=\"xs:string\">Informacija mala</opis_trazene_informacije>" + 
    				" </sadrzaj>" + 
    				" <podnozje>" + 
    				"  <mesto_i_datum>" + 
    				" <mesto>Novi Sad</mesto>" + 
    				" <datum_zahteva property=\"pred:datum_zahteva\" datatype=\"xs:string\" >2020-12-05</datum_zahteva>" + 
    				" </mesto_i_datum>" + 
    				" <informacije_o_traziocu>" + 
    				" <ime_i_prezime property=\"pred:ime_trazioca\" datatype=\"xs:string\" >Maja Milic</ime_i_prezime>" + 
    				" <adresa>" + 
    				" <ulica property=\"pred:ulica_trazioca\" datatype=\"xs:string\">Jevrejska</ulica>" + 
    				"  <broj property=\"pred:broj_kuce_trazioca\" datatype=\"xs:integer\">19</broj>" + 
    				" <mesto property=\"pred:mesto_trazioca\" datatype=\"xs:string\">Novi Pazar</mesto>" + 
    				" </adresa>" + 
    				"  <kontakt property=\"pred:kontakt_trazioca\" datatype=\"xs:string\">021228896</kontakt>" + 
    				"   <potpis></potpis>" + 
    				" </informacije_o_traziocu>" + 
    				"  </podnozje>" + 
    				" </zahtev_za_pristup_informacijama>" ;
        	
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty("indent", "yes");
        	
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            
            xupdateService.updateResource(documentId, String.format(XUpdateTemplateZahtev.APPEND, contextXPath, xmlFragment));
            
        } finally {
        	
            // don't forget to cleanup
            if(col != null) {
                try { 
                	col.close();
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
        }
        
        //Adding metadata to RDF database
        
        ConnectionProperties conn = AuthenticationUtilities.loadProperties();
		
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually 
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/zahtev/333");
		
		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "naziv_ustanove");
		Literal literal1 = model.createLiteral("FTN");
		
		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "sediste_ustanove");
		Literal literal2 = model.createLiteral("Novi Pazar");
		
		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "opis_informacije");
		Literal literal3 = model.createLiteral("Informacija mala");
		
		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "datum_zahteva");
		Literal literal4 = model.createLiteral("2020-12-05");
		
		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "ime_trazioca");
		Literal literal5 = model.createLiteral("Maja Milic");
		
		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "ulica_trazioca");
		Literal literal6 = model.createLiteral("Jevrejska");
		
		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_trazioca");
		Literal literal7 = model.createTypedLiteral(19);
		
		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "mesto_trazioca");
		Literal literal8 = model.createLiteral("Novi Pazar");
		
		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "kontakt_trazioca");
		Literal literal9 = model.createLiteral("021228896");
		
		// Adding the statements to the model
		Statement statement1 = model.createStatement(resource, property1, literal1);
		Statement statement2 = model.createStatement(resource, property2, literal2);
		Statement statement3 = model.createStatement(resource, property3, literal3);
		Statement statement4 = model.createStatement(resource, property4, literal4);
		Statement statement5 = model.createStatement(resource, property5, literal5);
		Statement statement6 = model.createStatement(resource, property6, literal6);
		Statement statement7 = model.createStatement(resource, property7, literal7);
		Statement statement8 = model.createStatement(resource, property8, literal8);
		Statement statement9 = model.createStatement(resource, property9, literal9);

		model.add(statement1);
		model.add(statement2);
		model.add(statement3);
		model.add(statement4);
		model.add(statement5);
		model.add(statement6);
		model.add(statement7);
		model.add(statement8);
		model.add(statement9);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);
		
		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, new String(out.toByteArray()));
		
		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
        
        
        return new ResponseEntity<>(HttpStatus.OK);
	}

	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
		return getOrCreateCollection(collectionUri, 0);
	}

	private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

		Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

		// create the collection if it does not exist
		if (col == null) {

			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}

			String pathSegments[] = collectionUri.split("/");

			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();

				for (int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}

				Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

				if (startCol == null) {

					// child collection does not exist

					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user,
							conn.password);

					CollectionManagementService mgt = (CollectionManagementService) parentCol
							.getService("CollectionManagementService", "1.0");

					System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);

					col.close();
					parentCol.close();

				} else {
					startCol.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		} else {
			return col;
		}
	}

}
