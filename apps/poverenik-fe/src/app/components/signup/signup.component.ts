import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {KorisnikPrijavaDto} from '../../model/korisnikPrijavaDto';
import Swal from 'sweetalert2';
import * as JsonToXML from 'js2xmlparser';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private service: AuthService,
              private formBuilder: FormBuilder,
              private route: Router) { }

  signupForm = this.formBuilder.group({
      ime_i_prezime: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      lozinka: ['', [Validators.required]]
    });

  ngOnInit(): void {
  }

  registrujSe(){

    let korisnik: KorisnikPrijavaDto = {
      email: this.signupForm.value.email,
      ime_i_prezime: this.signupForm.value.ime_i_prezime,
      lozinka: this.signupForm.value.lozinka
    }

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("korisnikRegistracijaDto", korisnik, options);

    this.service.registrujSe(data).subscribe(response => {
      Swal.fire({
        title: 'Успешна регистрација!',
        icon: 'success',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'Пријави се'
      })
      this.route.navigate(['/log-in']);
    }, error => {
      Swal.fire({
        title: 'Грешка!',
        text: 'Корисник са овим email-ом већ постоји.',
        icon: 'error',
        confirmButtonColor: '#DC143C',
        confirmButtonText: 'У реду'
      })
    })
  }
}
