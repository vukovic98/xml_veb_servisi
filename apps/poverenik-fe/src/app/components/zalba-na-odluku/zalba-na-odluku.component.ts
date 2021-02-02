import { Component, Input, OnInit } from '@angular/core';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';

@Component({
  selector: 'app-zalba-na-odluku',
  templateUrl: './zalba-na-odluku.component.html',
  styleUrls: ['./zalba-na-odluku.component.css']
})
export class ZalbaNaOdlukuComponent implements OnInit {

  @Input() zalba: any;
  @Input() resi: boolean;

  constructor(private service: ZalbaNaOdlukuService) { }

  ngOnInit(): void {
  }
  preuzmiPDF() {
    console.log("ALOOOOOO")
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

}
