import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NaprednaPretragaService {

  private zalbaOdlukaApi: string = "zalbaNaOdluku/napredna-pretraga";
  private zalbaCutanjeApi: string = "zalbaCutanje/napredna-pretraga";
  private resenjeApi: string = "resenja/napredna-pretraga";

  constructor(private http: HttpClient) { }


  pretragaResenje(data: any): Observable<any> {
    console.log(data);
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.POVERENIK_APP + this.resenjeApi, data, {headers: headers, responseType: 'text'});
  }


  pretragaZalbaCutanje(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.POVERENIK_APP + this.zalbaCutanjeApi, data, {headers: headers, responseType: 'text'});
  }

  pretragaZalbaOdluka(data: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.post(environment.POVERENIK_APP + this.zalbaOdlukaApi, data, {headers: headers, responseType: 'text'});
  }
}
