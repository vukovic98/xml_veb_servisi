import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {XonomyService} from '../../services/xonomy.service';
import {ActivatedRoute} from '@angular/router';
import {ZahtevService} from '../../services/zahtev.service';
import * as txml from 'txml';
import {ObavestenjeService} from '../../services/obavestenje.service';
import Swal from "sweetalert2";
import {ObavestenjeMail} from '../../model/shared-modules.model';
import * as JsonToXML from 'js2xmlparser';
import {AuthService} from '../../services/auth.service';

declare const Xonomy: any;

@Component({
  selector: 'app-dodaj-obavestenje',
  templateUrl: './dodaj-obavestenje.component.html',
  styleUrls: ['./dodaj-obavestenje.component.css']
})
export class DodajObavestenjeComponent implements OnInit {

  private zahtev_id: string;
  private zahtev: any;
  private obavestenje_id: any;
  private email: string;

  constructor(
    private xonomyService: XonomyService,
    private route: ActivatedRoute,
    private authService: AuthService,
    private zahtevService: ZahtevService,
    private obavestenjeService: ObavestenjeService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.zahtev_id = params['zahtev_id'] || '';

      this.zahtevService.dobaviZahtevPoId(Number(this.zahtev_id))
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zahtev = obj[0].children;
          this.email = this.zahtev[13].children[0];
          console.log(this.email);

          this.obavestenjeService.dobaviBrojacZaObavestenje()
            .subscribe((response) => {
              let obj2: any = txml.parse(response);

              this.obavestenje_id = obj2[0].children[0];

              this.prikaziXonomy();
            })
        });


    });
  }

  prikaziXonomy() {
    let element = document.getElementById("obavestenjeEditor");
    let xmlString = '<?xml version="1.0" encoding="UTF-8"?><obavestenje xmlns="http://ftn.uns.ac.rs/obavestenje" about="http://www.ftn.uns.ac.rs/rdf/examples/obavestenje/' + this.obavestenje_id + '" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><osnovni_podaci><podaci_o_organu><naziv datatype="xs:string" property="pred:naziv_ustanove">' + this.zahtev[1].children[0] + '</naziv><sediste datatype="xs:string" property="pred:sediste_ustanove">' + this.zahtev[8].children[0] + '</sediste><broj_predmeta datatype="xs:string" property="pred:br_predmeta"></broj_predmeta><datum_zahteva datatype="xs:string" property="pred:datum_zahteva">' + this.zahtev[4].children[0] + '</datum_zahteva></podaci_o_organu><podaci_o_podnosiocu><ime_i_prezime datatype="xs:string" property="pred:ime_podnosioca">' + this.zahtev[6].children[0] + '</ime_i_prezime><naziv_podnosioca datatype="xs:string" property="pred:naziv_podnosioca">Raska</naziv_podnosioca><adresa_podnosioca datatype="xs:string" property="pred:adresa_podnosioca">' + this.zahtev[11].children[0] + '</adresa_podnosioca></podaci_o_podnosiocu></osnovni_podaci><sadrzaj><godina_zahteva datatype="xs:integer" property="pred:godina_zahteva"></godina_zahteva><opis_trazene_informacije datatype="xs:string" property="pred:opis">' + this.zahtev[3].children[0] + '</opis_trazene_informacije><datum_uvida></datum_uvida><cas_uvida></cas_uvida><sat_od></sat_od><sat_do></sat_do><adresa><grad></grad><ulica></ulica><broj></broj></adresa><broj_kancelarije></broj_kancelarije><cena></cena><ziro_racun poziv_na_broj="97"></ziro_racun></sadrzaj><podnozje><potpis_ovlascenog_lica/></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">' + this.zahtev_id + '</broj_zahteva></obavestenje>';

    let specification = this.xonomyService.obavestenjeSpecifikacija;
    Xonomy.render(xmlString, element, specification);
  }

  predajObavestenje() {
    let data: any = Xonomy.harvest();

    this.obavestenjeService.dodajObavestenje(data)
      .subscribe((response) => {
        Swal.fire({
          title: 'Успех!',
          text: 'Ваше обавештење је успешно додато!',
          icon: 'success',
          confirmButtonText: 'У реду'
        });
        this.obavestenjeService.preuzmiPDF(this.obavestenje_id).subscribe(response => {
          let file = new Blob([response], { type: 'application/pdf' });

          this.obavestenjeService.preuzmiHTML(this.obavestenje_id).subscribe(response => {
            let file_html = new Blob([response], { type: 'text/html' });


            this.read_pdf(this, file, file_html, this.read_html, this.sendMail);

          }), error => console.log('Error downloading the file'),
            () => console.info('File downloaded successfully');

        }), error => console.log('Error downloading the file'),
          () => console.info('File downloaded successfully');
      }, error => {
        console.log(error);
        Swal.fire({
          title: 'Грешка!',
          text: 'Дошло је до грешке, молимо покушајте поново!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'У реду'
        })
      })
  }

  sendMail(that, base64data_pdf, base64data_html) {
    let m: ObavestenjeMail = {
      to: that.email,
      naslov: "еСлужбеник: Обавештење по предатом захтеву",
      content: "У прилогу се налази документ обавештења на основу Вашег захтева.",
      pdf: base64data_pdf,
      html: base64data_html
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("obavestenjeDTO", m, options);
    that.obavestenjeService.posaljiMejlZaOdobravanje(data)
      .subscribe((response) => {
        console.log("OK");
      })
  }

  read_pdf(that, file, file_html, callback, callback2) {
    var reader = new FileReader();
    var base64data;
    reader.readAsDataURL(file);
    reader.onloadend = function() {
      base64data = reader.result;
      callback(that, base64data, file_html, callback2);
    }
  }

  read_html(that, pdf, html_file, callback) {
    var reader = new FileReader();
    var base64data;
    reader.readAsDataURL(html_file);
    reader.onloadend = function() {
      base64data = reader.result;
      callback(that, pdf, base64data);
    }
  }

}
