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

  private readonly sveZalbe = "zalbaCutanje";

  constructor(private http: HttpClient, private route: Router) { }

  dobaviZalbe(): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/xml',
      'Accept': 'application/xml',
    });
    return this.http.get<[]>(environment.POVERENIK_APP + this.sveZalbe, {headers: headers});
  }
}
