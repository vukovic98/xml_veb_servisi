import { Component, OnInit } from '@angular/core';
import {ZahtevService} from '../../services/zahtev.service';
import {ArrayList} from '../../model/shared-modules.model';
import * as txml from 'txml';

@Component({
  selector: 'app-lista-zahteva',
  templateUrl: './lista-zahteva.component.html',
  styleUrls: ['./lista-zahteva.component.css']
})
export class ListaZahtevaComponent implements OnInit {

  public zahtevi: Array<any>;
  parser = new DOMParser();


  constructor(
    private zahtevService: ZahtevService
  ) { }

  ngOnInit(): void {
      this.zahtevService.zahteviUlogovanogKorisnika().subscribe((data) => {
        let obj: any = txml.parse(data);

        this.zahtevi = obj[0].children;
      });
  }

}
