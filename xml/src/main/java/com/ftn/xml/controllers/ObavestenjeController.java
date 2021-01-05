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

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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
import static com.ftn.xml.jaxb.util.XUpdateTemplate.APPEND;

import com.ftn.xml.jaxb.obavestenje.ListaObavestenja;
import com.ftn.xml.jaxb.util.AuthenticationUtilities;
import com.ftn.xml.jaxb.util.AuthenticationUtilities.ConnectionProperties;
import com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist;
import com.ftn.xml.jaxb.util.MetadataExtractor;
import com.ftn.xml.jaxb.util.SparqlUtil;

@RestController
@RequestMapping("/obavestenje")
public class ObavestenjeController {
	
	private static final String SPARQL_NAMED_GRAPH_URI = "/obavestenja";
	private static com.ftn.xml.jaxb.util.AuthenticationUtilitiesExist.ConnectionProperties conn;

	@PostMapping("/initializeRDF")
	public ResponseEntity<HttpStatus> initializeRDFDatabase() throws IOException, SAXException, TransformerException {
		
		ConnectionProperties conn = AuthenticationUtilities.loadProperties();

		String xmlFilePath = "data/obavestenje.xml";

		String rdfFilePath = "gen/obavestenje.rdf";

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
	public ResponseEntity<HttpStatus> initializeExistDatabase() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, JAXBException {
		
		this.conn = AuthenticationUtilitiesExist.loadProperties();
		// initialize collection and document identifiers
        String collectionId = "/db/documents";
		String documentId = "obavestenje.xml"; 
		String filePath = "data/obavestenje.xml";
        	
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
            
            JAXBContext context = JAXBContext.newInstance("com.ftn.xml.jaxb.obavestenje");
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			ListaObavestenja listaObavestenja = (ListaObavestenja) unmarshaller.unmarshal(new File(filePath));
//			bookstore.getBook().add(createTestBook());
			
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			marshaller.marshal(listaObavestenja, os);
			
            res.setContent(os);
            
            col.storeResource(res);
            
        } finally {
            
        	//don't forget to cleanup
            if(res != null) {
                try { 
                	((EXistResource)res).freeResources(); 
                } catch (XMLDBException xe) {
                	xe.printStackTrace();
                }
            }
            
            if(col != null) {
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
    	String documentId = "obavestenje.xml";

        Class<?> cl = Class.forName(conn.driver);
        
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        
        DatabaseManager.registerDatabase(database);
        
        Collection col = null;
        
        try { 

    		String contextXPath = "/lista_obavestenja";
    		
    		String xmlFragment = "<obavestenje  vocab=\"http://www.ftn.uns.ac.rs/rdf/examples/predicate/\" about=\"http://www.ftn.uns.ac.rs/rdf/examples/resenje/77777\">"+
        			"<osnovni_podaci>"	+
        			"<podaci_o_organu>"	+
        			"<naziv property=\"pred:naziv_ustanove\" datatype=\"xs:string\">FTN</naziv>"	+
        			"<sediste property=\"pred:sediste_ustanove\" datatype=\"xs:string\">Novi Sad</sediste>"	+
        			"<broj_predmeta property=\"pred:br_predmeta\" datatype=\"xs:string\">77777</broj_predmeta>"+
        			"<datum_zahteva property=\"pred:datum_zahteva\" datatype=\"xs:string\">2020-12-24</datum_zahteva>"+
        			"</podaci_o_organu>"+
		            "<podaci_o_podnosiocu>"+
		            "<ime_i_prezime property=\"pred:ime_podnosioca\" datatype=\"xs:string\">Vladimir Vukovic</ime_i_prezime>"+
		            "<naziv property=\"pred:naziv_podnosioca\" datatype=\"xs:string\">Raska</naziv>"+
		            "<adresa property=\"pred:adresa_podnosioca\" datatype=\"xs:string\">Tomice Aleksic 25</adresa>"+
		            "</podaci_o_podnosiocu>"+
		            "</osnovni_podaci>"+
		            "<sadrzaj>"+
		            "<godina_zahteva property=\"pred:godina_zahteva\" datatype=\"xs:integer\">2020</godina_zahteva> "+
		            "<opis_trazene_informacije property=\"pred:opis\" datatype=\"xs:string\">Uvid u spisak polozenih ispita</opis_trazene_informacije>"+
		            "<datum_uvida>2020-12-04</datum_uvida>"+
		            "<cas_uvida>15:30:00</cas_uvida>"+
		            "<sat_od>14:26:30</sat_od>"+
		            "<sat_do>20:26:00</sat_do>"+
		            "<adresa>"+
	                "<grad>Novi Sad</grad>"+
	                "<ulica>Adresa fakulteta</ulica>"+
	                "<broj>23</broj>"+
		            "</adresa>"+
		            "<broj_kancelarije>12</broj_kancelarije>"+
		            "<cena>176.00</cena> "+
		            "<ziro_racun poziv_na_broj=\"97\">840-74232884311-30</ziro_racun> "+
			        "</sadrzaj>" +
			        "<podnozje>" +
			        "<potpis_ovlascenog_lica></potpis_ovlascenog_lica>" +
			        "</podnozje>" +
			        "</obavestenje>";
        	
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty("indent", "yes");
        	
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            
            xupdateService.updateResource(documentId, String.format(APPEND, contextXPath, xmlFragment));
            
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
        
        return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);
        
        // create the collection if it does not exist
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
                
                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);
                
                if (startCol == null) {
                	
                	// child collection does not exist
                    
                	String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);
                    
                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");
                    
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