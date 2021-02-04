import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GeneratorFajlovaService {

  private readonly preuzmiPDFResenjeApi = "resenje/generatePDF/";
  private readonly preuzmiHTMLResenjeApi = "resenje/generateHTML/";
  private readonly preuzmiPDFCutanjeApi = "zalba_cutanje/generisiPDF/";
  private readonly preuzmiHTMLCutanjeApi = "zalba_cutanje/generisiHTML/";
  private readonly preuzmiPDFOdlukaApi = "zalba_na_odluku/generisiPDF/";
  private readonly preuzmiHTMLOdlukaApi = "zalba_na_odluku/generisiHTML/";

  constructor(private http: HttpClient) { }

  generatePDFResenje(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFResenjeApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  generateHTMLResenje(id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLResenjeApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiPDFZalbaCutanje(zalba_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFCutanjeApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
  preuzmiHTMLZalbaCutanje(zalba_id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLCutanjeApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiPDFZalbaOdluka(zalba_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiPDFOdlukaApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
  preuzmiHTMLZalbaOdluka(zalba_id: number): any{
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiHTMLOdlukaApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
