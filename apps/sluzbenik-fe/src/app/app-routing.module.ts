import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import {HomeComponent} from './components/home/home.component';
import {DodajZahtevComponent} from './components/dodaj-zahtev/dodaj-zahtev.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'log-in'},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'log-in', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'dodaj-zahtev', component: DodajZahtevComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
