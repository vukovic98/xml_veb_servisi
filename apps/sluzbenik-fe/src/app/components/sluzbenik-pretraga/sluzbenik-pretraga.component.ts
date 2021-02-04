import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {PretragaModel} from '../../model/shared-modules.model';
import {PretragaService} from '../../services/pretraga.service';
import * as txml from 'txml';
import {ZahtevService} from '../../services/zahtev.service';
import {ObavestenjeService} from '../../services/obavestenje.service';
import {GeneratorFajlovaService} from '../../services/generator-fajlova.service';

@Component({
  selector: 'app-sluzbenik-pretraga',
  templateUrl: './sluzbenik-pretraga.component.html',
  styleUrls: ['./sluzbenik-pretraga.component.css']
})
export class SluzbenikPretragaComponent implements OnInit {

  pretragaForma = new FormGroup({
    "dokument": new FormControl('', [Validators.required]),
    "tekst": new FormControl('', [Validators.required])
  });

  public zahtevi: Array<any> = [];
  public resenja: Array<any> = [];
  public obavestenja: Array<any> = [];
  public zalbeNaOdluku: Array<any> = [];
  public zalbeNaCutanje: Array<any> = [];

  constructor(
    private service: PretragaService,
    private zahtevService: ZahtevService,
    private obavestenjeService: ObavestenjeService,
    private generatorService: GeneratorFajlovaService
    ) { }

  ngOnInit(): void {
  }

  pretrazi() {

    this.zahtevi = [];
    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];
    this.obavestenja = [];


    let pretragaDTO: PretragaModel = {
      dokument: this.pretragaForma.value.dokument,
      tekst: this.pretragaForma.value.tekst
    };

    if(pretragaDTO.dokument == 1){
      this.service.pretraziZahteve(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zahtevi = obj[0].children;
        });
      this.service.pretraziZalbeCutanja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zalbeNaCutanje = obj[0].children;
        });
      this.service.pretraziZalbeNaOdluku(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zalbeNaOdluku = obj[0].children;
        });
      this.service.pretraziResenja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.resenja = obj[0].children;
        });
      this.service.pretraziObavestenja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.obavestenja = obj[0].children;
        });

    }
    if(pretragaDTO.dokument == 2) {
      this.service.pretraziZahteve(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zahtevi = obj[0].children;
        });
    }

    if(pretragaDTO.dokument == 3) {
      this.service.pretraziZalbeCutanja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zalbeNaCutanje = obj[0].children;
        });
      this.service.pretraziZalbeNaOdluku(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.zalbeNaOdluku = obj[0].children;
        });
    }

    if(pretragaDTO.dokument == 4) {
      this.service.pretraziResenja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.resenja = obj[0].children;
        });
    }

    if(pretragaDTO.dokument == 5) {
      this.service.pretraziObavestenja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.obavestenja = obj[0].children;
        });
    }
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
}
