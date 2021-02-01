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
    // <naziv datatype="xs:string" property="pred:naziv_ustanove">FTN</naziv>
//     <sediste datatype="xs:string" property="pred:sediste_ustanove">Novi Sad</sediste>
// <broj_predmeta datatype="xs:string" property="pred:br_predmeta">12345</broj_predmeta>
//     <datum_zahteva datatype="xs:string" property="pred:datum_zahteva">2020-12-24</datum_zahteva>
    elements: {
      obavestenje: {
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

      naziv:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        isReadOnly: true
      },
      sediste_organa:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      opis_informacije:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      mesto_trazioca:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      ulica_trazioca:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      broj_trazioca:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      kontakt:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      opis_zahteva:{
        hasText: true,
        asker: Xonomy.askPicklist,
        askerParameter: [
          {value: "zahtev_obavestenje", caption: "Zahtev za obaveštenjem da li organ poseduje traženu informaciju"},
          {value: "zahtev_kopija", caption: "Zahtev za uvid u dokument koji sadrži traženu informaciju"},
          {value: "zahtev_uvid", caption: "Zahtev za kopiju dokumenta koji sadrži traženu informaciju"},
          {value: "zahtev_dostava", caption: "Zahtev za dostavu kopije dokumenta koji sadrži traženu informaciju"}
        ],
        menu: [
          {
            caption: 'Dodaj način dostavljanja zahteva',
            action: Xonomy.newElementChild,
            actionParameter: '<nacin_dostave></nacin_dostave>',
            hideIf: function(jsElement) {
              return (jsElement.hasChildElement("nacin_dostave"))
            }
          }
        ]
      },
      nacin_dostave: {
        hasText: true,
        oneliner: false,
        asker: Xonomy.askPicklist,
        askerParameter: [
          {value: "posta", caption: "Dostava poštom"},
          {value: "faks", caption: "Dostava faksom"},
          {value: "e_posta", caption: "Dostava mejlom"},
          {value: "drugi", caption: "Drugi način dostave"}
        ],
      menu: [
        {
          caption: "Ukloni način dostave",
          action: Xonomy.deleteElement
        }
      ]
    }
    }
  }
}
