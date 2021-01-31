import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {SignupComponent} from './components/signup/signup.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {ZalbeCutanjeComponent} from './components/zalbe-cutanje-lista/zalbe-cutanje.component';
import {ZalbeComponent} from './components/zalbe/zalbe.component';
import { ResenjeComponent } from './components/resenje/resenje.component';
import { ResenjaComponent } from './components/resenja/resenja.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'log-in'},
  {path: 'log-in', component: LoginComponent},
  {path: 'sign-up', component: SignupComponent},
  {path: 'home-page', component: HomepageComponent},
  {path: 'zalbe-cutanje-lista', component: ZalbeCutanjeComponent},
  {path: 'zalbe', component: ZalbeComponent},
  {path: 'resenja', component: ResenjaComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
