import { Component, Input, OnInit } from '@angular/core';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';
import Swal from'sweetalert2';
import {Router} from '@angular/router';

@Component({
  selector: 'app-zalba-na-odluku',
  templateUrl: './zalba-na-odluku.component.html',
  styleUrls: ['./zalba-na-odluku.component.css']
})
export class ZalbaNaOdlukuComponent implements OnInit {

  @Input() zalba: any;
  @Input() resi: boolean;
  @Input() poverenikPregled : boolean;

  constructor(private service: ZalbaNaOdlukuService,private router: Router) { }

  ngOnInit(): void {
  }
  preuzmiPDF() {
    console.log(this.zalba[0].children[0])
    this.service.preuzmiPDF(this.zalba[0].children[0]).subscribe(response => {

      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zalba[0].children[0]}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiHTML() {
    this.service.preuzmiHTML(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.zalba[0].children[0]}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiJSON() {
    console.log(this.zalba[0].children[0])
    this.service.preuzmiJSON(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/json' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_na_odluku_${this.zalba[0].children[0]}.json`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiRDF() {
    this.service.preuzmiRDF(this.zalba[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `zalba_na_odluku_${this.zalba[0].children[0]}.rdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  odustani(id: number) {
    this.service.odustani(id).subscribe(
      res => {
        console.log(res,"aa");
        Swal.fire({
          title: 'Успешно сте одустали од жалбе!',
          icon: 'success',
          showConfirmButton: false,
          timer: 1200
        }).then(() => window.location.reload())
      } ,
        error => {
          Swal.fire({
            title: 'Неуспешно одустајање од жалбе.',
            icon: 'error',
            timer: 1200
          })
        }
    );
  }

}
