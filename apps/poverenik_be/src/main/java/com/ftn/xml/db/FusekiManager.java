package com.ftn.xml.db;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.xml.db.AuthenticationManagerFuseki.ConnectionProperties;
import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.dto.ZahtevIzjasnjenjeCutanjeFusekiDTO;
import com.ftn.xml.dto.ZahtevZaIzjasnjenjeOdlukaFusekiDTO;

import com.ftn.xml.jaxb.util.FileUtil;

import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;
import com.ftn.xml.model.zalba_na_odluku.ZalbaNaOdluku;
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
	private static final String ZALBA_NA_ODLUKU_NAMED_GRAPH_URI = "/zalba_na_odluku";
	private static final String RESENJE_NAMED_GRAPH_URI = "/resenje";
	
	public FusekiManager() {
		try {
			this.conn = AuthenticationManagerFuseki.loadProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> query(String graphUri, String sparqlFilePath, List<String> queryParams) throws Exception {
		queryParams.add(0, conn.dataEndpoint + graphUri);

		// Querying the named graph with a referenced SPARQL query
		System.out.println("[INFO] Loading SPARQL query from file \"" + sparqlFilePath + "\"");
		String sparqlQuery = String.format(FileUtil.readFile(sparqlFilePath, StandardCharsets.UTF_8),
				queryParams.toArray());
		System.out.println(queryParams.toArray());

		System.out.println(sparqlQuery);

		// Create a QueryExecution that will access a SPARQL service over HTTP
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the SPARQL endpoint, iterate over the result set...
		System.out.println("[INFO] Showing the results for SPARQL query using the result handler.\n");
		ResultSet results = query.execSelect();

		String varName;
		RDFNode varValue;

		List<String> result = new ArrayList<String>();
		while (results.hasNext()) {

			// A single answer from a SELECT query
			QuerySolution querySolution = results.next();
			Iterator<String> variableBindings = querySolution.varNames();

			// Retrieve variable bindings
			while (variableBindings.hasNext()) {

				varName = variableBindings.next();
				varValue = querySolution.get(varName);

				result.add(varValue.toString().substring(varValue.toString().lastIndexOf("/") + 1));
			}
		}

		// Issuing the same query once again...

		// Create a QueryExecution that will access a SPARQL service over HTTP
		query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

		// Query the collection, dump output response as XML
		System.out.println("[INFO] Showing the results for SPARQL query in native SPARQL XML format.\n");
		results = query.execSelect();

		// ResultSetFormatter.outputAsXML(System.out, results);
		ResultSetFormatter.out(System.out, results);

		query.close();

		System.out.println("[INFO] End.");
		return result;
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
	
	public void dodajZahtevIzjasnjenjeCutanje(ZahtevIzjasnjenjeCutanjeFusekiDTO z) {
		
	}
	
	public void dodajZahtevIzjasnjenjeOdluka(ZahtevZaIzjasnjenjeOdlukaFusekiDTO z) {
		
	}
	
	public void dodajResenje(String id, ResenjeFusekiDTO dto) {
		
		String SPARQL_NAMED_GRAPH_URI = "/resenja";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/resenje/" + id);

		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "naslov");
		Literal literal1 = model.createLiteral(dto.getNaslov() + "");

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "datum");
		Literal literal2 = model.createLiteral(dto.getDatum());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "korisnik_email");
		Literal literal3 = model.createTypedLiteral(dto.getKorisnik_email());

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "organ");
		Literal literal4 = model.createLiteral(dto.getOrgan());

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "podnosilac");
		Literal literal5 = model.createLiteral(dto.getPodnosilac());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "ustanova");
		Literal literal6 = model.createLiteral(dto.getUstanova());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "datum_zahteva");
		Literal literal7 = model.createLiteral(dto.getDatum_zahteva());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "trazeni_dokument");
		Literal literal8 = model.createTypedLiteral(dto.getTrazeni_dokument());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "tekst_resenja");
		Literal literal9 = model.createLiteral(dto.getTekst_resenja());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "tekst_obrazlozenja");
		Literal literal10 = model.createLiteral(dto.getTekst_obrazlozenja());

		Property property11 = model.createProperty(PREDICATE_NAMESPACE, "sud");
		Literal literal11 = model.createLiteral(dto.getSud());
		
		Property property12 = model.createProperty(PREDICATE_NAMESPACE, "taksa");
		Literal literal12 = model.createTypedLiteral(dto.getTaksa());
		
		Property property13 = model.createProperty(PREDICATE_NAMESPACE, "poverenik");
		Literal literal13 = model.createLiteral(dto.getPoverenik());

		Property property14 = model.createProperty(PREDICATE_NAMESPACE, "ishod");
		Literal literal14 = model.createLiteral(dto.getIshod());
		
		Property property15 = model.createProperty(PREDICATE_NAMESPACE, "broj");
		Literal literal15 = model.createLiteral(dto.getBroj());

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
		Statement statement13 = model.createStatement(resource, property13, literal13);
		Statement statement14 = model.createStatement(resource, property14, literal14);
		Statement statement15 = model.createStatement(resource, property15, literal15);

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
		String sparqlUpdate = com.ftn.xml.db.SparqlUtil.deleteNode(conn.dataEndpoint + ZALBA_CUTANJE_NAMED_GRAPH_URI, sparqlCondition);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}
	
	//za zalbu cutanje
	public void generisiJSON(long id) throws FileNotFoundException {
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + 
				ZALBA_CUTANJE_NAMED_GRAPH_URI, "<http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/" + id + "> ?p ?o");
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String filePath = "src/main/resources/static/json/zalba_cutanje_" + id + ".json";
		File rdfFile = new File(filePath);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(rdfFile));
		ResultSetFormatter.outputAsJSON(out, results);
		query.close();
		
	}
	
	public void generisiJSONZalbaNaOdluku(long id) throws FileNotFoundException {
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + ZALBA_NA_ODLUKU_NAMED_GRAPH_URI, "<http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/" + id + "> ?p ?o");
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String filePath = "src/main/resources/static/json/zalba_na_odluku_" + id + ".json";
		File rdfFile = new File(filePath);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(rdfFile));
		ResultSetFormatter.outputAsJSON(out, results);
		query.close();
		
	}
	
	public void dodajZalbuNaOdluku(ZalbaNaOdluku zalba) {
		
		String SPARQL_NAMED_GRAPH_URI = "/zalba_na_odluku";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource(zalba.getAbout());


		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "korisnik");
		Literal literal1 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getKorisnikEmail().getContent());

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "ime_zalioca");
		Literal literal2 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getZaliocIme().getValue());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "prezime_zalioca");
		Literal literal3 = model.createTypedLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getZaliocPrezime().getValue());
		
		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "naziv_zalbe");
		Literal literal5 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getZaliocNazivZalbe().getValue());
		
		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "adresa_zalioca");
		Literal literal4 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getZaliocAdresa().getContent());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "sediste_zalioca");
		Literal literal6 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOZaliocu().getZaliocSediste().getContent());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "naziv_organa");
		Literal literal7 = model.createLiteral(zalba.getOsnovniPodaci().getPodaciOOrganu().getNaziv().getValue());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "broj_zalbe");
		Literal literal8 = model.createTypedLiteral(zalba.getSadrzaj().getBrojZalbe().getValue());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "godina_zalbe");
		Literal literal9 = model.createLiteral(zalba.getSadrzaj().getGodinaZalbe().getValue().toString());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "datum_odbijenog_zahteva");
		Literal literal10 = model.createLiteral(zalba.getSadrzaj().getDatumOdbijenogZahteva().getValue().toString());
		
		Property property11 = model.createProperty(PREDICATE_NAMESPACE, "odluka");
		Literal literal11 = model.createLiteral(zalba.getSadrzaj().getOdlukaOrganaVlasti().getContent());
		
		Property property12 = model.createProperty(PREDICATE_NAMESPACE, "mesto_zakljucka_zalbe");
		Literal literal12 = model.createLiteral(zalba.getPodnozje().getMestoZakljuckaZalbe().getContent());
		
		Property property13 = model.createProperty(PREDICATE_NAMESPACE, "datum_zakljucka_zalbe");
		Literal literal13 = model.createLiteral(zalba.getPodnozje().getDatumZakljuckaZalbe().getValue().toString());
		
		Property property14 = model.createProperty(PREDICATE_NAMESPACE, "ime_zalioca");
		Literal literal14 = model.createLiteral(zalba.getPodnozje().getPodaciOZaliocu().getZaliocIme().getValue());
		
		Property property15 = model.createProperty(PREDICATE_NAMESPACE, "prezime_zalioca");
		Literal literal15 = model.createLiteral(zalba.getPodnozje().getPodaciOZaliocu().getZaliocPrezime().getValue());
		
		Property property16 = model.createProperty(PREDICATE_NAMESPACE, "adresa_zalioca");
		Literal literal16 = model.createLiteral(zalba.getPodnozje().getPodaciOZaliocu().getZaliocAdresa().getContent());
		
		Property property17 = model.createProperty(PREDICATE_NAMESPACE, "kontakt_zalioca");
		Literal literal17 = model.createLiteral(zalba.getPodnozje().getPodaciOZaliocu().getDrugiPodaciZaKontakt().getContent());
		
		Property property18 = model.createProperty(PREDICATE_NAMESPACE, "ime_zalioca");
		Literal literal18 = model.createLiteral(zalba.getPodnozje().getPodaciOZaliocu().getZaliocIme().getValue());
		
		Property property19 = model.createProperty(PREDICATE_NAMESPACE, "broj_zahteva");
		Literal literal19 = model.createLiteral(zalba.getBrojZahteva().getValue().toString());
		
		
		
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
		Statement statement13 = model.createStatement(resource, property13, literal13);
		Statement statement14 = model.createStatement(resource, property14, literal14);
		Statement statement15 = model.createStatement(resource, property15, literal15);
		Statement statement16 = model.createStatement(resource, property16, literal16);
		Statement statement17 = model.createStatement(resource, property17, literal17);
		Statement statement18 = model.createStatement(resource, property18, literal18);
		Statement statement19 = model.createStatement(resource, property19, literal19);
		


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
		model.add(statement17);
		model.add(statement18);
		model.add(statement19);
		
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
		
	}
	
	public void obrisiZalbuNaOdluku(long id) {

		String ZALBA_NA_ODLUKU_NAMED_GRAPH_URI = "/zalba_na_odluku";
		String sparqlCondition = "http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/" + id;
		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		String sparqlUpdate = com.ftn.xml.db.SparqlUtil.deleteNode(conn.dataEndpoint + ZALBA_NA_ODLUKU_NAMED_GRAPH_URI, sparqlCondition);

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}
	
	
	public void generisiJSONResenje(long id) throws FileNotFoundException {
		String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + 
				RESENJE_NAMED_GRAPH_URI, "<http://www.ftn.uns.ac.rs/rdf/examples/resenje/" + id + "> ?p ?o");
		QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
		ResultSet results = query.execSelect();
		String filePath = "src/main/resources/static/json/resenje_" + id + ".json";
		File rdfFile = new File(filePath);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(rdfFile));
		ResultSetFormatter.outputAsJSON(out, results);
		query.close();
		
	}
	
	
}
