import { Component, OnInit } from '@angular/core';
import {XonomyService} from '../../services/xonomy.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import * as JsonToXML from 'js2xmlparser';
import {ZahtevService} from '../../services/zahtev.service';
import Swal from "sweetalert2";

declare const Xonomy: any;

@Component({
  selector: 'app-dodaj-zahtev',
  templateUrl: './dodaj-zahtev.component.html',
  styleUrls: ['./dodaj-zahtev.component.css']
})
export class DodajZahtevComponent implements OnInit {

  dodajZahtevForma = new FormGroup({
    "naziv_organa": new FormControl('', [Validators.required]),
    "mesto_organa": new FormControl('', [Validators.required]),
    "opis_informacije": new FormControl('', [Validators.required]),
    "mesto_trazioca": new FormControl('', [Validators.required]),
    "ulica_trazioca": new FormControl('', [Validators.required]),
    "broj_trazioca": new FormControl('', [Validators.required]),
    "kontakt": new FormControl('', [Validators.required]),
    "opis_zahteva": new FormControl('', [Validators.required]),
    "nacin_dostave": new FormControl('', [] ),
    "drugi_nacin": new FormControl('', []),
  });

  constructor(private zahtevService: ZahtevService) { }

  ngOnInit(): void {
  }

  dodajZahtev() {
    let zahtevDto = {
      "naziv_organa": this.dodajZahtevForma.value.naziv_organa,
      "mesto_organa": this.dodajZahtevForma.value.mesto_organa,
      "opis_informacije": this.dodajZahtevForma.value.opis_informacije,
      "mesto_trazioca": this.dodajZahtevForma.value.mesto_trazioca,
      "ulica_trazioca": this.dodajZahtevForma.value.ulica_trazioca,
      "broj_trazioca": this.dodajZahtevForma.value.broj_trazioca,
      "kontakt": this.dodajZahtevForma.value.kontakt,
      "opis_zahteva": this.dodajZahtevForma.value.opis_zahteva,
      "nacin_dostave": this.dodajZahtevForma.value.nacin_dostave,
      "drugi_nacin": this.dodajZahtevForma.value.drugi_nacin
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("dodajZahtevDTO", zahtevDto, options);

    this.zahtevService.kreirajZahtev(data)
      .subscribe((response) => {
        Swal.fire({
          title: 'Успех!',
          text: 'Ваш захтев је успешно додат! Службеник ће Вам се јавити ускоро са одговором!',
          icon: 'success',
          confirmButtonText: 'У реду'
        })
      }, error => {
        console.log(error);
        Swal.fire({
          title: 'Грешка!',
          text: 'Дошло је до грешке, молимо покушајте поново!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'У реду'
        })
      })
  }

}
