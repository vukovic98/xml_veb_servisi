package com.ftn.xml.db;

import java.io.File;

import javax.xml.transform.OutputKeys;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XUpdateQueryService;

@Component
public class ExistManager {

	@Autowired
	private AuthenticationManager authManager;

	public void createConnection() throws Exception {
		Class<?> cl = Class.forName(this.authManager.getDriver());

		Database db = (Database) cl.newInstance();

		db.setProperty("create-database", "true");
		DatabaseManager.registerDatabase(db);
	}

	public void closeConnection(Collection col, XMLResource res) throws XMLDBException {
		if (col != null) {
			col.close();
		}

		if (res != null) {
			((EXistResource) res).freeResources();
		}
	}

	public Collection getOrCreateCollection(String collectionUri, int pathOffset) throws XMLDBException {
		Collection col = DatabaseManager.getCollection(this.authManager.getUri() + collectionUri,
				this.authManager.getUser(), this.authManager.getPassword());

		if (col == null) {
			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}

			String pathSegments[] = collectionUri.split("/");

			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();

				for (int i = 0; i <= pathOffset; i++) {
					path.append("/" + pathSegments[i]);
				}

				Collection startCol = DatabaseManager.getCollection(this.authManager.getUri() + path,
						this.authManager.getUser(), this.authManager.getPassword());

				if (startCol == null) {
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parentCol = DatabaseManager.getCollection(this.authManager.getUri() + parentPath,
							this.authManager.getUser(), this.authManager.getPassword());

					CollectionManagementService service = (CollectionManagementService) parentCol
							.getService("CollectionManagementService", "1.0");

					col = service.createCollection(pathSegments[pathOffset]);

					col.close();
					parentCol.close();
				} else {
					startCol.close();
				}
			}

			return getOrCreateCollection(collectionUri, ++pathOffset);
		} else {
			return col;
		}
	}

	// store file
	public void store(String collectionId, String documentId, String filePath) throws Exception {
		createConnection();

		Collection col = null;
		XMLResource res = null;

		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);
			File f = new File(filePath);

			if (!f.canRead()) {
				return;
			} else {
				res.setContent(f);
				col.storeResource(res);
			}
		} finally {
			closeConnection(col, res);
		}
	}

	// store from string
	public void storeFromText(String collectionId, String documentId, String xmlString) throws Exception {
		createConnection();

		Collection col = null;
		XMLResource res = null;

		try {
			col = getOrCreateCollection(collectionId, 0);
			res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

			res.setContent(xmlString);
			col.storeResource(res);

		} finally {
			closeConnection(col, res);
		}
	}


	// load whole document
	public XMLResource load(String collectionId, String documentId) throws Exception {
		createConnection();

		Collection col = null;
		XMLResource res = null;

		try {
			col = DatabaseManager.getCollection(this.authManager.getUri() + collectionId, this.authManager.getUser(),
					this.authManager.getPassword());
			col.setProperty(OutputKeys.INDENT, "yes");
			res = (XMLResource) col.getResource(documentId);

			return res;
		} finally {
			if (col != null)
				col.close();
		}
	}

	// executing xquery on collection
	public ResourceSet retrieve(String collectionId, String xpathExp, String TARGET_NAMESPACE) throws Exception {
		createConnection();

		Collection col = null;
		ResourceSet res = null;

		try {
			col = DatabaseManager.getCollection(this.authManager.getUri() + collectionId, this.authManager.getUser(),
					this.authManager.getPassword());
			XPathQueryService xPathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
			xPathService.setProperty("indent", "yes");
			xPathService.setNamespace("", TARGET_NAMESPACE);

			res = xPathService.query(xpathExp);
		} finally {
			if (col != null)
				col.close();
		}

		return res;
	}

	public void update(String collectionId, String documentId, String contextXPath, String patch, String UPDATE)
			throws Exception {
		createConnection();
		Collection col = null;
		String chosenTemplate = UPDATE;

		try {
			col = DatabaseManager.getCollection(this.authManager.getUri() + collectionId, this.authManager.getUser(),
					this.authManager.getPassword());
			XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			service.setProperty("indent", "yes");

			service.updateResource(documentId, String.format(chosenTemplate, contextXPath, patch));
		} finally {
			if (col != null)
				col.close();
		}
	}

	public void append(String collectionId, String documentId, String contextXPath, String patch, String APPEND)
			throws Exception {
		createConnection();
		Collection col = null;
		String chosenTemplate = APPEND;

		try {
			col = DatabaseManager.getCollection(this.authManager.getUri() + collectionId, this.authManager.getUser(),
					this.authManager.getPassword());
			XUpdateQueryService service = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
			service.setProperty("indent", "yes");

			service.updateResource(documentId, String.format(chosenTemplate, contextXPath, patch));
		} finally {
			if (col != null)
				col.close();
		}
	}
}
