import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public signUpSpecification = {
    elements: {
      korisnik: {
        menu: [{
          caption: 'Dodaj <ime_i_prezime>',
          action: Xonomy.newElementChild,
          actionParameter: '<ime_i_prezime></ime_i_prezime>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("ime_i_prezime")
          }
        },
        {
          caption: 'Dodaj <email>',
          action: Xonomy.newElementChild,
          actionParameter: '<email></email>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("email")
          }
        },
        {
          caption: 'Dodaj <lozinka>',
          action: Xonomy.newElementChild,
          actionParameter: '<lozinka></lozinka>',
          hideIf: function(jsElement) {
            return jsElement.hasChildElement("lozinka")
          }
        }
      ],
        attributes: {}
      },
      ime_i_prezime:{
        mustBeBefore: ['email', 'lozinka'],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString 
      },
      email:{
        mustBeBefore: ['lozinka'],
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      },
      lozinka:{
        hasText: true,
        asker: Xonomy.askString,
        oneliner: true
      }
    }
  }
}
