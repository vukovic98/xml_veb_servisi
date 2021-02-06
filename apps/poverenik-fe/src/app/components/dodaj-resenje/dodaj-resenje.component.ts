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
declare const Xonomy: any;

@Component({
  selector: 'app-dodaj-resenje',
  templateUrl: './dodaj-resenje.component.html',
  styleUrls: ['./dodaj-resenje.component.css']
})
export class DodajResenjeComponent implements OnInit {

  private zalba_id: string;
  private resenje_id: string;
  private email: string;
  private brojResenja: string = '-1';
  private podnosilac: string = '';
  private naziv_ustanove: string = '';
  private ulica: string = '';
  private datum_zahteva: string = '';
  private trazeni_dokument: string = '';
  private tekst_obrazlozenja: string = '';
  private tekst_resenja: string = '';
  private poverenik: string = '';
  private broj_zalbe: string = '';
  private sud: string = '';
  private taksa: string = '';
  private ishod: string = '';

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



      this.zalbaCutanjeService.dobaviZalbuPoId(Number(this.zalba_id)).subscribe((response) =>{
        let obj: any = txml.parse(response);

        this.zalba = obj[0].children;
        this.email = this.zalba[6].children[0];
        this.podnosilac = this.zalba[3].children[0];
        console.log(this.email);

        this.resenjeService.dobaviBrojacResenja()
        .subscribe((response) =>{

          let obj2: any = txml.parse(response);

          console.log(obj2);

          this.resenje_id = obj2[0];

          console.log(this.resenje_id);

          this.prikaziXonomy();

        });

      });
      

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

  posaljiResenje(){
    let text = Xonomy.harvest();
    console.log(text);
    this.resenjeService.kreirajResenjeTekst(text).subscribe(
      response => {
        Swal.fire({
          title: 'Решење је успешно састављено.',
          icon: 'success',
          confirmButtonColor: '#6495ed',
          confirmButtonText: 'У реду'
        }).then((result) => {
          if(result.isConfirmed){
            this.router.navigate(['/zalbe']);
          }
        })
      }, еrror => {
        Swal.fire({
          title: 'Грешка!',
          text: 'Неуспешно састављање решења. Проверите да ли сте унели све податке исправно.',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'У реду'
        })
      }
    )
    
  }

}
