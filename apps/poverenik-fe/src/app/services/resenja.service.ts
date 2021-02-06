import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResenjaService {

  private readonly resenjaApi = "resenja";
  private readonly resenjaKorisnikApi = "resenja/user";
  private readonly preuzmiPDFApi = "resenja/generatePDF/";
  private readonly preuzmiHTMLApi = "resenja/generateHTML/";
  private readonly proveriIzjasnjenjeApi = "resenja/ispitajObrazlozenje";
  private readonly brojacResenjeApi = "brojac/resenje"
  private readonly kreirajResenjeTekstApi = "resenja/tekst"
  private readonly preuzmiJSONApi = "resenja/generisiJSON/";
  private readonly preuzmiRDFApi = "resenja/generisiRDF/";

  constructor(private http: HttpClient, private route: Router) { }

  generatePDF(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiPDFApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  generateHTML(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiHTMLApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  resenja(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.resenjaApi, {headers: headers, responseType: 'text'});
  }

  resenjaUser(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.resenjaKorisnikApi, {headers: headers, responseType: 'text'});
  }

  resenje(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.brojacResenjeApi, {headers: headers, responseType: 'text'});
  }

  kreirajResenje(data: any): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.POVERENIK_APP + this.resenjaApi, data, {headers: headers});

  }

  kreirajResenjeTekst(data: any): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.POVERENIK_APP + this.kreirajResenjeTekstApi, data, {headers: headers, responseType: 'text'});

  }

  proveriIzjasnjenje(data: any): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.POVERENIK_APP + this.proveriIzjasnjenjeApi, data, {headers: headers, responseType: 'text'});

  }

  dobaviBrojacResenja(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.brojacResenjeApi, {headers: headers, responseType: 'text'});


  }

  posaljiMejlResenje(data: any): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml'
    });

    return this.http.post(environment.MAIL_APP + "email/resenje", data, {headers: headers});
  }

  preuzmiJSON(id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiJSONApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
  preuzmiRDF(id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.preuzmiRDFApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
