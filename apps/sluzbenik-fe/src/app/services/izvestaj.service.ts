import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {PretragaModel} from '../model/shared-modules.model';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  private dobaviSveIzvestajeApi: string = "izvestaj";
  private kreirajIzvestajApi: string = "izvestaj/kreiraj-izvestaj"
  private preuzmiPDFApi: string = "izvestaj/generisiPDF/";
  private preuzmiHTMLApi: string = "izvestaj/generisiHTML/";
  private posaljiIzvestajSOAPApi: string = 'ws/izvestaj';

  constructor(private http: HttpClient) { }

  posaljiIzvestaj(data: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml'
    });

    return this.http.post(environment.POVERENIK_APP + this.posaljiIzvestajSOAPApi, data, {headers: headers, responseType: 'text'});
  }

  dobaviSve():Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.dobaviSveIzvestajeApi, {headers: headers, responseType: 'text'});
  }

  kreirajIzvestaj(): Observable<any> {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken"),
      'Accept': 'application/xml'
    });

    return this.http.get(environment.SLUZBENIK_APP + this.kreirajIzvestajApi, {headers: headers, responseType: 'text'});
  }

  preuzmiPDF(datum: string): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFApi + datum, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTML(datum: string): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLApi + datum, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
