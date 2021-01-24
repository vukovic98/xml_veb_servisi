import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginDto} from '../model/loginDto';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ZalbeCutanjeService {

  private readonly nereseneZalbeApi = "zalbaCutanje/neresene";
  private readonly zalbeApi = "zalbaCutanje";
  private readonly preuzmiPDFApi = "zalbaCutanje/generisiPDF/";


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

  dobaviZalbe():Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.POVERENIK_APP + this.zalbeApi, {headers: headers, responseType: 'text'});
  }
}
