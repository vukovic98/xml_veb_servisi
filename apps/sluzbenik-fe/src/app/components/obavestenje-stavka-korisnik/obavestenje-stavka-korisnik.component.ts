import {Component, Input, OnInit} from '@angular/core';
import {ObavestenjeService} from '../../services/obavestenje.service';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-obavestenje-stavka-korisnik',
  templateUrl: './obavestenje-stavka-korisnik.component.html',
  styleUrls: ['./obavestenje-stavka-korisnik.component.css']
})
export class ObavestenjeStavkaKorisnikComponent implements OnInit {

  @Input() obavestenje: any;

  constructor(
    private service: ObavestenjeService,
    private authService: AuthService) { }

  ngOnInit(): void {
  }

  preuzmiHTML() {
    this.service.preuzmiHTML(this.obavestenje[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.obavestenje[0].children[0]}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  isUser(): boolean {
    return this.authService.isUser();
  }

  preuzmiPDF() {
    this.service.preuzmiPDF(this.obavestenje[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.obavestenje[0].children[0]}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

}
