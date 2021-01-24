import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignUpModel } from '../model/sign-up.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {

  private korisnikoviZahteviApi: string = "zahtev/ulogovanKorisnik";
  private preuzmiPDFApi: string = "zahtev/generisiPDF/";
  private preuzmiHTMLApi: string = "zahtev/generisiHTML/";


  constructor(private http: HttpClient) { }

  zahteviUlogovanogKorisnika(): Observable<any> {
    const headers = new HttpHeaders({
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });

    return this.http.get(environment.SLUZBENIK_APP + this.korisnikoviZahteviApi, {headers: headers, responseType: 'text'});
  }

  preuzmiPDF(zahtev_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiHTML(zahtev_id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLApi + zahtev_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

}
