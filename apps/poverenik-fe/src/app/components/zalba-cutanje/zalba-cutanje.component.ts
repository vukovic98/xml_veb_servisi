import {Component, Input, OnInit} from '@angular/core';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';

@Component({
  selector: 'app-zalba-cutanje',
  templateUrl: './zalba-cutanje.component.html',
  styleUrls: ['./zalba-cutanje.component.css']
})
export class ZalbaCutanjeComponent implements OnInit {

  @Input() zalba: any;
  @Input() resi: boolean;
  constructor(private service: ZalbeCutanjeService) { }

  ngOnInit(): void {
  }
  preuzmiPDF() {
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
}
