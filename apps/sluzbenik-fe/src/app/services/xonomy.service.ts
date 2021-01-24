import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public zahtevSpecifikacija = {
    elements: {
      zahtev: {
        menu: [{
          caption: 'Dodaj naziv organa',
          action: Xonomy.newElementChild,
          actionParameter: '<naziv_organa></naziv_organa>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("naziv_organa")
          }
        },
        {
          caption: 'Dodaj sedište organa',
          action: Xonomy.newElementChild,
          actionParameter: '<sediste_organa></sediste_organa>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("sediste_organa")
          }
        },
        {
          caption: 'Dodaj opis tražene informacije',
          action: Xonomy.newElementChild,
          actionParameter: '<opis_informacije></opis_informacije>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("opis_informacije")
          }
        },
          {
            caption: 'Dodaj mesto tražioca',
            action: Xonomy.newElementChild,
            actionParameter: '<mesto_trazioca></mesto_trazioca>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement("mesto_trazioca")
            }
          },
          {
            caption: 'Dodaj ulicu tražioca',
            action: Xonomy.newElementChild,
            actionParameter: '<ulica_trazioca></ulica_trazioca>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement("ulica_trazioca")
            }
          },
          {
            caption: 'Dodaj broj kuće tražioca',
            action: Xonomy.newElementChild,
            actionParameter: '<broj_trazioca></broj_trazioca>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement("broj_trazioca")
            }
          },
          {
            caption: 'Dodaj kontakt telefon tražioca',
            action: Xonomy.newElementChild,
            actionParameter: '<kontakt></kontakt>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement("kontakt")
            }
          },
          {
            caption: 'Dodaj opis zahteva',
            action: Xonomy.newElementChild,
            actionParameter: '<opis_zahteva></opis_zahteva>',
            hideIf: function(jsElement) {
              return jsElement.hasChildElement("opis_zahteva")
            }
          }
      ],
        attributes: {}
      },

      naziv_organa:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
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
