import {Component, Input, OnInit} from '@angular/core';
import {ZahtevService} from '../../services/zahtev.service';
import {AuthService} from '../../services/auth.service';
import {MatDialog} from '@angular/material/dialog';
import {DodajObavestenjeComponent} from '../dodaj-obavestenje/dodaj-obavestenje.component';
import {Router} from '@angular/router';
import Swal from "sweetalert2";
import {OdbijenZahtevMail} from '../../model/shared-modules.model';
import {consoleTestResultHandler} from 'tslint/lib/test';
import * as JsonToXML from 'js2xmlparser';

@Component({
  selector: 'app-zahtev-sluzbenik',
  templateUrl: './zahtev-sluzbenik.component.html',
  styleUrls: ['./zahtev-sluzbenik.component.css']
})
export class ZahtevSluzbenikComponent implements OnInit {

  @Input() zahtev: any;
  private obavestenje: string;

  constructor(
    private service: ZahtevService,
    private authService: AuthService,
    private route: Router
  ) { }

  ngOnInit(): void {
    console.log(this.zahtev);
  }

  isApproved(): boolean {
    return JSON.parse(this.zahtev[7].children[0]);
  }

  preuzmiHTML() {
    this.service.preuzmiHTML(this.zahtev[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zahtev[0].children[0]}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  isUser(): boolean {
    return this.authService.isUser();
  }

  dodajObavestenjeDijalog() {
    this.route.navigate(['dodaj-obavestenje'], {  queryParams: {  zahtev_id: this.zahtev[0].children[0] } });
  }

  odbijZahtev() {
    this.service.odbijZahtev(this.zahtev[0].children[0])
      .subscribe((response) => {
        let sadrzaj: string = "Обавештавамо Вас да је захтев упућен " + this.zahtev[1].children[0] + ", за потражњу информације: \" " + this.zahtev[3].children[0] + " \" одбијен од стране органа власти.";

        let data: OdbijenZahtevMail = {
          to: this.zahtev[13].children[0],
          naslov: "еСлужбеник - Обавештење о одбијању захтева",
          sadrzaj: sadrzaj
        };

        const options = {
          declaration: {
            include: false
          }
        };

        let mailData: any = JsonToXML.parse("odbijenZahtevDTO", data, options);

        this.service.posaljiMejlZaOdbijanjeZahteva(mailData)
          .subscribe((response) => {
            Swal.fire({
              title: 'Успех!',
              text: 'Захтев је успешно одбијен!',
              icon: 'success',
              confirmButtonText: 'У реду'
            }).then(() => {
              location.reload();
            })
          }
          , error => {
          console.log(error);
          Swal.fire({
            title: 'Грешка!',
            text: 'Дошло је до грешке, молимо покушајте поново!',
            icon: 'error',
            confirmButtonColor: '#DC143C',
            confirmButtonText: 'У реду'
          })
        })
      })
  }

  preuzmiPDF() {
    this.service.preuzmiPDF(this.zahtev[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zahtev[0].children[0]}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();


    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

}
