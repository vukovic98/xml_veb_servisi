import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ObavestenjeService {

  private obavestenjaApi: string = 'obavestenje/';
  private obavestenjaZaKorisnikaApi: string = 'obavestenje/ulogovanKorisnik';
  private preuzmiPDFApi: string = "obavestenje/generisiPDF/";
  private preuzmiHTMLApi: string = "obavestenje/generisiHTML/";

  constructor(
    private http: HttpClient,
    private route: Router
  ) { }

  svaObavestenja(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.obavestenjaApi, {headers: headers, responseType: 'text'});
  }

  svaObavestenjaZaKorisnika(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.obavestenjaZaKorisnikaApi, {headers: headers, responseType: 'text'});
  }

  preuzmiPDF(obavestenje_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFApi + obavestenje_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTML(obavestenje_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLApi + obavestenje_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
