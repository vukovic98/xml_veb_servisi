import { Component, OnInit } from '@angular/core';
import * as txml from 'txml';
import {ZahtevService} from '../../services/zahtev.service';

@Component({
  selector: 'app-svi-zahtevi',
  templateUrl: './svi-zahtevi.component.html',
  styleUrls: ['./svi-zahtevi.component.css']
})
export class SviZahteviComponent implements OnInit {

  public zahtevi: Array<any>;
  parser = new DOMParser();

  constructor(private zahtevService: ZahtevService) { }

  ngOnInit(): void {
    this.zahtevService.sviZahtevi().subscribe((data) => {
      let obj: any = txml.parse(data);

      this.zahtevi = obj[0].children;
    });
  }

}
