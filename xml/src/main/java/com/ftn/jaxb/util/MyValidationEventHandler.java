package com.ftn.jaxb.util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

/**
 * Implementira metodu koja handluje greÅ¡ke nastale prilikom validacije u odnosu
 * na XML schemu tokom unmarshallovanja.
 * 
 */
public class MyValidationEventHandler implements ValidationEventHandler {

	public boolean handleEvent(ValidationEvent event) {

		// Ako nije u pitanju WARNING metoda vraÄ‡a false
		if (event.getSeverity() != ValidationEvent.WARNING) {
			ValidationEventLocator validationEventLocator = event.getLocator();
			System.out.println("ERROR: Line "
					+ validationEventLocator.getLineNumber() + ": Col"
					+ validationEventLocator.getColumnNumber() + ": "
					+ event.getMessage());

			// Dalje izvrÅ¡avanje se prekida
			return false;
		} else {
			ValidationEventLocator validationEventLocator = event.getLocator();
			System.out.println("WARNING: Line "
					+ validationEventLocator.getLineNumber() + ": Col"
					+ validationEventLocator.getColumnNumber() + ": "
					+ event.getMessage());

			// Nastavlja se dalje izvrÅ¡avanje
			return true;
		}
	}

}
