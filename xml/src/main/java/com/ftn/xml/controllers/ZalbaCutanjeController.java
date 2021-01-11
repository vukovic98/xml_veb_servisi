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

import com.ftn.xml.jaxb.util.AuthenticationUtilities;
import com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist;
import com.ftn.xml.jaxb.util.MetadataExtractor;
import com.ftn.xml.jaxb.util.SparqlUtil;
import com.ftn.xml.jaxb.util.XUpdateTemplateZahtev;
import com.ftn.xml.jaxb.util.XUpdateTemplateZalbaCutanje;
import com.ftn.xml.jaxb.util.AuthenticationUtilities.ConnectionProperties;
import com.ftn.xml.jaxb.zahtev.ListaZahtevaZaPristupInformacijama;
import com.ftn.xml.jaxb.zalba_cutanje.ListaZalbiCutanje;

@RestController
@RequestMapping("/zalba_cutanje")
public class ZalbaCutanjeController {

	private static final String SPARQL_NAMED_GRAPH_URI = "/zalbe_cutanje";
	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";

	private static com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist.ConnectionProperties conn;

	@PostMapping("/initializeRDF")
	public ResponseEntity<HttpStatus> initializeRDFDatabase() throws IOException, SAXException, TransformerException {

		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String xmlFilePath = "data/zalba_cutanje.xml";

		String rdfFilePath = "gen/zalba_cutanje.rdf";

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
		String documentId = "zalba_cutanje.xml";
		String filePath = "data/zalba_cutanje.xml";

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

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.zalba_cutanje");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			ListaZalbiCutanje listaZahteva = (ListaZalbiCutanje) unmarshaller
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
    	String documentId = "zalba_cutanje.xml";

        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        
        try { 

    		String contextXPath = "/lista_zalbi_cutanje";
    		
    		String xmlFragment ="<zalba_cutanje\r\n" + 
    				"    vocab=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" \r\n" + 
    				"    about=\"http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/30\">\r\n" + 
    				"    <zaglavlje>\r\n" + 
    				"    <primalac_zalbe>\r\n" + 
    				"    <naziv_primaoca property=\"pred:naziv_primaoca\" datatype=\"xs:string\">            \r\n" + 
    				"    Повереникy за информације од јавног значаја и заштиту података о личности\r\n" + 
    				"    </naziv_primaoca>\r\n" + 
    				"    <adresa>\r\n" + 
    				"    <ulica property=\"pred:ulica_poverenika\" datatype=\"xs:string\">Булевар деспота Стефана</ulica>\r\n" + 
    				"    <broj property=\"pred:broj_kuce_poverenika\" datatype=\"xs:integer\">15</broj>\r\n" + 
    				"    <mesto property=\"pred:mesto_poverenika\" datatype=\"xs:string\">Нови Сад</mesto>\r\n" + 
    				"    </adresa>\r\n" + 
    				"    </primalac_zalbe>\r\n" + 
    				"    </zaglavlje>\r\n" + 
    				"    <sadrzaj>\r\n" + 
    				"    <naziv_organa property=\"pred:naziv_organa\" datatype=\"xs:string\">Универзитет у Новом Саду</naziv_organa>\r\n" + 
    				"    <razlozi_zalbe>\r\n" + 
    				"    <razlog otkaceno=\"false\" id=\"r_1\" razlog=\"nije_postupio\"></razlog>\r\n" + 
    				"    <razlog otkaceno=\"true\" id=\"r_2\" razlog=\"nije_postupio_u_celosti\"></razlog>\r\n" + 
    				"    <razlog otkaceno=\"false\" id=\"r_3\" razlog=\"nije_postupio_u_zakonskom_roku\"></razlog>  \r\n" + 
    				"    </razlozi_zalbe>\r\n" + 
    				"    <datum_zahteva>2020-12-25</datum_zahteva>\r\n" + 
    				"    <podaci_o_zahtevu_i_informacijama>Тражени захтев односио се на увид у информације о буџету.</podaci_o_zahtevu_i_informacijama>\r\n" + 
    				"    </sadrzaj>\r\n" + 
    				"    <podnozje>\r\n" + 
    				"    <podnosilac_zalbe>\r\n" + 
    				"    <ime_i_prezime property=\"pred:ime_prezime_podnosioca\" datatype=\"xs:string\">\r\n" + 
    				"    Марија Матић \r\n" + 
    				"    </ime_i_prezime>\r\n" + 
    				"    <potpis></potpis>\r\n" + 
    				"    <adresa>\r\n" + 
    				"    <ulica property=\"pred:ulica_podnosioca\" datatype=\"xs:string\">Хаџи Рувимова</ulica>\r\n" + 
    				"    <broj property=\"pred:broj_kuce_podnosioca\" datatype=\"xs:integer\">15</broj>\r\n" + 
    				"    <mesto property=\"pred:mesto_podnosioca\" datatype=\"xs:string\">Нови Сад</mesto>\r\n" + 
    				"    </adresa>\r\n" + 
    				"    <drugi_podaci_za_kontakt>\r\n" + 
    				"    Мобилни телефон: 0605123456\r\n" + 
    				"    </drugi_podaci_za_kontakt>\r\n" + 
    				"    <potpis></potpis>\r\n" + 
    				"    </podnosilac_zalbe>\r\n" + 
    				"    <mesto_i_datum>\r\n" + 
    				"    <mesto>Нови Сад</mesto>\r\n" + 
    				"    <datum_zalbe property=\"pred:datum_zalbe\" datatype=\"xs:string\">2021-01-05</datum_zalbe> \r\n" + 
    				"    </mesto_i_datum>\r\n" + 
    				"    </podnozje>\r\n" + 
    				"</zalba_cutanje>" ;
        	
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty("indent", "yes");
        	
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            
            xupdateService.updateResource(documentId, String.format(XUpdateTemplateZalbaCutanje.APPEND, contextXPath, xmlFragment));
            
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
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/30");
		
		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "naziv_primaoca");
		Literal literal1 = model.createLiteral("Повереникy за информације од јавног значаја и заштиту података о личности");
		
		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "ulica_poverenika");
		Literal literal2 = model.createLiteral("Булевар деспота Стефана");
		
		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_poverenika");
		Literal literal3 = model.createTypedLiteral(15);
		
		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "mesto_poverenika");
		Literal literal4 = model.createLiteral("Нови Сад");
		
		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "naziv_organa");
		Literal literal5 = model.createLiteral("Универзитет у Новом Саду");
		
		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "ime_prezime_podnosioca");
		Literal literal6 = model.createLiteral("Марија Матић");
		
		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "ulica_podnosioca");
		Literal literal7 = model.createLiteral("Хаџи Рувимова");
		
		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_podnosioca");
		Literal literal8 = model.createTypedLiteral(15);
		
		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "mesto_podnosioca");
		Literal literal9 = model.createLiteral("Нови Сад");
		
		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "datum_zalbe");
		Literal literal10 = model.createLiteral("2021-01-05");
		
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
		Statement statement10 = model.createStatement(resource, property10, literal10);
		
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
