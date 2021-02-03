import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-sluzbenik-pretraga',
  templateUrl: './sluzbenik-pretraga.component.html',
  styleUrls: ['./sluzbenik-pretraga.component.css']
})
export class SluzbenikPretragaComponent implements OnInit {

  pretragaForma = new FormGroup({
    "dokument": new FormControl('', [Validators.required]),
    "tekst": new FormControl('', [Validators.required])
  });

  constructor() { }

  ngOnInit(): void {
  }

  pretrazi() {}

}
