import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ObavestenjeService} from '../../services/obavestenje.service';
import * as txml from 'txml';

@Component({
  selector: 'app-obavestenja-korisnik',
  templateUrl: './obavestenja-korisnik.component.html',
  styleUrls: ['./obavestenja-korisnik.component.css']
})
export class ObavestenjaKorisnikComponent implements OnInit {

  public obavestenja: Array<any> = [];

  constructor(
    private authService: AuthService,
    private obavestenjeService: ObavestenjeService
  ) { }

  ngOnInit(): void {
    this.obavestenjeService.svaObavestenjaZaKorisnika()
      .subscribe((response) => {
        let obj: any = txml.parse(response);

        this.obavestenja = obj[0].children;
      })
  }

}
