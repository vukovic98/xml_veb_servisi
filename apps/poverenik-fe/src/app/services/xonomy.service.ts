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

  //public zalbaOdlukaSpecification = {};
  //public resenjeSpecification = {};
}

