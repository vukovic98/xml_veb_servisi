import {Component, Input, OnInit} from '@angular/core';
import { ZahtevIzjasnjenjeCutanjeDTO } from 'src/app/model/zahtevIzjasnjenjeCutanjeDto';
import { ZahtevIzjasnjenjeCutanjeService } from 'src/app/services/zahtev-izjasnjenje-cutanje.service';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import {DatePipe} from '@angular/common'
import { ResenjaService } from 'src/app/services/resenja.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';
@Component({
  selector: 'app-zalba-cutanje',
  templateUrl: './zalba-cutanje.component.html',
  styleUrls: ['./zalba-cutanje.component.css']
})
export class ZalbaCutanjeComponent implements OnInit {

  @Input() zalba: any;
  @Input() resi: boolean;
  @Input() poverenikPregled: boolean;

  zahtev_izjasnjenje: ZahtevIzjasnjenjeCutanjeDTO;
  poslatZahtev : boolean;

  constructor(private service: ZalbeCutanjeService,
    private zahtevService: ZahtevIzjasnjenjeCutanjeService,
    private resenjeService: ResenjaService,
    private datePipe: DatePipe,
    private router: Router) { }

  ngOnInit(): void {

    this.zahtevService.pronadjiZahtevPoIdZalbe(this.zalba[0].children[0]).subscribe((data) => {
      let obj: any = txml.parse(data);

      console.log(obj);
      this.zahtev_izjasnjenje = {id_zalbe: obj[0].children[0].children[0], vreme: obj[0].children[1].children[0]}


      if(this.zahtev_izjasnjenje.id_zalbe == 0){
          this.poslatZahtev = false;
      }else{
        this.poslatZahtev = true;
      }


    });

  }
  preuzmiPDF() {
    this.service.preuzmiPDF(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zalba[0].children[0]}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
  preuzmiJSON() {
    console.log(this.zalba[0].children[0])
    this.service.preuzmiJSON(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanje_${this.zalba[0].children[0]}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
  preuzmiRDF() {
    this.service.preuzmiRDF(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_cutanje_${this.zalba[0].children[0]}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
  preuzmiHTML() {
    this.service.preuzmiHTML(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zalba[0].children[0]}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  resiZalbu(){

    if(this.poslatZahtev){

        let dodajResenjeDto = {
          "vreme": this.zahtev_izjasnjenje.vreme

        }


        const options = {
          declaration: {
            include: false
          }
        };

        let data: any = JsonToXML.parse("resenjeDTO", dodajResenjeDto, options);

        this.resenjeService.kreirajResenje(data).subscribe(response =>{

          Swal.fire({
            title: 'Прошло је време изјашњења!',
          });

        },
        error =>{

          Swal.fire({
            title: 'Службеник се још увек није изјаснио по питању ове жалбе.',
            allowOutsideClick: false,
            showCancelButton: false,
            showConfirmButton: true,
            onBeforeOpen: () => {
              Swal.showLoading();
            },
          });

        });

    }else{
      
      let currentTime = Date.now();

      let currentTimeString = this.datePipe.transform(currentTime, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

      let zahtevDto = {
        "id_zalbe": this.zalba[0].children[0],
        "vreme": currentTimeString

      };

      const options = {
        declaration: {
          include: false
        }
      };

      let data: any = JsonToXML.parse("dodajZahtevDTO", zahtevDto, options);

      this.zahtevService.kreirajZahtev(data).subscribe(response =>{

          window.location.reload();

      })

    }



}

odustani(id: number) {
  this.service.odustani(id).subscribe(
    res => {
      console.log(res,"aa");
      Swal.fire({
        title: 'Успешно сте одустали од жалбе!',
        icon: 'success',
        showConfirmButton: false,
        timer: 1200
      }).then(() => window.location.reload())
    } ,
      error => {
        Swal.fire({
          title: 'Неуспешно одустајање од жалбе.',
          icon: 'error',
          timer: 1200
        })
      }
  );
}
}