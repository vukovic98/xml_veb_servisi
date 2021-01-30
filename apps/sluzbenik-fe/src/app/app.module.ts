import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { HomeComponent } from './components/home/home.component';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { CommonModule } from "@angular/common";
import { MatListModule } from "@angular/material/list";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatCardModule } from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import { ListaZahtevaComponent } from './components/lista-zahteva/lista-zahteva.component';
import { ZahtevComponent } from './components/zahtev/zahtev.component';
import { DodajZahtevComponent } from './components/dodaj-zahtev/dodaj-zahtev.component';
import { SviZahteviComponent } from './components/svi-zahtevi/svi-zahtevi.component';
import { ZahtevSluzbenikComponent } from './components/zahtev-sluzbenik/zahtev-sluzbenik.component';
import { ObavestenjaKorisnikComponent } from './components/obavestenja-korisnik/obavestenja-korisnik.component';
import { ObavestenjaSluzbenikComponent } from './components/obavestenja-sluzbenik/obavestenja-sluzbenik.component';
import { ObavestenjeStavkaSluzbenikComponent } from './components/obavestenje-stavka-sluzbenik/obavestenje-stavka-sluzbenik.component';
import { ObavestenjeStavkaKorisnikComponent } from './components/obavestenje-stavka-korisnik/obavestenje-stavka-korisnik.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    NavigationBarComponent,
    HomeComponent,
    ListaZahtevaComponent,
    ZahtevComponent,
    DodajZahtevComponent,
    SviZahteviComponent,
    ZahtevSluzbenikComponent,
    ObavestenjaKorisnikComponent,
    ObavestenjaSluzbenikComponent,
    ObavestenjeStavkaSluzbenikComponent,
    ObavestenjeStavkaKorisnikComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSelectModule,MatGridListModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatDialogModule,
    CommonModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
