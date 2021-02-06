import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZalbaNaOdlukuService {

  private readonly zalbeApi = "zalbaNaOdluku";
  private readonly nereseneZalbeApi = "zalbaNaOdluku/neresene";
  private readonly dobaviRawApi = "zalbaNaOdluku/dobaviRaw/"
  private readonly zalbeKorisnikApi = "zalbaNaOdluku/korisnik";
  private readonly preuzmiPDFApi = "zalbaNaOdluku/generisiPDF/";
  private readonly preuzmiHTMLApi = "zalbaNaOdluku/generisiHTML/";
  private readonly preuzmiJSONApi = "zalbaNaOdluku/generisiJSON/";
  private readonly dodajZalbuApi = "zalbaNaOdluku";
  private readonly dobaviBrojacApi = "brojac/zalbaOdluka";
  private readonly dobaviNeodgovoreneZahteveApi = "ws/zahtev";
  private readonly obrisiZalbuApi = "zalbaNaOdluku/";
  private readonly preuzmiRDFApi = "zalbaNaOdluku/generisiRDF/"

  constructor(private http: HttpClient,private route: Router) { }
    dobaviNereseneZalbe() : Observable<any> {
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.nereseneZalbeApi, {headers: headers, responseType: 'text'});
    }
  
    preuzmiPDF(zalba_id: number): any {
      const headers = new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.preuzmiPDFApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
    }
    preuzmiHTML(zalba_id: number): any{
      const headers = new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.preuzmiHTMLApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
    }
    dobaviZalbe():Observable<any> {
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.zalbeApi, {headers: headers, responseType: 'text'});
    }
  
    dobaviZalbeZaKorisnika():Observable<any> {
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.zalbeKorisnikApi, {headers: headers, responseType: 'text'});
    }

    dobaviRaw(id_zalbe: number): Observable<any>{
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.dobaviRawApi+id_zalbe, {headers: headers, responseType: 'text'});
  
    }

    preuzmiJSON(zalba_id: number): any {
      const headers = new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.preuzmiJSONApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
    }
  
    dodajZalbuNaOdluku(entity: String) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.post(environment.POVERENIK_APP + this.dodajZalbuApi, entity, {headers: headers, responseType: 'text'});
    }
    dobaviBrojac(){
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.dobaviBrojacApi, {headers: headers, responseType: 'text'});
  
    }
  
    dobaviNeodgovoreneZahteve(email: string){
      const headers = new HttpHeaders({
        'Content-Type': 'text/plain',
        'Accept': '*/*',
      });
      let zahtev: string = `<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body> <pronadjiOdbijeneZahteve xmlns="http://www.ftn.uns.ac.rs/zahtev"><email>${email}</email></pronadjiOdbijeneZahteve></soap:Body></soap:Envelope>`;
      return this.http.post(environment.SLUZBENIK_APP + this.dobaviNeodgovoreneZahteveApi, zahtev, {headers: headers, responseType: 'text'});
    }
  
    odustani(id: number):Observable<any> {
      const headers = new HttpHeaders({
        'Content-Type': 'application/xml',
        'Accept': 'application/xml',
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.delete(environment.POVERENIK_APP + this.obrisiZalbuApi + id,{headers: headers, responseType: 'text'});
  
    }
    preuzmiRDF(zalba_id: number): any {
      const headers = new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.POVERENIK_APP + this.preuzmiRDFApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
    }
 
}
