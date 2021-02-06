import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public zalbaCutanjeSpecification = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      };
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { //if element
          this.validate(jsChild); //recursion
        }
      }
    },
    elements: {
      "zalba_cutanje": {
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
      }
      ,
      "naziv_primaoca":{
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
        asker: Xonomy.askString
      },
      "mesto":{
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
        asker: Xonomy.askString
      }
    ,
    "ulica":{
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
      asker: Xonomy.askString
    },

    "broj":{
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
      asker: Xonomy.askString
      },
      "naziv_organa":{
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
        asker: Xonomy.askString
      },
      "razlozi_zalbe":{
        isReadOnly: true
      },
      "datum_zahteva":{
        isReadOnly: true
      },
    "podaci_o_zahtevu_i_informacijama":{
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
      asker: Xonomy.askString
      }
    ,
      "drugi_podaci_za_kontakt":{
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
        asker: Xonomy.askString
      },
      "datum_zalbe":{
        isReadOnly: true
      },
      "mesto_zalbe":{
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
        asker: Xonomy.askString
      },
      "broj_zahteva":{
        isReadOnly: true
      },
      "korisnik_email":{
        isReadOnly:true
      },
      "ime_i_prezime":{
        isReadOnly:true
      }
    },

  };

  public zalbaOdlukaSpecification = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      };
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { //if element
          this.validate(jsChild); //recursion
        }
      }
    },
    elements: {
      "zalba_na_odluku": {
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
      }
      ,
      "korisnik_email":{
        isReadOnly:true
      },
      "zalioc_ime":{
        isReadOnly:true
      },
      "zalioc_prezime":{
        isReadOnly:true
      },
      "zalioc_naziv_zalbe":{
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
        asker: Xonomy.askString
      },
      "zalioc_adresa":{
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
        asker: Xonomy.askString
      }
    ,
    "zalioc_sediste":{
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
      asker: Xonomy.askString
    },
      "naziv":{
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
        asker: Xonomy.askString
      },
      "broj_zalbe":{
        isReadOnly: true
      },
      "godina_zalbe":{
        isReadOnly: true
      },
      "datum_odbijenog_zahteva":{
        isReadOnly: true
      },
      "odluka_organa_vlasti":{
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
        asker: Xonomy.askString
      },
      "datum_zakljucka_zalbe":{
        isReadOnly: true
      },
      "mesto_zakljucka_zalbe":{
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
        asker: Xonomy.askString
      },

      "drugi_podaci_za_kontakt":{
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
        asker: Xonomy.askString
      },

      "broj_zahteva":{
        isReadOnly: true
      },

      "potpis_zalioca":{
        isReadOnly: true
      }
    },
  };


  public resenjeSpecification = {
    validate: function (jsElement) {
      //Validate the element:
      let elementSpec = this.elements[jsElement.name];
      if (elementSpec.validate) elementSpec.validate(jsElement);
      //Cycle through the element's attributes:
      for (let i = 0; i < jsElement.attributes.length; i++) {
        let jsAttribute = jsElement.attributes[i];
        let attributeSpec = elementSpec.attributes[jsAttribute.name];
        if (attributeSpec.validate) attributeSpec.validate(jsAttribute);
      };
      //Cycle through the element's children:
      for (let i = 0; i < jsElement.children.length; i++) {
        let jsChild = jsElement.children[i];
        if (jsChild.type == "element") { //if element
          this.validate(jsChild); //recursion
        }
      }
    },
    elements: {
      "resenje": {
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
          },
          
        }
      },
      "naslov": {
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
      "datum": {
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
      "korisnik_email": {
        isReadOnly:true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "organ": {
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
      "podnosilac": {
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
      "naziv": {
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
      "datum_zahteva": {
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
      "trazeni_dokument": {
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
      "tekst_resenja": {
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
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "tekst_obrazlozenja": {
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
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "sud": {
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
      "taksa": {
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
      "poverenik": {
        isReadOnly:true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "broj_zalbe": {
        isReadOnly:true,
        attributes: {
          "datatype": {
            isInvisible: true,
          },
          "property": {
            isInvisible: true,
          }
        }
      },
      "ishod": {
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
      }

    }


  };
}

