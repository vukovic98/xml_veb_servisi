import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {PretragaModel} from '../model/shared-modules.model';

@Injectable({
  providedIn: 'root'
})
export class PretragaService {

  private zahtevPretragaApi: string = "zahtev/pretraga/";
  private obavestenjePretragaApi: string = "obavestenje/pretraga/";
  private zalbaCutanjePretragaApi: string = "zalba_cutanje/pretraga/";
  private zalbaNaOdlukuPretragaApi: string = "zalba_na_odluku/pretraga/";
  private resenjePretragaApi: string = "resenje/pretraga/";

  constructor(private http: HttpClient) { }

  pretraziZahteve(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.zahtevPretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziObavestenja(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.obavestenjePretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziResenja(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.resenjePretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziZalbeNaOdluku(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.zalbaNaOdlukuPretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }

  pretraziZalbeCutanja(dto: PretragaModel):Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.zalbaCutanjePretragaApi + dto.tekst, {headers: headers, responseType: 'text'});
  }
}
