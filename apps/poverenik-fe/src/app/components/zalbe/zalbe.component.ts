import { Component, OnInit } from '@angular/core';
import { ZalbaNaOdlukuService } from 'src/app/services/zalba-na-odluku.service';
import * as txml from 'txml';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';

@Component({
  selector: 'app-zalbe',
  templateUrl: './zalbe.component.html',
  styleUrls: ['./zalbe.component.css']
})
export class ZalbeComponent implements OnInit {
  public zalbeCutanje: Array<any>;
  public zalbeOdbijanje: Array<any>;

  public zalbeNaOdluku: Array<any>;
  public naslov: string;
  public uloga: string;

  public naslov2: string;
  constructor(private cutanjeService: ZalbeCutanjeService, private odlukaService: ZalbaNaOdlukuService) { }

  ngOnInit(): void {
    this.naslov = "Жалбе на ћутање";
    this.naslov2 = "Жалбе на одлуку";
    this.uloga = localStorage.getItem('uloga');
    if(this.uloga == 'K'){
      this.cutanjeService.dobaviZalbeZaKorisnika().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.zalbeCutanje = data[0].children;
      });
    } else if (this.uloga == 'P') {
      this.cutanjeService.dobaviZalbe().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.zalbeCutanje = data[0].children;
      });
    }

    if(this.uloga == 'K'){
      this.odlukaService.dobaviZalbeZaKorisnika().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.zalbeNaOdluku = data[0].children;
      });
    } else if (this.uloga == 'P') {
      this.odlukaService.dobaviZalbe().subscribe( zalbe => {
        let data: any = txml.parse(zalbe);
        this.zalbeNaOdluku = data[0].children;
      });
    }

  }

}
