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
import com.ftn.xml.dto.ResenjeFusekiDTO;
import com.ftn.xml.dto.ZahtevIzjasnjenjeCutanjeFusekiDTO;
import com.ftn.xml.dto.ZahtevZaIzjasnjenjeOdlukaFusekiDTO;
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
		
		String SPARQL_NAMED_GRAPH_URI = "/zahtevi";

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

}
