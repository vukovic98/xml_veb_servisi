import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NaprednaPretragaService {

  private zalbaOdlukaApi: string = "zalba_na_odluku/napredna-pretraga";
  private zalbaCutanjeApi: string = "zalba_cutanje/napredna-pretraga";
  private obavestenjeApi: string ="obavestenje/napredna-pretraga";
  private zahtevApi: string = "zahtev/napredna-pretraga";
  private resenjeApi: string = "resenje/napredna-pretraga";

  constructor(private http: HttpClient) { }

  pretragaZahtev(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.zahtevApi, data, {headers: headers, responseType: 'text'});
  }

  pretragaResenje(data: any): Observable<any> {
    console.log(data);
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.resenjeApi, data, {headers: headers, responseType: 'text'});
  }

  pretragaObavestenje(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.obavestenjeApi, data, {headers: headers, responseType: 'text'});
  }

  pretragaZalbaCutanje(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.zalbaCutanjeApi, data, {headers: headers, responseType: 'text'});
  }

  pretragaZalbaOdluka(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.SLUZBENIK_APP + this.zalbaOdlukaApi, data, {headers: headers, responseType: 'text'});
  }
}
