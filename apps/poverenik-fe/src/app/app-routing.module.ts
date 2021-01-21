import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {SignupComponent} from './components/signup/signup.component';
import {HomepageComponent} from './components/homepage/homepage.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'log-in'},
  {path: 'log-in', component: LoginComponent},
  {path: 'sign-up', component: SignupComponent},
  {path: 'home-page', component: HomepageComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
