import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

declare const Xonomy: any;

@Component({
  selector: 'app-dodaj-obavestenje',
  templateUrl: './dodaj-obavestenje.component.html',
  styleUrls: ['./dodaj-obavestenje.component.css']
})
export class DodajObavestenjeComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<DodajObavestenjeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.prikaziXonomy();
  }

  prikaziXonomy() {
    let element = document.getElementById("obavestenjeEditor");
    let xmlString = '';

    let specification = this.xonomyService.zalbaCutanjeSpecification;
    Xonomy.render(xmlString, element, specification);
  }
  }

}
