import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  private dobaviSveIzvestajeApi: string = "izvestaj";
  private preuzmiPDFApi: string = "izvestaj/generisiPDF/";
  private preuzmiHTMLApi: string = "izvestaj/generisiHTML/";

  constructor(private http: HttpClient) { }

  dobaviSve():Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.POVERENIK_APP + this.dobaviSveIzvestajeApi, {headers: headers, responseType: 'text'});
  }

  preuzmiPDF(datum: string): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiPDFApi + datum, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTML(datum: string): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiHTMLApi + datum, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
