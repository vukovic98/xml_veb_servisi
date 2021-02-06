import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {LoginDto} from '../../model/loginDto';
import {AuthService} from '../../services/auth.service';
import {Route, Router} from '@angular/router';
import * as JsonToXML from  'js2xmlparser';
import Swal from "sweetalert2";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    lozinka: new FormControl('', Validators.required)
  });

  parser = new DOMParser();

  constructor(
    private service: AuthService,
    private route: Router) { }

  prijava(): void {
    let user = {
      email: this.loginForm.value.email,
      lozinka: this.loginForm.value.lozinka
    };
    const options = {
      declaration: {
        include: false
      }
    };

    let data: any = JsonToXML.parse("korisnikPrijavaDto", user, options);

    this.service.prijava(data)
      .subscribe(response => {
        let xmlDoc = this.parser.parseFromString(response,"text/xml");
        let token = xmlDoc.getElementsByTagName("authenticationToken")[0].childNodes[0].nodeValue;
        let uloga = xmlDoc.getElementsByTagName("uloga")[0].childNodes[0].nodeValue;
        let email = xmlDoc.getElementsByTagName("email")[0].childNodes[0].nodeValue;
        localStorage.setItem('email',email);
        let imeIprezime = xmlDoc.getElementsByTagName("imeIprezime")[0].childNodes[0].nodeValue;
        localStorage.setItem('accessToken', token);
        localStorage.setItem('uloga', uloga);
        localStorage.setItem('imeIprezime', imeIprezime);


        this.route.navigate(['/zalbe']);
      }, error => {
        Swal.fire({
          title: 'Грешка!',
          text: 'Email или лозинка су погрешни!',
          icon: 'error',
          confirmButtonColor: '#DC143C',
          confirmButtonText: 'У реду'
        })
      })


  }
  ngOnInit(): void {
  }

}
