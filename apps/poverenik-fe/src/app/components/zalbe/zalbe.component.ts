import { Component, OnInit } from '@angular/core';
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
  public naslov: string;
  constructor(private cutanjeService: ZalbeCutanjeService) { }

  ngOnInit(): void {
    this.naslov = "Žalbe na ćutanje";
    this.cutanjeService.dobaviZalbe().subscribe( zalbe => {
      let data: any = txml.parse(zalbe);
      this.zalbeCutanje = data[0].children;
    })
  }

}
