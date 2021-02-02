import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ZalbeCutanjeService {

  private readonly nereseneZalbeApi = "zalbaCutanje/neresene";
  private readonly zalbeApi = "zalbaCutanje";
  private readonly zalbeKorisnikApi = "zalbaCutanje/korisnik";
  private readonly preuzmiPDFApi = "zalbaCutanje/generisiPDF/";
  private readonly preuzmiHTMLApi = "zalbaCutanje/generisiHTML/";
  private readonly dodajZalbuApi = "zalbaCutanje";
  private readonly dobaviBrojacApi = "brojac/zalbaCutanje";
  private readonly dobaviNeodgovoreneZahteveApi = "ws/zahtev";


  constructor(private http: HttpClient, private route: Router) { }

  dobaviNereseneZalbe(): Observable<any> {
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

  dodajZalbuCutanje(entity: String) {
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
    let zahtev: string = `<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body> <pronadjiNeodgovoreneZahteve xmlns="http://www.ftn.uns.ac.rs/zahtev"><email>${email}</email></pronadjiNeodgovoreneZahteve></soap:Body></soap:Envelope>`;
    return this.http.post(environment.SLUZBENIK_APP + this.dobaviNeodgovoreneZahteveApi, zahtev, {headers: headers, responseType: 'text'});
  }
}
