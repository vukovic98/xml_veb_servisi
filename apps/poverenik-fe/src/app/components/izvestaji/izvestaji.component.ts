import { Component, OnInit } from '@angular/core';
import * as txml from 'txml';
import {IzvestajService} from '../../services/izvestaj.service';

@Component({
  selector: 'app-izvestaji',
  templateUrl: './izvestaji.component.html',
  styleUrls: ['./izvestaji.component.css']
})
export class IzvestajiComponent implements OnInit {

  public izvestaji: Array<any> = [];

  constructor(private service: IzvestajService) { }

  ngOnInit(): void {
    this.service.dobaviSve()
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.izvestaji = obj[0].children;
      })
  }

  preuzmiHTML(datum: string) {
    this.service.preuzmiHTML(datum).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `izvestaj_${datum}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiPDF(datum: string) {
    this.service.preuzmiPDF(datum).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `izvestaj_${datum}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();

    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
}
