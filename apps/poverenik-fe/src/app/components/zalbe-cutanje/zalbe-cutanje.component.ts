import { Component, OnInit } from '@angular/core';
import {ZalbeCutanjeService} from '../../services/zalbe-cutanje.service';
import * as JsonToXML from 'js2xmlparser';

@Component({
  selector: 'app-zalbe-cutanje',
  templateUrl: './zalbe-cutanje.component.html',
  styleUrls: ['./zalbe-cutanje.component.css']
})
export class ZalbeCutanjeComponent implements OnInit {

  constructor(private service: ZalbeCutanjeService) { }
  private zalbe: any = [];

  ngOnInit(): void {

    const options = {
      declaration: {
        include: false
      }
    };



    this.service.dobaviZalbe().subscribe( zalbe => {
      let data: any = JsonToXML.parse("zalbaCutanje", zalbe, options);
      this.zalbe = data;
    })
  }

}
