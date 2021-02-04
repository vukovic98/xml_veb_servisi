import { Component, OnInit } from '@angular/core';
import {ZahteviZaIzjasnjenjeService} from '../../services/zahtevi-za-izjasnjenje.service';
import * as txml from 'txml';
import {MatDialog} from '@angular/material/dialog';
import {DodajIzjasnjenjeNaZalbuComponent} from '../dodaj-izjasnjenje-na-zalbu/dodaj-izjasnjenje-na-zalbu.component';

@Component({
  selector: 'app-zahtevi-za-izjasnjenje-cutanje',
  templateUrl: './zahtevi-za-izjasnjenje-cutanje.component.html',
  styleUrls: ['./zahtevi-za-izjasnjenje-cutanje.component.css']
})
export class ZahteviZaIzjasnjenjeCutanjeComponent implements OnInit {

  public zahtevi: Array<any>;
  odgovor: any = { id_zalbe: 0, sadrzaj: "", tip: "C"};

  constructor(
    private service: ZahteviZaIzjasnjenjeService,
    private dialog: MatDialog,
    private izjasnjenjeService: ZahteviZaIzjasnjenjeService
    ) { }

  ngOnInit(): void {
    this.service.pronadjiSveZahteveZaIzjasnjenjeCutanje()
      .subscribe((response) => {
        let obj: any = txml.parse(response);
        this.zahtevi = obj[0].children[0].children;
      })
  }

  preuzmiPDF(id: number) {
    this.service.preuzmiPDFCutanje(id).subscribe(response => {
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
    this.service.preuzmiHTMLCutanje(id).subscribe(response => {
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
        let tip: string = "<tip>C</tip>";

        let req: string = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><dodajOdgovorZahtevZaIzjasnjenje xmlns=\"http://ftn.uns.ac.rs/izjasnjenje/odgovor\"><odgovor_zahtev_za_izjasnjenje>";
        req = req + idZalbe + sadrzaj + tip;
        req = req + "</odgovor_zahtev_za_izjasnjenje></dodajOdgovorZahtevZaIzjasnjenje></soap:Body></soap:Envelope>";

        this.izjasnjenjeService.posaljiOdgovorNaCutanje(req)
          .subscribe((response) => {
            console.log(response);
          })
      }
    });
  }

}
