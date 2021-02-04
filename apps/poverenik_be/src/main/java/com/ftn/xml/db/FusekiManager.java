package com.ftn.xml.db;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;

import com.ftn.xml.db.AuthenticationManagerFuseki.ConnectionProperties;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.repository.ZalbaCutanjeRepository;
import com.ftn.xml.service.ZalbaCutanjeService;

@Service
public class FusekiManager {

	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";
	
	private ConnectionProperties conn;

	@Autowired
	ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	
	private ZalbaCutanjeService zalbaCutanjeService;
	
	//private MetadataExtractor metadataExtractor;
	
	private static final String ZALBA_CUTANJE_NAMED_GRAPH_URI = "/zalba_cutanje";
	
	public FusekiManager() {
		try {
			this.conn = AuthenticationManagerFuseki.loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dodajZalbuCutanje(ZalbaCutanje zalba) {
		
		String SPARQL_NAMED_GRAPH_URI = "/zalba_cutanje";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource(zalba.getAbout());


		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "naziv_primaoca");
		Literal literal1 = model.createLiteral(zalba.getZaglavlje().getPrimalacZalbe().getNazivPrimaoca().getContent());

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "ulica_poverenika");
		Literal literal2 = model.createLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getUlica().getContent());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_poverenika");
		Literal literal3 = model.createTypedLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getBroj().getValue()+"");

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "mesto_poverenika");
		Literal literal4 = model.createLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getMesto().getContent());

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "naziv_organa");
		Literal literal5 = model.createLiteral(zalba.getSadrzaj().getNazivOrgana().getValue());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "korisnik");
		Literal literal6 = model.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getKorisnikEmail().getContent());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "ime_prezime_podnosioca");
		Literal literal7 = model.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "ulica_podnosioca");
		Literal literal8 = model.createTypedLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getUlica().getContent());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_podnosioca");
		Literal literal9 = model.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getBroj().getValue().toString());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "mesto_podnosioca");
		Literal literal10 = model.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getMesto().getContent());
		
		Property property11 = model.createProperty(PREDICATE_NAMESPACE, "datum_zalbe");
		Literal literal11 = model.createLiteral(zalba.getPodnozje().getMestoIDatum().getDatumZalbe().getValue());
		
		Property property12 = model.createProperty(PREDICATE_NAMESPACE, "broj_zahteva");
		Literal literal12 = model.createLiteral(zalba.getBrojZahteva().getValue().toString());
		
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
		Statement statement11 = model.createStatement(resource, property11, literal11);
		Statement statement12 = model.createStatement(resource, property12, literal12);


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
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}

	public void obrisiZalbuCutanje(long id) {
//		DELETE WHERE {
//			  GRAPH <http://localhost:8083/fuseki/PoverenikDataset/data/zalba_cutanje> {
//			    <http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/11> ?p ?o 
//			  }
//			 }
		String ZALBA_CUTANJE_NAMED_GRAPH_URI = "/zalba_cutanje";
		String sparqlCondition = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/" + id;
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		

		//String sparqlUpdate = SparqlUtil.dropGraph(conn.dataEndpoint + ZALBA_CUTANJE_NAMED_GRAPH_URI);
		String sparqlUpdate = SparqlUtil.deleteNode(conn.dataEndpoint + ZALBA_CUTANJE_NAMED_GRAPH_URI, sparqlCondition);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}
	
	public void generisiJSON(long id) throws FileNotFoundException {
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + ZALBA_CUTANJE_NAMED_GRAPH_URI, "<http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/" + id + "> ?p ?o");
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String filePath = "src/main/resources/static/json/zalba_cutanje_" + id + ".json";
		File rdfFile = new File(filePath);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(rdfFile));
		ResultSetFormatter.outputAsJSON(out, results);
		query.close();
		
	}
	

	
	
}
