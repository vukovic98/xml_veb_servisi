import { Injectable } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {LoginDto} from '../model/loginDto';
import {KorisnikPrijavaDto} from '../model/korisnikPrijavaDto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly loginPath = 'korisnik/prijava';
  private readonly signupPath = 'korisnik/registracija';

  constructor(private http: HttpClient, private route: Router) { }

  prijava(user: LoginDto): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml'
    });
    return this.http.post(environment.POVERENIK_APP + this.loginPath, user, {headers: headers});
  }

  registrujSe(user: KorisnikPrijavaDto): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml'
    });
    return this.http.post(environment.POVERENIK_APP + this.signupPath, user, {headers: headers});
  }
}
