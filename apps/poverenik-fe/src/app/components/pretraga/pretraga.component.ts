import { Component, OnInit } from '@angular/core';
import {PretragaService} from '../../services/pretraga.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';
import {ZalbaNaOdlukuService} from '../../services/zalba-na-odluku.service';
import {ResenjaService} from '../../services/resenja.service';
import {PretragaModel} from '../../model/pretragaModel';
import * as txml from 'txml';
@Component({
  selector: 'app-pretraga',
  templateUrl: './pretraga.component.html',
  styleUrls: ['./pretraga.component.css']
})
export class PretragaComponent implements OnInit {

  pretragaForma = new FormGroup({
    "dokument": new FormControl('', [Validators.required]),
    "tekst": new FormControl('', [Validators.required])
  });

  public resenja: Array<any> = [];
  public zalbeNaOdluku: Array<any> = [];
  public zalbeNaCutanje: Array<any> = [];

  constructor(
    private service: PretragaService,
    private zalbaCutanjaService: ZalbeCutanjeService,
    private zalbaOdlukaService: ZalbaNaOdlukuService,
    private resenjeService: ResenjaService

  ) { }

  ngOnInit(): void {
  }
  pretrazi() {


    this.zalbeNaCutanje = [];
    this.zalbeNaOdluku = [];
    this.resenja = [];


    let pretragaDTO: PretragaModel = {
      dokument: this.pretragaForma.value.dokument,
      tekst: this.pretragaForma.value.tekst
    };

    if(pretragaDTO.dokument == 1){
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

    }


    if(pretragaDTO.dokument == 2) {
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

    if(pretragaDTO.dokument == 3) {
      this.service.pretraziResenja(pretragaDTO)
        .subscribe((response) => {
          let obj: any = txml.parse(response);

          this.resenja = obj[0].children;
        });
    }
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
    this.zalbaCutanjaService.preuzmiHTML(id).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanja_${id}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiPDFZalbaCutanje(id: number) {
    this.zalbaCutanjaService.preuzmiPDF(id).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanja_${id}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();

    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiJSONZalbaCutanje(id: number) {
    this.zalbaCutanjaService.preuzmiJSON(id).subscribe(response => {
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
    this.zalbaCutanjaService.preuzmiRDF(id).subscribe(response => {
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
}
