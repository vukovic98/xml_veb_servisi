import { Component, OnInit } from '@angular/core';
import { ResenjaService } from 'src/app/services/resenja.service';
import * as txml from 'txml';
@Component({
  selector: 'app-resenja',
  templateUrl: './resenja.component.html',
  styleUrls: ['./resenja.component.css']
})
export class ResenjaComponent implements OnInit {
  public resenja: Array<any>;
  public naslov: string;
  private uloga: string;
  constructor(private resenjeService: ResenjaService) { }

  ngOnInit(): void {
    this.naslov = "Решења";
    this.uloga = localStorage.getItem('uloga');
    if(this.uloga == 'K'){
      this.resenjeService.resenjaUser().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.resenja = data[0].children;
      });
    } else if (this.uloga == 'P') {
      this.resenjeService.resenja().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.resenja = data[0].children;
      });
    }



  }

}
