import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ZalbaCutanjeComponent} from '../zalba-cutanje/zalba-cutanje.component';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';
import {ZalbaNaOdlukuService} from '../../services/zalba-na-odluku.service';
import {NaprednaPretragaService} from '../../services/napredna-pretraga.service';
import {ResenjeNapredna} from '../../model/resenjeNapredna';
import {ZalbaNapredna} from '../../model/zalbaNapredna';
import * as JsonToXML from 'js2xmlparser';
import * as txml from 'txml';
import {ResenjaService} from '../../services/resenja.service';

@Component({
  selector: 'app-napredna-pretraga',
  templateUrl: './napredna-pretraga.component.html',
  styleUrls: ['./napredna-pretraga.component.css']
})
export class NaprednaPretragaComponent implements OnInit {
  public dokument: number;

  public resenja: Array<any> = [];
  public zalbeNaOdluku: Array<any> = [];
  public zalbeNaCutanje: Array<any> = [];

  pretragaResenjeForma = new FormGroup({
    "zalba": new FormControl(''),
    "ishod": new FormControl(''),
    "korisnik": new FormControl(''),
    "and3": new FormControl('true')
  });

  pretragaZalbaForma = new FormGroup({
    "zahtev2": new FormControl(''),
    "mejl": new FormControl(''),
    "organ2": new FormControl(''),
    "and4": new FormControl('true')
  });

  constructor(private zalbaCutanjeService: ZalbeCutanjeService,
              private  zalbaOdlukaService: ZalbaNaOdlukuService,
              private resenjeService: ResenjaService,
              private service: NaprednaPretragaService) { }

  ngOnInit(): void {
  }

  pretraziZalbe(){
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];


    let pretragaDTO: ZalbaNapredna = {
      zahtev: this.pretragaZalbaForma.value.zahtev2 ? this.pretragaZalbaForma.value.zahtev2 : null,
      mejl: this.pretragaZalbaForma.value.mejl ? this.pretragaZalbaForma.value.mejl : null,
      organ: this.pretragaZalbaForma.value.organ2 ? this.pretragaZalbaForma.value.organ2 : null,
      and: this.pretragaZalbaForma.value.and4,
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("zalbaCutanjeNaprednaDTO", pretragaDTO, options);

    this.service.pretragaZalbaCutanje(data)
      .subscribe((response) => {

        let obj: any = txml.parse(response);
        this.zalbeNaCutanje = obj[0].children;
      })

    data = JsonToXML.parse("zalbaOdlukaNaprednaDTO", pretragaDTO, options);

    this.service.pretragaZalbaOdluka(data)
      .subscribe((response) => {
          let obj: any = txml.parse(response);
          this.zalbeNaOdluku = obj ? obj[0].children : [];
      })
  }

  pretraziResenja(){
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];


    let pretragaDTO: ResenjeNapredna = {
      zalba: this.pretragaResenjeForma.value.zalba ? this.pretragaResenjeForma.value.zalba : null,
      ishod: this.pretragaResenjeForma.value.ishod ? this.pretragaResenjeForma.value.ishod : null,
      korisnik: this.pretragaResenjeForma.value.korisnik ? this.pretragaResenjeForma.value.korisnik : null,
      and: this.pretragaResenjeForma.value.and3,
    };

    console.log(pretragaDTO);

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("resenjeNaprednaDTO", pretragaDTO, options);

    this.service.pretragaResenje(data)
      .subscribe((response) => {
        console.log(response)
        let obj: any = txml.parse(response);

        this.resenja = obj[0].children;
      })
  }

  preuzmiHTMLResenje(id: number) {
    this.resenjeService.generateHTML(id).subscribe(response => {
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

  preuzmiPDFResenje(id: number) {
    this.resenjeService.generatePDF(id).subscribe(response => {
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

  preuzmiHTMLZalbaNaOdluku(id: number) {
    this.zalbaOdlukaService.preuzmiHTML(id).subscribe(response => {
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

  preuzmiPDFZalbaNaOdluku(id: number) {
    this.zalbaOdlukaService.preuzmiPDF(id).subscribe(response => {
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

  preuzmiHTMLZalbaCutanje(id: number) {
    this.zalbaCutanjeService.preuzmiHTML(id).subscribe(response => {
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

  preuzmiPDFZalbaCutanje(id: number) {
    this.zalbaCutanjeService.preuzmiPDF(id).subscribe(response => {
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

  preuzmiJSONResenje(id: number) {
    this.resenjeService.preuzmiJSON(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `resenje_${id}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDFResenje(id: number) {
    this.resenjeService.preuzmiRDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `resenje_${id}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiJSONZalbaNaOdluku(id: number) {
    this.zalbaOdlukaService.preuzmiJSON(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_na_odluku_${id}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDFZalbaNaOdluku(id: number) {
    this.zalbaOdlukaService.preuzmiRDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_na_odluku_${id}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiJSONZalbaCutanje(id: number) {
    this.zalbaCutanjeService.preuzmiJSON(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanje_${id}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDFZalbaCutanje(id: number) {
    this.zalbaCutanjeService.preuzmiRDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanje_${id}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
}
