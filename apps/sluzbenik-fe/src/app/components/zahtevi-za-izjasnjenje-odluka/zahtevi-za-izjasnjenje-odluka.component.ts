import { Component, OnInit } from '@angular/core';
import {ZahteviZaIzjasnjenjeService} from '../../services/zahtevi-za-izjasnjenje.service';
import * as txml from 'txml';
import {MatDialog} from '@angular/material/dialog';
import {DodajIzjasnjenjeNaZalbuComponent} from '../dodaj-izjasnjenje-na-zalbu/dodaj-izjasnjenje-na-zalbu.component';

@Component({
  selector: 'app-zahtevi-za-izjasnjenje-odluka',
  templateUrl: './zahtevi-za-izjasnjenje-odluka.component.html',
  styleUrls: ['./zahtevi-za-izjasnjenje-odluka.component.css']
})
export class ZahteviZaIzjasnjenjeOdlukaComponent implements OnInit {

  public zahtevi: Array<any>;
  odgovor: any = { id_zalbe: 0, sadrzaj: "", tip: "O"};

  constructor(
    private service: ZahteviZaIzjasnjenjeService,
    private dialog: MatDialog
    ) { }

  ngOnInit(): void {
    this.service.pronadjiSveZahteveZaIzjasnjenjeNaOdluku()
      .subscribe((response) => {
        let obj: any = txml.parse(response);
        this.zahtevi = obj[0].children[0].children;
      }, error => {
      })
  }

  preuzmiPDF(id: number) {
    this.service.preuzmiPDFOdluka(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${id}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();


    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiHTML(id: number) {
    this.service.preuzmiHTMLOdluka(id).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${id}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  dodajOdgovor(id: number) {
    this.odgovor.id_zalbe = id;
    const dialogRef = this.dialog.open(DodajIzjasnjenjeNaZalbuComponent, {
      width: '500px',
      data: this.odgovor
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        let post = result.data;
        let idZalbe: string = "<id_zalbe>" + post.id_zalbe + "</id_zalbe>";
        let sadrzaj: string = "<sadrzaj>" + post.sadrzaj + "</sadrzaj>";
        let tip: string = "<tip>O</tip>";

        let req: string = "<odgovor_zahtev_za_izjasnjenje>";
        req = req + idZalbe + sadrzaj + tip;
        req = req + "</odgovor_zahtev_za_izjasnjenje>";

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8082/ws/odgovorZahtevZaIzjasnjenje', true);

        // The following variable contains the xml SOAP request.
        const sr =
          `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                  <dodajOdgovorZahtevZaIzjasnjenje xmlns="http://ftn.uns.ac.rs/izjasnjenje/odgovor">
                     `+ req +`
                   </dodajOdgovorZahtevZaIzjasnjenje>
                 </soap:Body>
               </soap:Envelope>`;

        xmlhttp.onreadystatechange =  () => {
          if (xmlhttp.readyState == 4) {
            if (xmlhttp.status == 200) {
              const xml = xmlhttp.responseXML;
              // Here I'm getting the value contained by the <return> node.
              const response_number = parseInt(xml.getElementsByTagName('return')[0].childNodes[0].nodeValue);
              // Print result square number.
              console.log(response_number);
            }
          }
        }
        // Send the POST request.
        xmlhttp.setRequestHeader('Content-Type', 'text/xml');
        xmlhttp.responseType = 'document';
        xmlhttp.send(sr);
      }
    });
  }

}
