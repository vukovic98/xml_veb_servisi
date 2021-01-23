import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {

  public uloga: string = '';

  constructor(
    private authService: AuthService,
    private route: Router
  ) { }

  ngOnInit(): void {
    this.uloga = localStorage.getItem("uloga");
    console.log(this.uloga)
  }


  odjaviSe() {
    localStorage.setItem("accessToken","");
    localStorage.setItem("uloga","");
    this.uloga = '';
    this.route.navigate(["/home-page"]);
  }
}
