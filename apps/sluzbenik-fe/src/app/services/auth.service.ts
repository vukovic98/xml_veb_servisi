import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpModel } from '../model/sign-up.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private korisnikRegistracijaApi: string = "korisnik/registracija";
  private korisnikPrijavaApi: string = "korisnik/prijava";
  private ulogovanKorisnikApi: string = "korisnik/ulogovanKorisnik"

  constructor(private http: HttpClient) { }

  ulogovanKorisnik(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.ulogovanKorisnikApi, {headers: headers});
  }

  registracija(data: SignUpModel): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.korisnikRegistracijaApi, data, {headers: headers});
  }

  prijava(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.korisnikPrijavaApi, data, {headers: headers, responseType: 'text'});
  }

  getToken(): string{
    return <string> localStorage.getItem("accessToken");
  }

  isSluzbenik(): boolean {

    let data = this.decodeToken(this.getToken());

    return data.uloga == "S";

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
