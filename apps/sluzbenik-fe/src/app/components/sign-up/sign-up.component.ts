import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SignUpModel } from 'src/app/model/sign-up.model';
import { AuthService } from 'src/app/services/auth.service';
import { XonomyService } from 'src/app/services/xonomy.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import * as JsonToXML from 'js2xmlparser';
import Swal from "sweetalert2";
import {Router} from '@angular/router';

declare const Xonomy: any;

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  signUpForm = new FormGroup({
    "ime_i_prezime": new FormControl('', [Validators.required]),
    "email": new FormControl('', [Validators.required, Validators.email]),
    "lozinka": new FormControl('', Validators.required)
  });

  constructor(
    private service: AuthService,
    private route: Router
    ) { }

  ngOnInit(): void {
  }

  register(): void {
    let signUpDto = {
      "ime_i_prezime": this.signUpForm.value.ime_i_prezime,
      "email": this.signUpForm.value.email,
      "lozinka": this.signUpForm.value.lozinka
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("korisnikSignUpDTO", signUpDto, options);

    this.service.registracija(data)
      .subscribe(response => {
        this.route.navigate(['/']);
      }, error => {
        Swal.fire({
          title: 'Greška!',
          text: 'Korisnik sa unetim email-om već postoji!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'U redu'
        })
      })
  }

}
