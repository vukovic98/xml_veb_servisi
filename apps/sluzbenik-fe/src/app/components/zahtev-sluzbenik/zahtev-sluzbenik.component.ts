import {Component, Input, OnInit} from '@angular/core';
import {ZahtevService} from '../../services/zahtev.service';
import {AuthService} from '../../services/auth.service';
import {MatDialog} from '@angular/material/dialog';
import {DodajObavestenjeComponent} from '../dodaj-obavestenje/dodaj-obavestenje.component';

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
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
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
    const dialogRef = this.dialog.open(DodajObavestenjeComponent, {
      width: '250px',
      data: {obavestenje: this.obavestenje, idZahteva: this.zahtev[0].children[0]}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      console.log("---------------");
      console.log(this.obavestenje);
    });
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
