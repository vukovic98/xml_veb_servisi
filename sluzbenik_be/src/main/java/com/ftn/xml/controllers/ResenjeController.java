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
import com.ftn.xml.jaxb.util.AuthenticationUtilities.ConnectionProperties;
import com.ftn.xml.model.resenje.ListaResenja;
import com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist;
import com.ftn.xml.jaxb.util.MetadataExtractor;
import com.ftn.xml.jaxb.util.SparqlUtil;
import com.ftn.xml.jaxb.util.XUpdateTemplateResenje;

@RestController
@RequestMapping("/resenje")
public class ResenjeController {

	private static final String SPARQL_NAMED_GRAPH_URI = "/resenja";
	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";

	private static com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist.ConnectionProperties conn;

	@PostMapping("/initializeRDF")
	public ResponseEntity<HttpStatus> initializeRDFDatabase() throws IOException, SAXException, TransformerException {

		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String xmlFilePath = "data/resenje.xml";

		String rdfFilePath = "gen/resenje.rdf";

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
		String documentId = "resenje.xml";
		String filePath = "data/resenje.xml";

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

			JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.resenje");

			Unmarshaller unmarshaller = context.createUnmarshaller();

			ListaResenja listaResenja = (ListaResenja) unmarshaller.unmarshal(new File(filePath));

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			marshaller.marshal(listaResenja, os);

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
	public ResponseEntity<HttpStatus> addEntity() throws Exception {
		this.conn = AuthenticationUtilitiesExist.loadProperties();

		String collectionId = "/db/documents";
		String documentId = "resenje.xml";

		Class<?> cl = Class.forName(conn.driver);

		Database database = (Database) cl.newInstance();
		database.setProperty("create-database", "true");

		DatabaseManager.registerDatabase(database);

		Collection col = null;

		try {

			String contextXPath = "/lista_resenja";

			String xmlFragment = "<resenje broj=\"071-01-1114/2020-11\" vocab=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" about=\"http://www.ftn.uns.ac.rs/rdf/examples/resenje/071-01-1114/2020-11\">" +
					"<osnovni_podaci>" +
					"<naslov property=\"pred:naslov\" datatype=\"xs:string\">Решење када је жалба основана</naslov>" +
					"<datum property=\"pred:datum_resenja\" datatype=\"xs:date\">2020-06-09</datum>" +
					"</osnovni_podaci>" +
					"<sadrzaj>" +
					"<uvod>" +
					"<organ property=\"pred:organ\" datatype=\"xs:string\">Повереник за информације од јавног значаја и заштиту података о личности</organ>" +
					"<podnosilac property=\"pred:podnosilac\" datatype=\"xs:string\">AA</podnosilac>" +
					"<ustanova>" +
					"<naziv property=\"pred:naziv_ustanove\" datatype=\"xs:string\">Учитељског факултета у Призрену са привременим седиштем у Лепосавићу</naziv>" +
					"<ulica>Немањина бб</ulica>" +
					"</ustanova>" +
					"<datum_zahteva property=\"pred:datum_zahteva\" datatype=\"xs:date\">2020-04-16</datum_zahteva>" +
					"</uvod>" +
					"<doneto_resenje>" +
					"<ustanova>" +
					"<naziv>Учитељском факултету у Призрену са привременим седиштем у Лепосавићу</naziv>" +
					"</ustanova>" +
					"<podnosilac>АА</podnosilac>" +
					"<trazeni_dokument property=\"pred:trazeni_dokument\" datatype=\"xs:string\">Уговор о раду који је као последњи потписан између тог Факултета и ББ</trazeni_dokument>" +
					"<adresa>Tomice Aleksic 25</adresa>" +
					"</doneto_resenje>" +
					"<obrazlozenje>" +
					"<sud>Управном суду у Београду</sud>" +
					"<taksa property=\"pred:taksa\" datatype=\"xs:double\">390.00</taksa>" +
					"</obrazlozenje>" +
					"</sadrzaj>" +
					"<poverenik property=\"pred:poverenik\" datatype=\"xs:string\">Милан Мариновић</poverenik>" +
					"</resenje>";

			col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
			col.setProperty("indent", "yes");

			XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			xupdateService.setProperty("indent", "yes");

			xupdateService.updateResource(documentId, String.format(XUpdateTemplateResenje.APPEND, contextXPath, xmlFragment));

		} finally {

			// don't forget to cleanup
			if (col != null) {
				try {
					col.close();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}

		// ADD TO RDF DATABASE

		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/resenje/071-01-1114/2020-11");

		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "naslov");
		Literal literal1 = model.createLiteral("Решење када је жалба основана");

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "datum_resenja");
		Literal literal2 = model.createLiteral("2020-06-09");

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "organ");
		Literal literal3 = model
				.createLiteral("Повереник за информације од јавног значаја и заштиту података о личности");

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "podnosilac");
		Literal literal4 = model.createLiteral("AA");

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "naziv_ustanove");
		Literal literal5 = model.createLiteral("Учитељског факултета у Призрену са привременим седиштем у Лепосавићу");

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "datum_zahteva");
		Literal literal6 = model.createLiteral("2020-04-16");

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "trazeni_dokument");
		Literal literal7 = model.createLiteral("Уговор о раду који је као последњи потписан између тог Факултета и ББ");

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "taksa");
		Literal literal8 = model.createLiteral("390.00");

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "poverenik");
		Literal literal9 = model.createLiteral("Милан Мариновић");

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

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

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
