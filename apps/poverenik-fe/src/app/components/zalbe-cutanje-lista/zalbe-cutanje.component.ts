import { Component, OnInit } from '@angular/core';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-zalbe-cutanje',
  templateUrl: './zalbe-cutanje.component.html',
  styleUrls: ['./zalbe-cutanje.component.css']
})
export class ZalbeCutanjeComponent implements OnInit {

  constructor(private service: ZalbeCutanjeService) { }

  public zalbe: Array<any>;
  public naslov: string;
  parser = new DOMParser();

  ngOnInit(): void {
    this.naslov = "Nerešene žalbe na ćutanje";

    this.service.dobaviNereseneZalbe().subscribe( zalbe => {
      let data: any = txml.parse(zalbe);
      this.zalbe = data[0].children;
    })
  }

}
