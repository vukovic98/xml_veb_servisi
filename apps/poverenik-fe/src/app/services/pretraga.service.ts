import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {PretragaModel} from '../model/pretragaModel';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PretragaService {


  private zalbaCutanjePretragaApi: string = "zalbaCutanje/pretraga/";
  private zalbaNaOdlukuPretragaApi: string = "zalbaNaOdluku/pretraga/";
  private resenjePretragaApi: string = "resenja/pretraga/";

  constructor(private http: HttpClient) { }

  pretraziResenja(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.POVERENIK_APP + this.resenjePretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziZalbeNaOdluku(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.POVERENIK_APP + this.zalbaNaOdlukuPretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziZalbeCutanja(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.POVERENIK_APP + this.zalbaCutanjePretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }
}
