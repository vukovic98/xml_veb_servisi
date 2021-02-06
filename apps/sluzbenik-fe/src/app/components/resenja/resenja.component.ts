import { Component, OnInit } from '@angular/core';
import {ResenjaService} from "../../services/resenja.service";
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

    this.resenjeService.resenja().subscribe( zalbe => {
      let data: any = txml.parse(zalbe);
      this.resenja = data[0].children;
    });
  }

}
