import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-podnosenje-zalbe-cutanje',
  templateUrl: './podnosenje-zalbe-cutanje.component.html',
  styleUrls: ['./podnosenje-zalbe-cutanje.component.css']
})
export class PodnosenjeZalbeCutanjeComponent implements OnInit {

  private uloga: string;
  constructor() { }

  ngOnInit(): void {
    this.uloga = localStorage.getItem('uloga');
  }

}
