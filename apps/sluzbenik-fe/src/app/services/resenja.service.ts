import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ResenjaService {

  private readonly resenjaApi = "resenje";
  private readonly preuzmiPDFApi = "resenje/generatePDF/";
  private readonly preuzmiHTMLApi = "resenje/generateHTML/";
  private readonly preuzmiJSONApi = "resenje/generisiJSON/";
  private readonly preuzmiRDFApi = "resenje/generisiRDF/";

  constructor(private http: HttpClient, private route: Router) { }

  generatePDF(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  generateHTML(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  resenja(): Observable<any>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.resenjaApi, {headers: headers, responseType: 'text'});
  }

  preuzmiJSON(id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiJSONApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
  preuzmiRDF(id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiRDFApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
