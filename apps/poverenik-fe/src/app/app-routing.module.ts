import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {SignupComponent} from './components/signup/signup.component';
import {HomepageComponent} from './components/homepage/homepage.component';
import {ZalbeCutanjeComponent} from './components/zalbe-cutanje-lista/zalbe-cutanje.component';
import {ZalbeComponent} from './components/zalbe/zalbe.component';
import { ResenjeComponent } from './components/resenje/resenje.component';
import { ResenjaComponent } from './components/resenja/resenja.component';
import {PodnosenjeZalbeCutanjeComponent} from './components/podnosenje-zalbe-cutanje/podnosenje-zalbe-cutanje.component';
import {PretragaComponent} from './components/pretraga/pretraga.component';
import {PodnosenjeZalbeNaOdlukuComponent} from './components/podnosenje-zalbe-na-odluku/podnosenje-zalbe-na-odluku.component'

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'log-in'},
  {path: 'log-in', component: LoginComponent},
  {path: 'sign-up', component: SignupComponent},
  {path: 'home-page', component: HomepageComponent},
  {path: 'zalbe-cutanje-lista', component: ZalbeCutanjeComponent},
  {path: 'zalbe', component: ZalbeComponent},
  {path: 'resenja', component: ResenjaComponent},
  {path: 'pretraga', component: PretragaComponent},
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
