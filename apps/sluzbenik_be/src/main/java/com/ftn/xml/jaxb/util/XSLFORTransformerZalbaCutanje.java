package com.ftn.xml.jaxb.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

public class XSLFORTransformerZalbaCutanje {

	private FopFactory fopFactory;

	private TransformerFactory transformerFactory;

	private DocumentBuilderFactory documentFactory;

	public static final String XSL_FILE = "src/main/resources/static/xsl/zalba_cutanje_fo.xsl";
	public static final String XHTML_FILE = "src/main/resources/static/xsl/zalba_cutanje.xsl";

	public XSLFORTransformerZalbaCutanje() throws SAXException, IOException {
		fopFactory = FopFactory.newInstance(new File("src/main/java/fop.xconf"));
		transformerFactory = new TransformerFactoryImpl();

		documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringComments(true);
		documentFactory.setIgnoringElementContentWhitespace(true);

		transformerFactory = TransformerFactory.newInstance();
		fopFactory = FopFactory.newInstance(new File("src/main/java/fop.xconf"));
	}

	public boolean generatePDF(String xml, String OUTPUT_FILE) {

		try {
			File xslFile = new File(XSL_FILE);

			StreamSource transformSource = new StreamSource(xslFile);

			StringReader r = new StringReader(xml);

			StreamSource source = new StreamSource(r);

			FOUserAgent userAgent = fopFactory.newFOUserAgent();

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();

			Transformer xslFoTransformer = transformerFactory.newTransformer(transformSource);

			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

			Result res = new SAXResult(fop.getDefaultHandler());

			xslFoTransformer.transform(source, res);

			File pdfFile = new File(OUTPUT_FILE);

			if (!pdfFile.getParentFile().exists()) {
				pdfFile.getParentFile().mkdir();
			}

			OutputStream out = new BufferedOutputStream(new FileOutputStream(pdfFile));
			out.write(outStream.toByteArray());

			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean generateHTML(String xmlPath, String OUTPUT_FILE) {
		try {
			StreamSource transformSource = new StreamSource(new File(XHTML_FILE));
			Transformer transformer = transformerFactory.newTransformer(transformSource);
			transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

			DOMSource source = new DOMSource(buildDocument(xmlPath));
			StreamResult result = new StreamResult(new FileOutputStream(OUTPUT_FILE));
			transformer.transform(source, result);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Document buildDocument(String filePath) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(new InputSource(new StringReader(filePath)));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
