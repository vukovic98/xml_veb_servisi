import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZahtevizjasnjenjeOdlukaService {

  private readonly zahteviIzjasnjenjeCutanjeApi = "zahtev_za_izjasnjenje_odluka";
  private readonly soapKreirajZahtevIzjasnjenjeCutanjeApi = "/ws/zahtev_za_izjasnjenje_odluka";

  constructor(private http: HttpClient,private route: Router) { }

  pronadjiZahtevPoIdZalbe(id_zalbe: number): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.zahteviIzjasnjenjeCutanjeApi +"/"+ id_zalbe, {headers: headers, responseType: 'text'});


  }

  kreirajZahtev(data: any): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.POVERENIK_APP + this.zahteviIzjasnjenjeCutanjeApi, data, {headers: headers});

  }

  kreirajZahtevSluzbenik(data: any): Observable<any>{

    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.post(environment.SLUZBENIK_APP + this.soapKreirajZahtevIzjasnjenjeCutanjeApi, data, {headers: headers});
  }
}
