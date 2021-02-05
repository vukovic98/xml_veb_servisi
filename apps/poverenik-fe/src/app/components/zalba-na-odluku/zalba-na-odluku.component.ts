import { Component, Input, OnInit } from '@angular/core';
import { ZahtevIzjasnjenjeCutanjeDTO } from 'src/app/model/zahtevIzjasnjenjeCutanjeDto';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';
import * as txml from 'txml';
import * as JsonToXML from 'js2xmlparser';
import Swal from 'sweetalert2';
import { ResenjaService } from 'src/app/services/resenja.service';
import {DatePipe} from '@angular/common'
@Component({
  selector: 'app-zalba-na-odluku',
  templateUrl: './zalba-na-odluku.component.html',
  styleUrls: ['./zalba-na-odluku.component.css']
})
export class ZalbaNaOdlukuComponent implements OnInit {

  @Input() zalba: any;
  @Input() resi: boolean;

  poslatZahtev : boolean;
  zahtev_izjasnjenje: ZahtevIzjasnjenjeCutanjeDTO;

  constructor(private datePipe: DatePipe,
    private resenjeService: ResenjaService,
    private service: ZalbaNaOdlukuService) { }

  ngOnInit(): void {
  }
  preuzmiPDF() {
    console.log("ALOOOOOO")
    console.log(this.zalba[0].children[0])
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

        console.log("ZALBA: " + this.zalba);





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

      console.log("ZALBA: " + this.zalba);

      let data: any = JsonToXML.parse("dodajZahtevDTO", zahtevDto, options);
/*
      this.zahtevService.kreirajZahtev(data).subscribe(response =>{

          window.location.reload();

      });

      this.service.dobaviRaw(this.zalba[0].children[0]).subscribe(response =>{

        console.log(response);

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8081/ws/zahtev_za_izjasnjenje_cutanje', true);
    
        // The following variable contains the xml SOAP request.
        const sr =
            `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                   <dodajZahtevZaIzjasnjenjeCutnje xmlns="http://ftn.uns.ac.rs/zalba_cutanje">
                     `+ response +`
                   </dodajZahtevZaIzjasnjenjeCutnje>
                 </soap:Body>
               </soap:Envelope>`;
    
        console.log("SOAP: "+sr);

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

      });
*/

    }



}

}
