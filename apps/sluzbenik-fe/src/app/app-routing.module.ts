import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {HomeComponent} from './components/home/home.component';
import {DodajZahtevComponent} from './components/dodaj-zahtev/dodaj-zahtev.component';
import {ObavestenjaKorisnikComponent} from './components/obavestenja-korisnik/obavestenja-korisnik.component';
import {ObavestenjaSluzbenikComponent} from './components/obavestenja-sluzbenik/obavestenja-sluzbenik.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'log-in'},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'log-in', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'dodaj-zahtev', component: DodajZahtevComponent},
  {path: 'obavestenja-korisnik', component: ObavestenjaKorisnikComponent},
  {path: 'obavestenja-sluzbenik', component: ObavestenjaSluzbenikComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
