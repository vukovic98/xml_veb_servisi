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
  private readonly zalbeKorisnikApi = "zalbaNaOdluku/korisnik";
  private readonly preuzmiPDFApi = "zalbaNaOdluku/generisiPDF/";
  private readonly preuzmiHTMLApi = "zalbaNaOdluku/generisiHTML/";

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

 
}
