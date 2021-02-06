import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GeneratorFajlovaService {

  private readonly preuzmiPDFResenjeApi = "resenje/generatePDF/";
  private readonly preuzmiHTMLResenjeApi = "resenje/generateHTML/";
  private readonly preuzmiJSONResenjeApi = "resenje/generisiJSON/";
  private readonly preuzmiRDFResenjeApi = "resenje/generisiRDF/";

  private readonly preuzmiPDFCutanjeApi = "zalba_cutanje/generisiPDF/";
  private readonly preuzmiHTMLCutanjeApi = "zalba_cutanje/generisiHTML/";
  private readonly preuzmiJSONCutanjeApi = "zalba_cutanje/generisiJSON/";
  private readonly preuzmiRDFCutanjeApi = "zalba_cutanje/generisiRDF/";

  private readonly preuzmiPDFOdlukaApi = "zalba_na_odluku/generisiPDF/";
  private readonly preuzmiJSONOdlukaApi = "zalba_na_odluku/generisiJSON/";
  private readonly preuzmiRDFOdlukaApi = "zalba_na_odluku/generisiRDF/";
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

  preuzmiJSONCutanje(zalba_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiJSONCutanjeApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
  preuzmiRDFCutanje(zalba_id: number): any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiRDFCutanjeApi + zalba_id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiRDFOdluka(id: number) {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiRDFOdlukaApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiJSONOdluka(id: number): any {

      const headers = new HttpHeaders({
        'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
      });
      return this.http.get(environment.SLUZBENIK_APP + this.preuzmiJSONOdlukaApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });

  }

  preuzmiRDFResenje(id:number):any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiRDFResenjeApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }

  preuzmiJSONResenje(id:number):any {
    const headers = new HttpHeaders({
      'Authorization' : 'Bearer ' + localStorage.getItem("accessToken")
    });
    return this.http.get(environment.SLUZBENIK_APP + this.preuzmiJSONResenjeApi + id, { headers: headers, responseType: 'arraybuffer' as 'text' });
  }
}
