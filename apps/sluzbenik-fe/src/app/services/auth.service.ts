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
      'Accept': 'application/xml'
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

}
