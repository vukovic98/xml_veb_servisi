import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ObavestenjeService} from '../../services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-obavestenja-sluzbenik',
  templateUrl: './obavestenja-sluzbenik.component.html',
  styleUrls: ['./obavestenja-sluzbenik.component.css']
})
export class ObavestenjaSluzbenikComponent implements OnInit {

  public obavestenja: Array<any> = [];

  constructor(
    private authService: AuthService,
    private obavestenjeService: ObavestenjeService
  ) { }

  ngOnInit(): void {
    this.obavestenjeService.svaObavestenja()
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.obavestenja = obj[0].children;

      })
  }

  isSluzbenik(): boolean {
    return this.authService.isSluzbenik();
  }

}
