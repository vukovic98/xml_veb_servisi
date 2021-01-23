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
  private readonly ulogovanKorisnikApi = "korisnik/ulogovanKorisnik";

  constructor(private http: HttpClient, private route: Router) { }

  ulogovanKorisnik(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.POVERENIK_APP + this.ulogovanKorisnikApi, {headers: headers});
  }
  prijava(user: LoginDto): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
    });
    return this.http.post(environment.POVERENIK_APP + this.loginPath, user, {headers: headers, responseType: 'text'});
  }

  registrujSe(user: KorisnikPrijavaDto): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml'
    });
    return this.http.post(environment.POVERENIK_APP + this.signupPath, user, {headers: headers});
  }

  getToken(): string{
    return <string> localStorage.getItem("accessToken");
  }

  isPoverenik(): boolean {

    let data = this.decodeToken(this.getToken());

    return data.uloga == "P";

  }

  isUser(): boolean {
    let data = this.decodeToken(this.getToken());

    return data.uloga == "K";
  }

  isLoggedIn(): boolean {
    let token = this.getToken();
    return !!token;
  }

  decodeToken(token: string): any {
    if (token) {
      let payload = token.split(".")[1];
      payload = window.atob(payload);
      return JSON.parse(payload);
    } else return null;
  }
}
