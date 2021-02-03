import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZahteviZaIzjasnjenjeService {

  private zahteviZaIzjasnjenjeOdlukaApi: string = "izjasnjenje-odluka";
  private zahteviZaIzjasnjenjeCutanjeApi: string = "izjasnjenje-cutanje";
  private preuzmiPDFCutanjeApi: string = "zalba_cutanje/generisiPDF/";
  private preuzmiPDFOdlukaApi: string = "zalba_na_odluku/generisiPDF/";
  private preuzmiHTMLCutanjeApi: string = "zalba_cutanje/generisiHTML/";
  private preuzmiHTMLOdlukaApi: string = "zalba_na_odluku/generisiHTML/";

  constructor(private http: HttpClient) { }

  pronadjiSveZahteveZaIzjasnjenjeNaOdluku(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.zahteviZaIzjasnjenjeOdlukaApi, {headers: headers, responseType: 'text'});
  }

  preuzmiPDFCutanje(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFCutanjeApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTMLCutanje(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLCutanjeApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiPDFOdluka(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFOdlukaApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTMLOdluka(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLOdlukaApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  pronadjiSveZahteveZaIzjasnjenjeCutanje(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.zahteviZaIzjasnjenjeCutanjeApi, {headers: headers, responseType: 'text'});
  }
}
