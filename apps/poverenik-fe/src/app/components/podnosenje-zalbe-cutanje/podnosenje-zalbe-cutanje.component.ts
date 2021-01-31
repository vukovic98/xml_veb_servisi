import {Component, OnInit, ViewChild} from '@angular/core';
import {XonomyService} from '../../services/xonomy.service';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';

import Swal from 'sweetalert2';
import {AuthService} from '../../services/auth.service';
declare const Xonomy: any;
@Component({
  selector: 'app-podnosenje-zalbe-cutanje',
  templateUrl: './podnosenje-zalbe-cutanje.component.html',
  styleUrls: ['./podnosenje-zalbe-cutanje.component.css']
})
export class PodnosenjeZalbeCutanjeComponent implements OnInit {

  private uloga: string;
  private email: string = '';
  private imeIprezime: string = '';
  razlogZalbe: string = '';
  private brojZalbe: number = 0;
  brojZahteva: number = 0;

  constructor
  (
    private xonomyService: XonomyService,
    private zalbaCutanjeService: ZalbeCutanjeService,
  ) {
  }

  ngOnInit(): void {
    this.uloga = localStorage.getItem('uloga');
    this.imeIprezime = localStorage.getItem('imeIprezime');
    this.email = localStorage.getItem('email');
    this.brojZalbe = 10; //dobavi broj
  }

  ovo() {
    let element = document.getElementById("zalbaCutanjeEditor");
    //let id_zalbe = 5; //dobavi
    //let xmlString = '<zalba_cutanje about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje'  + id_zalbe.toString() +
    //'" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><naziv_primaoca datatype="xs:string" property="pred:naziv_primaoca">Повереникy за информације од јавног значаја и заштиту података о личности</naziv_primaoca><adresa_ulica_primaoca datatype="xs:string" property="pred:ulica_poverenika">Булевар краља Александрa</adresa_ulica_primaoca><adresa_broj_primaoca datatype="xs:integer" property="pred:broj_kuce_poverenika">15</adresa_broj_primaoca><adresa_mesto_primaoca datatype="xs:string" property="pred:mesto_poverenika">Београд</adresa_mesto_primaoca><naziv_organa datatype="xs:string" property="pred:naziv_organa"></naziv_organa><razlog_zalbe></razlog_zalbe><datum_zahteva></datum_zahteva><podaci_o_zahtevu_i_informacijama></podaci_o_zahtevu_i_informacijama><adresa_ulica_podnosilac datatype="xs:string" property="pred:ulica_podnosioca"></adresa_ulica_podnosilac><adresa_broj_podnosilac datatype="xs:integer" property="pred:broj_kuce_podnosioca"></adresa_broj_podnosilac><adresa_mesto_podnosilac datatype="xs:string" property="pred:mesto_podnosioca"></adresa_mesto_podnosilac><drugi_podaci_za_kontakt></drugi_podaci_za_kontakt><mesto_zalbe></mesto_zalbe><datum_zalbe datatype="xs:string" property="pred:datum_zalbe">'
    //+ new Date().toISOString().slice(0, 10)
    //+"</datum_zalbe></zalba_cutanje>";
    let xmlString = '';
    //let xmlString_r1 = `<zalba_cutanje about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/${this.brojZalbe}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><zaglavlje><primalac_zalbe><naziv_primaoca datatype="xs:string" property="pred:naziv_primaoca">Повереникy за информације од јавног значаја и заштиту података о личности</naziv_primaoca><adresa><ulica datatype="xs:string" property="pred:ulica_poverenika">Булевар краља Александрa</ulica><broj datatype="xs:integer" property="pred:broj_kuce_poverenika">15</broj><mesto datatype="xs:string" property="pred:mesto_poverenika">Београд</mesto></adresa></primalac_zalbe></zaglavlje><sadrzaj><naziv_organa datatype="xs:string" property="pred:naziv_organa"></naziv_organa><razlozi_zalbe><razlog id="r_1" otkaceno="true" razlog="nije_postupio"/><razlog id="r_2" otkaceno="false" razlog="nije_postupio_u_celosti"/><razlog id="r_3" otkaceno="false" razlog="nije_postupio_u_zakonskom_roku"/></razlozi_zalbe><datum_zahteva></datum_zahteva><podaci_o_zahtevu_i_informacijama></podaci_o_zahtevu_i_informacijama></sadrzaj><podnozje><podnosilac_zalbe><korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email><ime_i_prezime datatype="xs:string" property="pred:ime_prezime_podnosioca">${this.imeIprezime}</ime_i_prezime><adresa><ulica datatype="xs:string" property="pred:ulica_podnosioca"></ulica><broj datatype="xs:integer" property="pred:broj_kuce_podnosioca"></broj><mesto datatype="xs:string" property="pred:mesto_podnosioca"></mesto></adresa><drugi_podaci_za_kontakt></drugi_podaci_za_kontakt><potpis/></podnosilac_zalbe><mesto_i_datum><mesto></mesto><datum_zalbe datatype="xs:string" property="pred:datum_zalbe">${new Date().toISOString().slice(0, 10)}</datum_zalbe></mesto_i_datum></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">${this.brojZahteva}</broj_zahteva></zalba_cutanje>`;
    //u zavisnosti sta je korisnik odabrao salje se ili xmlString_r1 2 ili 3

    switch (this.razlogZalbe) {
      case "Није поступио": {
        xmlString = `<zalba_cutanje about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/${this.brojZalbe}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><zaglavlje><primalac_zalbe><naziv_primaoca datatype="xs:string" property="pred:naziv_primaoca">Повереникy за информације од јавног значаја и заштиту података о личности</naziv_primaoca><adresa><ulica datatype="xs:string" property="pred:ulica_poverenika">Булевар краља Александрa</ulica><broj datatype="xs:integer" property="pred:broj_kuce_poverenika">15</broj><mesto datatype="xs:string" property="pred:mesto_poverenika">Београд</mesto></adresa></primalac_zalbe></zaglavlje><sadrzaj><naziv_organa datatype="xs:string" property="pred:naziv_organa"></naziv_organa><razlozi_zalbe><razlog id="r_1" otkaceno="true" razlog="nije_postupio"/><razlog id="r_2" otkaceno="false" razlog="nije_postupio_u_celosti"/><razlog id="r_3" otkaceno="false" razlog="nije_postupio_u_zakonskom_roku"/></razlozi_zalbe><datum_zahteva></datum_zahteva><podaci_o_zahtevu_i_informacijama></podaci_o_zahtevu_i_informacijama></sadrzaj><podnozje><podnosilac_zalbe><korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email><ime_i_prezime datatype="xs:string" property="pred:ime_prezime_podnosioca">${this.imeIprezime}</ime_i_prezime><adresa><ulica datatype="xs:string" property="pred:ulica_podnosioca"></ulica><broj datatype="xs:integer" property="pred:broj_kuce_podnosioca"></broj><mesto datatype="xs:string" property="pred:mesto_podnosioca"></mesto></adresa><drugi_podaci_za_kontakt></drugi_podaci_za_kontakt><potpis/></podnosilac_zalbe><mesto_i_datum><mesto></mesto><datum_zalbe datatype="xs:string" property="pred:datum_zalbe">${new Date().toISOString().slice(0, 10)}</datum_zalbe></mesto_i_datum></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">${this.brojZahteva}</broj_zahteva></zalba_cutanje>`;
        break;
      }
      case "Није поступио у целости": {
        xmlString = `<zalba_cutanje about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/${this.brojZalbe}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><zaglavlje><primalac_zalbe><naziv_primaoca datatype="xs:string" property="pred:naziv_primaoca">Повереникy за информације од јавног значаја и заштиту података о личности</naziv_primaoca><adresa><ulica datatype="xs:string" property="pred:ulica_poverenika">Булевар краља Александрa</ulica><broj datatype="xs:integer" property="pred:broj_kuce_poverenika">15</broj><mesto datatype="xs:string" property="pred:mesto_poverenika">Београд</mesto></adresa></primalac_zalbe></zaglavlje><sadrzaj><naziv_organa datatype="xs:string" property="pred:naziv_organa"></naziv_organa><razlozi_zalbe><razlog id="r_1" otkaceno="false" razlog="nije_postupio"/><razlog id="r_2" otkaceno="true" razlog="nije_postupio_u_celosti"/><razlog id="r_3" otkaceno="false" razlog="nije_postupio_u_zakonskom_roku"/></razlozi_zalbe><datum_zahteva></datum_zahteva><podaci_o_zahtevu_i_informacijama></podaci_o_zahtevu_i_informacijama></sadrzaj><podnozje><podnosilac_zalbe><korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email><ime_i_prezime datatype="xs:string" property="pred:ime_prezime_podnosioca">${this.imeIprezime}</ime_i_prezime><adresa><ulica datatype="xs:string" property="pred:ulica_podnosioca"></ulica><broj datatype="xs:integer" property="pred:broj_kuce_podnosioca"></broj><mesto datatype="xs:string" property="pred:mesto_podnosioca"></mesto></adresa><drugi_podaci_za_kontakt></drugi_podaci_za_kontakt><potpis/></podnosilac_zalbe><mesto_i_datum><mesto></mesto><datum_zalbe datatype="xs:string" property="pred:datum_zalbe">${new Date().toISOString().slice(0, 10)}</datum_zalbe></mesto_i_datum></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">${this.brojZahteva}</broj_zahteva></zalba_cutanje>`;
        break;
      }

      case "Није поступио у законском року": {
        xmlString = `<zalba_cutanje about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_cutanje/${this.brojZalbe}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><zaglavlje><primalac_zalbe><naziv_primaoca datatype="xs:string" property="pred:naziv_primaoca">Повереникy за информације од јавног значаја и заштиту података о личности</naziv_primaoca><adresa><ulica datatype="xs:string" property="pred:ulica_poverenika">Булевар краља Александрa</ulica><broj datatype="xs:integer" property="pred:broj_kuce_poverenika">15</broj><mesto datatype="xs:string" property="pred:mesto_poverenika">Београд</mesto></adresa></primalac_zalbe></zaglavlje><sadrzaj><naziv_organa datatype="xs:string" property="pred:naziv_organa"></naziv_organa><razlozi_zalbe><razlog id="r_1" otkaceno="false" razlog="nije_postupio"/><razlog id="r_2" otkaceno="false" razlog="nije_postupio_u_celosti"/><razlog id="r_3" otkaceno="true" razlog="nije_postupio_u_zakonskom_roku"/></razlozi_zalbe><datum_zahteva></datum_zahteva><podaci_o_zahtevu_i_informacijama></podaci_o_zahtevu_i_informacijama></sadrzaj><podnozje><podnosilac_zalbe><korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email><ime_i_prezime datatype="xs:string" property="pred:ime_prezime_podnosioca">${this.imeIprezime}</ime_i_prezime><adresa><ulica datatype="xs:string" property="pred:ulica_podnosioca"></ulica><broj datatype="xs:integer" property="pred:broj_kuce_podnosioca"></broj><mesto datatype="xs:string" property="pred:mesto_podnosioca"></mesto></adresa><drugi_podaci_za_kontakt></drugi_podaci_za_kontakt><potpis/></podnosilac_zalbe><mesto_i_datum><mesto></mesto><datum_zalbe datatype="xs:string" property="pred:datum_zalbe">${new Date().toISOString().slice(0, 10)}</datum_zalbe></mesto_i_datum></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">${this.brojZahteva}</broj_zahteva></zalba_cutanje>`;
        break;
      }
    }
    let specification = this.xonomyService.zalbaCutanjeSpecification;
    Xonomy.render(xmlString, element, specification);
  }
    posalji()
    {
      let text = Xonomy.harvest();
      this.zalbaCutanjeService.dodajZalbuCutanje(text).subscribe(
        response => {
          Swal.fire({
            title: 'Жалба је успешно поднета',
            icon: 'success',
            confirmButtonColor: '#6495ed',
            confirmButtonText: 'У реду'
          })
          console.log(response);
        }, еrror => {
          Swal.fire({
            title: 'Грешка!',
            text: 'Неуспешно подношење жалбе.',
            icon: 'error',
            confirmButtonColor: '#DC143C',
            confirmButtonText: 'У реду'
          })
        }
      )
    }

  sastaviZalbu() {
      console.log(this.brojZahteva,this.razlogZalbe)
    this.ovo();
  }
}

