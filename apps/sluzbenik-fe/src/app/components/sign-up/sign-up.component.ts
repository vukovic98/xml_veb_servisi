import { AfterViewInit, Component, OnInit } from '@angular/core';
import { SignUpModel } from 'src/app/model/sign-up.model';
import { AuthService } from 'src/app/services/auth.service';
import { XonomyService } from 'src/app/services/xonomy.service';

declare const Xonomy: any;

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit, AfterViewInit {

  constructor(
    private xonomyService: XonomyService,
    private service: AuthService
    ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    let element = document.getElementById("editor");
    let specification = this.xonomyService.signUpSpecification;
    Xonomy.render("<korisnik> </korisnik>", element, specification);
  }

  register(): void {
    let user = Xonomy.harvest();

    const data: SignUpModel = {
      text: user
    };

    console.log(data);
    this.service.registracija(data);
  }

}
