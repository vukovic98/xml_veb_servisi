import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-svi-dokumenti-pretrage',
  templateUrl: './svi-dokumenti-pretrage.component.html',
  styleUrls: ['./svi-dokumenti-pretrage.component.css']
})
export class SviDokumentiPretrageComponent implements OnInit {

  @Input() public zahtevi: Array<any>;
  @Input() public obavestenja: Array<any>;
  @Input() public zalbeNaOdluku: Array<any>;
  @Input() public zalbeNaCutanje: Array<any>;
  @Input() public resenja: Array<any>;


  constructor() { }

  ngOnInit(): void {
    console.log("ZAHTEVI: ", this.zahtevi);
  }

}
