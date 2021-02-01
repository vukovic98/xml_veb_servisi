package com.ftn.xml.db;

import java.io.ByteArrayOutputStream;

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
import org.springframework.stereotype.Service;

import com.ftn.xml.db.AuthenticationManagerFuseki.ConnectionProperties;
import com.ftn.xml.dto.ZahtevFusekiDTO;
import com.ftn.xml.jaxb.util.SparqlUtil;

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

	public void dodajZahtev(String id, ZahtevFusekiDTO dto) {
		
		String SPARQL_NAMED_GRAPH_URI = "/zahtevi";

		Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("pred", PREDICATE_NAMESPACE);

		// Making the changes manually
		Resource resource = model.createResource("http://www.ftn.uns.ac.rs/rdf/examples/zahtev/" + id);


		Property property1 = model.createProperty(PREDICATE_NAMESPACE, "broj_kuce_trazioca");
		Literal literal1 = model.createLiteral(dto.getBroj_kuce_trazioca()+"");

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
