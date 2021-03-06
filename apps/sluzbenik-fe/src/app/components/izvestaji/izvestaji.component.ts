import { Component, OnInit } from '@angular/core';
import {IzvestajService} from '../../services/izvestaj.service';
import * as txml from 'txml';

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.css']
})
export class IzvestajiComponent implements OnInit {

  public izvestaji: Array<any> = [];
  public newIzvestaj: any;

  constructor(private service: IzvestajService) { }

  ngOnInit(): void {
    this.service.dobaviSve()
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.izvestaji = obj[0].children;
      })
  }

  kreirajIzvestaj() {
    this.service.kreirajIzvestaj()
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.newIzvestaj = obj[0].children;
        console.log(this.newIzvestaj)
        let soap: string = `<?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
          <soap:Body>
          <dodajIzvestaj xmlns="http://www.ftn.uns.ac.rs/izvestaj">
            <izvestaj datum="` + this.newIzvestaj[4].children[0] + `">
                <broj_zahteva>` + this.newIzvestaj[0].children[0] + `</broj_zahteva>
                <broj_odbijenih_zahteva>` + this.newIzvestaj[1].children[0] + `</broj_odbijenih_zahteva>
                <broj_zalbi_na_cutanje>` + this.newIzvestaj[2].children[0] + `</broj_zalbi_na_cutanje>
                <broj_zalbi_na_odluku>` + this.newIzvestaj[3].children[0] + `</broj_zalbi_na_odluku>
            </izvestaj>
            </dodajIzvestaj>
          </soap:Body>
        </soap:Envelope>`;

        this.service.posaljiIzvestaj(soap)
          .subscribe((response) => {})

        this.service.dobaviSve()
          .subscribe((response) => {
            let obj: any = txml.parse(response);

            this.izvestaji = obj[0].children;
          })
      })


  }

  preuzmiHTML(datum: string) {
    this.service.preuzmiHTML(datum).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `izvestaj_${datum}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiPDF(datum: string) {
    this.service.preuzmiPDF(datum).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `izvestaj_${datum}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();

    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

}
