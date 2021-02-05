package com.ftn.xml.db;

import java.io.ByteArrayOutputStream;
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
import org.springframework.stereotype.Service;

import com.ftn.xml.db.AuthenticationManagerFuseki.ConnectionProperties;
import com.ftn.xml.dto.ObavestenjeFusekiDTO;
import com.ftn.xml.dto.ZahtevFusekiDTO;
import com.ftn.xml.jaxb.util.FileUtil;
import com.ftn.xml.jaxb.util.SparqlUtil;
import com.ftn.xml.model.zalba_cutanje.ZalbaCutanje;


@Service
public class FusekiManager {

	private static final String PREDICATE_NAMESPACE = "http://www.ftn.uns.ac.rs/rdf/examples/predicate/";

	private ConnectionProperties conn;

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
		Literal literal2 = model
				.createLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getUlica().getContent());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_poverenika");
		Literal literal3 = model
				.createTypedLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getBroj().getValue() + "");

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "mesto_poverenika");
		Literal literal4 = model
				.createLiteral(zalba.getZaglavlje().getPrimalacZalbe().getAdresa().getMesto().getContent());

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "naziv_organa");
		Literal literal5 = model.createLiteral(zalba.getSadrzaj().getNazivOrgana().getValue());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "korisnik");
		Literal literal6 = model
				.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getKorisnikEmail().getContent());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "ime_prezime_podnosioca");
		Literal literal7 = model.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getImeIPrezime().getContent());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "ulica_podnosioca");
		Literal literal8 = model
				.createTypedLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getUlica().getContent());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_podnosioca");
		Literal literal9 = model
				.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getBroj().getValue().toString());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "mesto_podnosioca");
		Literal literal10 = model
				.createLiteral(zalba.getPodnozje().getPodnosilacZalbe().getAdresa().getMesto().getContent());

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

	public void dodajObavestenje(String id, ObavestenjeFusekiDTO dto) {

		String SPARQL_NAMED_GRAPH_URI = "/obavestenja";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/obavestenje/" + id);

		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "adresa_podnosioca");
		Literal literal1 = model.createLiteral(dto.getAdresa_podnosioca());

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "br_predmeta");
		Literal literal2 = model.createLiteral(dto.getBr_predmeta());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "broj_zahteva");
		Literal literal3 = model.createTypedLiteral(dto.getBroj_zahteva());

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "datum_zahteva");
		Literal literal4 = model.createLiteral(dto.getDatum_zahteva());

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "godina_zahteva");
		Literal literal5 = model.createLiteral(dto.getGodina_zahteva());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "ime_podnosioca");
		Literal literal6 = model.createLiteral(dto.getIme_podnosioca());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "naziv_podnosioca");
		Literal literal7 = model.createLiteral(dto.getNaziv_podnosioca());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "naziv_ustanove");
		Literal literal8 = model.createTypedLiteral(dto.getNaziv_ustanove());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "opis");
		Literal literal9 = model.createLiteral(dto.getOpis());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "sediste_ustanove");
		Literal literal10 = model.createLiteral(dto.getSediste_ustanove());

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

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}

	public void dodajZahtev(String id, ZahtevFusekiDTO dto) {

		String SPARQL_NAMED_GRAPH_URI = "/zahtevi";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/zahtev/" + id);

		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_trazioca");
		Literal literal1 = model.createLiteral(dto.getBroj_kuce_trazioca() + "");

		Property property2 = model.createProperty(PREDICATE_NAMESPACE, "datum_zahteva");
		Literal literal2 = model.createLiteral(dto.getDatum_zahteva());

		Property property3 = model.createProperty(PREDICATE_NAMESPACE, "ime_trazioca");
		Literal literal3 = model.createTypedLiteral(dto.getIme_trazioca());

		Property property4 = model.createProperty(PREDICATE_NAMESPACE, "kontakt_trazioca");
		Literal literal4 = model.createLiteral(dto.getKontakt_trazioca());

		Property property5 = model.createProperty(PREDICATE_NAMESPACE, "korisnik");
		Literal literal5 = model.createLiteral(dto.getKorisnik());

		Property property6 = model.createProperty(PREDICATE_NAMESPACE, "mesto_trazioca");
		Literal literal6 = model.createLiteral(dto.getMesto_trazioca());

		Property property7 = model.createProperty(PREDICATE_NAMESPACE, "mesto_zahteva");
		Literal literal7 = model.createLiteral(dto.getMesto_zahteva());

		Property property8 = model.createProperty(PREDICATE_NAMESPACE, "naziv_ustanove");
		Literal literal8 = model.createTypedLiteral(dto.getNaziv_ustanove());

		Property property9 = model.createProperty(PREDICATE_NAMESPACE, "opis_informacije");
		Literal literal9 = model.createLiteral(dto.getOpis_informacije());

		Property property10 = model.createProperty(PREDICATE_NAMESPACE, "sediste_ustanove");
		Literal literal10 = model.createLiteral(dto.getSediste_ustanove());

		Property property11 = model.createProperty(PREDICATE_NAMESPACE, "ulica_trazioca");
		Literal literal11 = model.createLiteral(dto.getUlica_trazioca());

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

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		model.write(out, SparqlUtil.NTRIPLES);

		String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI,
				new String(out.toByteArray()));

		UpdateRequest update = UpdateFactory.create(sparqlUpdate);

		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
		processor.execute();

		model.close();
	}

}
