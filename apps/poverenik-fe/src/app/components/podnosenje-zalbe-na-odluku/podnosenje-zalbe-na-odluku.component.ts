import {Component, OnInit, ViewChild} from '@angular/core';
import {XonomyService} from '../../services/xonomy.service';
import Swal from 'sweetalert2';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';
import * as txml from 'txml';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';
declare const Xonomy: any;

@Component({
  selector: 'app-podnosenje-zalbe-na-odluku',
  templateUrl: './podnosenje-zalbe-na-odluku.component.html',
  styleUrls: ['./podnosenje-zalbe-na-odluku.component.css']
})
export class PodnosenjeZalbeNaOdlukuComponent implements OnInit {

  private uloga: string;
  private email: string = '';
  private imeZalioca: string = '';
  private prezimeZalioca: string = '';
  private brojZalbe: string = '-1';
  brojZahteva: string = '0';
  zahtev: any;
  btnPosalji = false;
  datumZahteva = '';
  public zahtevi: Array<any>;
  constructor
  (
    private xonomyService: XonomyService,
    private zalbaNaOdlukuService: ZalbaNaOdlukuService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.uloga = localStorage.getItem('uloga');
    let imeIprezime = localStorage.getItem('imeIprezime');
    this.prezimeZalioca = imeIprezime.split(' ')[1];
    this.imeZalioca = imeIprezime.split(' ')[0];
    this.email = localStorage.getItem('email');
    this.zalbaNaOdlukuService.dobaviNeodgovoreneZahteve(this.email).subscribe(
      res => {
        let data: any = txml.parse(res);
        this.zahtevi = data[0].children[0].children[0].children[0].children;
      }
    )
  }

  prikaziXonomy() {

    let element = document.getElementById("zalbaNaOdlukuEditor");
    let xmlString =   `<?xml version="1.0" encoding="UTF-8"?><zalba_na_odluku xmlns="http://ftn.uns.ac.rs/zalba_na_odluku" about="http://www.ftn.uns.ac.rs/rdf/examples/zalba_na_odluku/${this.brojZalbe}" vocab="http://www.ftn.uns.ac.rs/rdf/examples/predicate/"><osnovni_podaci><podaci_o_zaliocu>  <korisnik_email datatype="xs:string" property="pred:korisnik">${this.email}</korisnik_email><zalioc_ime datatype="xs:string" property="pred:ime_zalioca">${this.imeZalioca}</zalioc_ime><zalioc_prezime datatype="xs:string" property="pred:prezime_zalioca">${this.prezimeZalioca}</zalioc_prezime><zalioc_naziv_zalbe datatype="xs:string" property="pred:naziv_zalbe"></zalioc_naziv_zalbe><zalioc_adresa datatype="xs:string" property="pred:adresa_zalioca"></zalioc_adresa><zalioc_sediste datatype="xs:string" property="pred:sediste_zalioca"></zalioc_sediste> </podaci_o_zaliocu><podaci_o_organu><naziv datatype="xs:string" property="pred:naziv_organa"></naziv></podaci_o_organu></osnovni_podaci><sadrzaj><broj_zalbe datatype="xs:integer" property="pred:broj_zalbe">${this.brojZalbe}</broj_zalbe><godina_zalbe datatype="xs:string" property="pred:godina_zalbe">${ (new Date()).getFullYear() }</godina_zalbe><datum_odbijenog_zahteva datatype="xs:string" property="pred:datum_odbijenog_zahteva">${this.datumZahteva}</datum_odbijenog_zahteva><odluka_organa_vlasti datatype="xs:string" property="pred:odluka"></odluka_organa_vlasti></sadrzaj><podnozje><mesto_zakljucka_zalbe datatype="xs:string" property="pred:mesto_zakljucka_zalbe"></mesto_zakljucka_zalbe><datum_zakljucka_zalbe datatype="xs:string" property="pred:datum_zakljucka_zalbe">${new Date().toISOString().slice(0, 10)}</datum_zakljucka_zalbe> <podaci_o_zaliocu><zalioc_ime datatype="xs:string" property="pred:ime_zalioca">${this.imeZalioca}</zalioc_ime><zalioc_prezime datatype="xs:string" property="pred:prezime_zalioca">${this.prezimeZalioca}</zalioc_prezime><zalioc_adresa datatype="xs:string" property="pred:adresa_zalioca"></zalioc_adresa><drugi_podaci_za_kontakt datatype="xs:string" property="pred:kontakt_zalioca"></drugi_podaci_za_kontakt><potpis_zalioca datatype="xs:string" property="pred:potpis_zalioca"></potpis_zalioca></podaci_o_zaliocu></podnozje><broj_zahteva datatype="xs:string" property="pred:broj_zahteva">${this.brojZahteva}</broj_zahteva></zalba_na_odluku>`;
    let specification = this.xonomyService.zalbaOdlukaSpecification;
    Xonomy.render(xmlString, element, specification);
  }
    posalji()
    {
      let text = Xonomy.harvest();
      console.log(text);
      this.zalbaNaOdlukuService.dodajZalbuNaOdluku(text).subscribe(
        response => {
          Swal.fire({
            title: 'Жалба је успешно поднета',
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
            text: 'Неуспешно подношење жалбе. Проверите да ли сте унели све податке исправно.',
            icon: 'error',
            confirmButtonColor: '#DC143C',
            confirmButtonText: 'У реду'
          })
        }
      )
    }

  sastaviZalbu() {
    this.btnPosalji = true;
    let lista: string[] = this.zahtev.attributes['about'].split('/');
    this.brojZahteva = lista[lista.length-1];
    this.datumZahteva = this.zahtev.children[2].children[0].children[1].children[0];
    this.zalbaNaOdlukuService.dobaviBrojac().subscribe(res => {
      console.log(res)
      this.brojZalbe = res;
      this.prikaziXonomy();
    });


  }
}

