import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }



  public obavestenjeSpecifikacija = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      }
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { //if element
          this.validate(jsChild); //recursion
        }
      }
    },

    elements: {
      "obavestenje": {
        hasText: false,
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        menu: [],
        attributes: {
          "vocab": {
            isInvisible: true,
          },
          "about": {
            isInvisible: true,
          }
        }
      },

      // OSNOVNI PODACI - PODACI O ORGANU

      "naziv":{
        isReadOnly:true
      },
      "sediste":{
        isReadOnly: true,
      },
      "broj_predmeta":{
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "datum_zahteva":{
        isReadOnly: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },

      // OSNOVNI PODACI - PODACI O PODNOSIOCU
      "ime_i_prezime":{
        hasText: true,
        oneliner: true,
        isReadOnly: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "naziv_podnosioca":{
        hasText: true,
        oneliner: true,
        isReadOnly: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "adresa_podnosioca":{
        hasText: true,
        oneliner: true,
        isReadOnly: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },

      // SADRZAJ
      "godina_zahteva":{
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "opis_trazene_informacije": {
        hasText: true,
        oneliner: false,
        isReadOnly: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "datum_uvida": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "cas_uvida": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "sat_od": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString
      },
      "sat_do": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "grad": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "ulica": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "broj": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "broj_kancelarije": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "cena": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
      },
      "ziro_racun": {
        validate: function (jsElement) {
          if (jsElement.getText() == "") {
            Xonomy.warnings.push({
                htmlID: jsElement.htmlID,
                text: "Овај елемент не сме бити празан."
              }
            );
          }
        },
        hasText: true,
        oneliner: false,
        asker: Xonomy.askString,
        attributes: {
          "poziv_na_broj": {
            isInvisible: true,
          }
        }
      },

      // PODNOZJE
      "podnozje": {
        isInvisible: true
      },
      "potpis_ovlascenog_lica": {
        isInvisible: true
      },

      "broj_zahteva": {
        isInvisible: true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      }
    }
  }
}
