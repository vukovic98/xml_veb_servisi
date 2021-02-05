import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpModel } from '../model/sign-up.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  private korisnikoviZahteviApi: string = "zahtev/ulogovanKorisnik";
  private sviZahteviApi: string = "zahtev/";
  private preuzmiPDFApi: string = "zahtev/generisiPDF/";
  private preuzmiHTMLApi: string = "zahtev/generisiHTML/";
  private kreirajZahtevApi: string = "zahtev/";
  private mejlOdbijanjeZahtevaApi: string = "email/odbijen-zahtev"
  private readonly preuzmiJSONApi = "zahtev/generisiJSON/";
  private readonly preuzmiRDFApi = "zahtev/generisiRDF/";

  constructor(private http: HttpClient) { }

  zahteviUlogovanogKorisnika(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.korisnikoviZahteviApi, {headers: headers, responseType: 'text'});
  }

  sviZahtevi(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.sviZahteviApi, {headers: headers, responseType: 'text'});
  }

  dobaviZahtevPoId(id: number): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.sviZahteviApi + id, {headers: headers, responseType: 'text'});
  }

  preuzmiPDF(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTML(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  kreirajZahtev(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.kreirajZahtevApi, data, {headers: headers});
  }

  odbijZahtev(id: any): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.delete(environment.SLUZBENIK_APP + this.sviZahteviApi + id, {headers: headers});
  }


  posaljiMejlZaOdbijanjeZahteva(data: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml;;charset=UTF-8',
      'Accept': 'application/xml'
    });

    return this.http.post(environment.MAIL_APP + this.mejlOdbijanjeZahtevaApi, data, {headers: headers});
  }

  preuzmiJSON(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiJSONApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiRDF(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiRDFApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}



