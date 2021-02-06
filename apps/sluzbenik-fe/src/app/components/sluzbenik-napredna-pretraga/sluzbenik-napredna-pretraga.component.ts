import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PretragaModel} from '../../model/shared-modules.model';
import {ObavestenjeNapredna, ResenjeNapredna, ZahtevNapredna, ZalbaNapredna} from '../../model/napredna-pretraga.model';
import {NaprednaPretragaService} from '../../services/napredna-pretraga.service';
import * as JsonToXML from 'js2xmlparser';
import * as txml from 'txml';
import {GeneratorFajlovaService} from '../../services/generator-fajlova.service';
import {ZahtevService} from '../../services/zahtev.service';
import {ObavestenjeService} from '../../services/obavestenje.service';

@Component({
  selector: 'app-sluzbenik-napredna-pretraga',
  templateUrl: './sluzbenik-napredna-pretraga.component.html',
  styleUrls: ['./sluzbenik-napredna-pretraga.component.css']
})
export class SluzbenikNaprednaPretragaComponent implements OnInit {

  public dokument: number;

  public zahtevi: Array<any> = [];
  public resenja: Array<any> = [];
  public obavestenja: Array<any> = [];
  public zalbeNaOdluku: Array<any> = [];
  public zalbeNaCutanje: Array<any> = [];

  pretragaZahtevForma = new FormGroup({
    "ime": new FormControl(''),
    "mail": new FormControl(''),
    "organ": new FormControl(''),
    "and": new FormControl('true')
  });

  pretragaObavestenjeForma = new FormGroup({
    "predmet": new FormControl(''),
    "zahtev": new FormControl(''),
    "ime2": new FormControl(''),
    "and2": new FormControl('true')
  });

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

  constructor(
    private service: NaprednaPretragaService,
    private generatorService: GeneratorFajlovaService,
    private zahtevService: ZahtevService,
    private obavestenjeService: ObavestenjeService
    ) { }

  ngOnInit(): void {
  }

  pretraziZahteve(){
    this.zahtevi = [];
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];
    this.obavestenja = [];


    let pretragaDTO: ZahtevNapredna = {
      ime: this.pretragaZahtevForma.value.ime ? this.pretragaZahtevForma.value.ime : null,
      mail: this.pretragaZahtevForma.value.mail ? this.pretragaZahtevForma.value.mail : null,
      organ: this.pretragaZahtevForma.value.organ ? this.pretragaZahtevForma.value.organ : null,
      and: this.pretragaZahtevForma.value.and,
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("zahtevNaprednaDTO", pretragaDTO, options);

    this.service.pretragaZahtev(data)
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.zahtevi = obj[0].children;
      })
  }

  pretraziObavestenja(){
    this.zahtevi = [];
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];
    this.obavestenja = [];


    let pretragaDTO: ObavestenjeNapredna = {
      predmet: this.pretragaObavestenjeForma.value.predmet ? this.pretragaObavestenjeForma.value.predmet : null,
      zahtev: this.pretragaObavestenjeForma.value.zahtev ? this.pretragaObavestenjeForma.value.zahtev : null,
      ime: this.pretragaObavestenjeForma.value.ime2 ? this.pretragaObavestenjeForma.value.ime2 : null,
      and: this.pretragaObavestenjeForma.value.and2,
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("obavestenjeNaprednaDTO", pretragaDTO, options);

    this.service.pretragaObavestenje(data)
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.obavestenja = obj[0].children;
        console.log(this.obavestenja)
      })
  }

  pretraziZalbe(){
    this.zahtevi = [];
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];
    this.obavestenja = [];


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
        console.log(response)
        let obj: any = txml.parse(response);

        this.zalbeNaCutanje = obj[0].children;
        console.log(this.zalbeNaCutanje);
      })

    data = JsonToXML.parse("zalbaOdlukaNaprednaDTO", pretragaDTO, options);

    this.service.pretragaZalbaOdluka(data)
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.zalbeNaOdluku = obj[0].children;
      })
  }

  pretraziResenja(){
    this.zahtevi = [];
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];
    this.obavestenja = [];


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
        let obj: any = txml.parse(response);

        this.resenja = obj[0].children;
      })
  }


  preuzmiHTMLZahtev(id: number) {
    this.zahtevService.preuzmiHTML(id).subscribe(response => {
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

  preuzmiPDFZahtev(id: number) {
    this.zahtevService.preuzmiPDF(id).subscribe(response => {
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

  preuzmiHTMLObavestenje(id: number) {
    this.obavestenjeService.preuzmiHTML(id).subscribe(response => {
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

  preuzmiPDFObavestenje(id: number) {
    this.obavestenjeService.preuzmiPDF(id).subscribe(response => {
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

  preuzmiHTMLResenje(id: number) {
    this.generatorService.generateHTMLResenje(id).subscribe(response => {
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
    this.generatorService.generatePDFResenje(id).subscribe(response => {
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
    this.generatorService.preuzmiHTMLZalbaOdluka(id).subscribe(response => {
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
    this.generatorService.preuzmiPDFZalbaOdluka(id).subscribe(response => {
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
    this.generatorService.preuzmiHTMLZalbaCutanje(id).subscribe(response => {
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
    this.generatorService.preuzmiPDFZalbaCutanje(id).subscribe(response => {
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
    this.generatorService.preuzmiJSONResenje(id).subscribe(response => {
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
    this.generatorService.preuzmiRDFResenje(id).subscribe(response => {
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
    this.generatorService.preuzmiJSONOdluka(id).subscribe(response => {
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
    this.generatorService.preuzmiRDFOdluka(id).subscribe(response => {
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
    this.generatorService.preuzmiJSONCutanje(id).subscribe(response => {
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
    this.generatorService.preuzmiRDFCutanje(id).subscribe(response => {
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

  preuzmiJSONObavestenje(id: number) {
    this.obavestenjeService.preuzmiJSON(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `obavestenje_${id}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDFObavestenje(id: number) {
    this.obavestenjeService.preuzmiRDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `obavestenje_${id}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiJSONZahtev(id: number) {
    this.zahtevService.preuzmiJSON(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zahtev_${id}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDFZahtev(id: number) {
    this.zahtevService.preuzmiRDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zahtev_${id}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
}


