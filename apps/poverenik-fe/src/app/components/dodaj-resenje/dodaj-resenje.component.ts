import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ResenjaService } from 'src/app/services/resenja.service';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';
import { ZalbeCutanjeService } from 'src/app/services/zalbe-cutanje.service';
import {XonomyService} from '../../services/xonomy.service';
import { ResenjeComponent } from '../resenje/resenje.component';
import * as txml from 'txml';
import {DatePipe} from '@angular/common'
import Swal from 'sweetalert2';
import { ResenjeMail } from 'src/app/model/resenjeMail';
import * as JsonToXML from 'js2xmlparser';
declare const Xonomy: any;

@Component({
  selector: 'app-dodaj-resenje',
  templateUrl: './dodaj-resenje.component.html',
  styleUrls: ['./dodaj-resenje.component.css']
})
export class DodajResenjeComponent implements OnInit {

  private zalba_id: string;
  private zalba_tip: string;
  private resenje_id: string;
  private email: string;
  private brojResenja: string = '-1';
  private podnosilac: string = '';
  private naziv_ustanove: string = '';
  private ulica: string = '';
  private datum_zahteva: string = '';

  private zalba: any;

  constructor(
    private xonomyService: XonomyService,
    private route: ActivatedRoute,
    private resenjeService: ResenjaService,
    private zalbaCutanjeService: ZalbeCutanjeService,
    private zalbaOdlukaService: ZalbaNaOdlukuService,
    private datePipe: DatePipe,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
    
      this.zalba_id = params['zalba_id'] || '';

      this.zalba_tip = params['zalba_tip'] || '';

      console.log(this.zalba_tip);

      if(this.zalba_tip == "cutanje"){

      this.zalbaCutanjeService.dobaviZalbuPoId(Number(this.zalba_id)).subscribe((response) =>{
        let obj: any = txml.parse(response);

        this.zalba = obj[0].children;
        this.email = this.zalba[6].children[0];
        this.podnosilac = this.zalba[3].children[0];
        this.datum_zahteva = this.zalba[7].children[0];
        console.log(this.email);

        console.log(this.datum_zahteva);
        console.log(this.zalba);

        this.resenjeService.dobaviBrojacResenja()
        .subscribe((response) =>{

          let obj2: any = txml.parse(response);

          console.log(obj2);

          this.resenje_id = obj2[0];

          console.log(this.resenje_id);

          this.prikaziXonomy();

        });

      });
      

    }else{

      
      this.zalbaOdlukaService.dobaviZalbuPoId(Number(this.zalba_id)).subscribe((response) =>{
        let obj: any = txml.parse(response);

        this.zalba = obj[0].children;
        this.email = this.zalba[7].children[0];
        this.podnosilac = this.zalba[3].children[0] + " "+this.zalba[4].children[0];
        this.datum_zahteva = this.zalba[8].children[0];
        console.log(this.email);

        console.log(this.datum_zahteva);
        console.log(this.zalba);

        this.resenjeService.dobaviBrojacResenja()
        .subscribe((response) =>{

          let obj2: any = txml.parse(response);

          console.log(obj2);

          this.resenje_id = obj2[0];

          console.log(this.resenje_id);

          this.prikaziXonomy();

        });

      });
    }
    });

    
   
  }

  prikaziXonomy(){

    let element = document.getElementById("resenjeEditor");

    let currDate = new Date();
    let dateString = this.datePipe.transform(currDate, 'yyyy-MM-dd')



    let xmlString = `<?xml version="1.0" encoding="UTF-8"?><resenje xmlns="http://ftn.uns.ac.rs/resenje" about="http://www.ftn.uns.ac.rs/rdf/examples/resenje/${this.resenje_id}" broj="${this.brojResenja}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><osnovni_podaci><naslov datatype="xs:string" property="pred:naslov"></naslov><datum datatype="xs:date" property="pred:datum_resenja">${dateString}</datum><korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email></osnovni_podaci><sadrzaj><uvod><organ datatype="xs:string" property="pred:organ">Повереник за информације од јавног значаја и заштиту података о личности</organ><podnosilac datatype="xs:string" property="pred:podnosilac">${this.podnosilac}</podnosilac><ustanova><naziv datatype="xs:string" property="pred:naziv_ustanove">${this.naziv_ustanove}</naziv><ulica>${this.ulica}</ulica></ustanova><datum_zahteva datatype="xs:date" property="pred:datum_zahteva">${this.datum_zahteva}</datum_zahteva></uvod><doneto_resenje><trazeni_dokument datatype="xs:string" property="pred:trazeni_dokument"></trazeni_dokument><tekst_resenja></tekst_resenja></doneto_resenje><obrazlozenje><tekst_obrazlozenja></tekst_obrazlozenja><sud></sud><taksa datatype="xs:decimal" property="pred:taksa"></taksa></obrazlozenje></sadrzaj><poverenik datatype="xs:string" property="pred:poverenik">Милан Мариновић</poverenik><broj_zalbe datatype="xs:string" property="pred:broj_zalbe">${this.zalba_id}</broj_zalbe><ishod datatype="xs:string" property="pred:ishod"></ishod></resenje>`;
    let specification = this.xonomyService.resenjeSpecification;
    Xonomy.render(xmlString,element,specification);
  }

  sendMail(that, base64data_pdf, base64data_html) {
    let m: ResenjeMail = {
      to: that.email,
      naslov: "еПовереник: Решење жалбе",
      content: "У прилогу се налази документ решења Ваше жалбе.",
      pdf: base64data_pdf,
      html: base64data_html
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("resenjeDTO", m, options);
    console.log(data);
    that.resenjeService.posaljiMejlResenje(data)
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

  ispitajOdustajanje(){

    console.log(this.zalba_tip);
    if(this.zalba_tip == "cutanje"){
      console.log("USAO U IF");

      this.zalbaCutanjeService.dobaviZalbuPoId(Number(this.zalba_id)).subscribe((response) =>{

        this.posaljiResenje(false);

      },(error) =>{


        this.posaljiResenje(true);

      });
    }else{
      console.log("USAO U ELSE");
      this.zalbaOdlukaService.dobaviZalbuPoId(Number(this.zalba_id)).subscribe((response) =>{

        this.posaljiResenje(false);

      },error =>{
        this.posaljiResenje(true);

      });


    }

  }

  posaljiResenje(odustao: boolean){
    let text = Xonomy.harvest();
    console.log(text);
    if(odustao == false){
    this.resenjeService.kreirajResenjeTekst(text).subscribe(
      response => {
        Swal.fire({
          title: 'Решење је успешно састављено.',
          icon: 'success',
          confirmButtonColor: '#6495ed',
          confirmButtonText: 'У реду'
        }).then((result) => {
          if(result.isConfirmed){

            const xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8081/ws/resenje', true);
    
        // The following variable contains the xml SOAP request.
        const sr =
            `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                   <dodajResenje xmlns="http://ftn.uns.ac.rs/resenje">
                     `+ text +`
                   </dodajResenje>
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

        this.resenjeService.generatePDF(Number(this.resenje_id)).subscribe(response => {
          let file = new Blob([response], { type: 'application/pdf' });

          this.resenjeService.generateHTML(Number(this.resenje_id)).subscribe(response => {
            let file_html = new Blob([response], { type: 'text/html' });


            this.read_pdf(this, file, file_html, this.read_html, this.sendMail);

          }), error => console.log('Error downloading the file'),
            () => console.info('File downloaded successfully');

        }), error => console.log('Error downloading the file'),
          () => console.info('File downloaded successfully');


          if(this.zalba_tip == 'cutanje'){

        const xmlhttp = new XMLHttpRequest();
        xmlhttp.open('POST', 'http://localhost:8081/ws/zahtev_za_izjasnjenje_cutanje', true);
    
        // The following variable contains the xml SOAP request.
        const sr =
            `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                   <izbrisiZahtevZaIzjasnjenjeCutanje xmlns="http://ftn.uns.ac.rs/zalba_cutanje">
                     <id_zalbe>`+ this.zalba_id +`</id_zalbe>
                   </izbrisiZahtevZaIzjasnjenjeCutanje>
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

          }else{


            const xmlhttp = new XMLHttpRequest();
            xmlhttp.open('POST', 'http://localhost:8081/ws/zahtev_za_izjasnjenje_odluka', true);
        
            // The following variable contains the xml SOAP request.
            const sr =
                `<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                  <soap:Body>
                       <izbrisiZahtevZaIzjasnjenjeOdluka xmlns="http://ftn.uns.ac.rs/zalba_na_odluku">
                         <id_zalbe>`+ this.zalba_id +`</id_zalbe>
                       </izbrisiZahtevZaIzjasnjenjeOdluka>
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
            
          }

          
        this.router.navigate(['/resenja']);
          }
        })
      }, еrror => {

        console.log(еrror);
        Swal.fire({
          title: 'Грешка!',
          text: 'Неуспешно састављање решења. Проверите да ли сте унели све податке исправно.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'У реду'
        })
      }

      
    )
    }else{

      Swal.fire({
        title: 'Обавештење',
        text: 'Корисник је одустао од жалбе у току састављања решења.',
        icon: 'info',
        confirmButtonColor: '#6495ed',
        confirmButtonText: 'У реду'
      })

    }
  }

}
