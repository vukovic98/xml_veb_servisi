import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import {Router} from '@angular/router';
import * as JsonToXML from "js2xmlparser";
import Swal from "sweetalert2";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    "email": new FormControl('', [Validators.required, Validators.email]),
    "lozinka": new FormControl('', Validators.required)
  });

  constructor(
    private service: AuthService,
    private route: Router
  ) {}

  ngOnInit(): void {
  }

  loginUser(): void {
    let loginDto = {
      "email": this.loginForm.value.email,
      "lozinka": this.loginForm.value.lozinka
    };

    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("korisnikLoginDTO", loginDto, options);

    this.service.prijava(data)
      .subscribe(response => {
        console.log(response);
        this.route.navigate(['/sign-up']);
      }, error => {
        Swal.fire({
          title: 'Greška!',
          text: 'Email ili lozinka su pogrešni!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'U redu'
        })
      })
  }

}