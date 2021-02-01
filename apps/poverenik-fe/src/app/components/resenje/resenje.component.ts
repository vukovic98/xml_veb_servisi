import { Component, Input, OnInit } from '@angular/core';
import { ResenjaService } from 'src/app/services/resenja.service';

@Component({
  selector: 'app-resenje',
  templateUrl: './resenje.component.html',
  styleUrls: ['./resenje.component.css']
})
export class ResenjeComponent implements OnInit {

  @Input() resenje: any;
  @Input() odobreno: boolean;
  constructor(private service: ResenjaService) { }

  ngOnInit(): void {

  }
  preuzmiPDF() {
    this.service.generatePDF(this.resenje[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'application/pdf' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.resenje[0].children[0]}.pdf`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }

  preuzmiHTML() {
    this.service.generateHTML(this.resenje[0].children[0]).subscribe(response => {
      let file = new Blob([response], { type: 'text/html' });
      var fileURL = URL.createObjectURL(file);
      let a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.href = fileURL;
      a.download = `${this.resenje[0].children[0]}.html`;
      a.click();
      window.URL.revokeObjectURL(fileURL);
      a.remove();
    }), error => console.log('Error downloading the file'),
      () => console.info('File downloaded successfully');
  }
}
