import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { SignupComponent } from './components/signup/signup.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CommonModule} from '@angular/common';

import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from "@angular/material/list";
import { MatExpansionModule } from "@angular/material/expansion";
import { MatCardModule } from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import { ZalbeCutanjeComponent } from './components/zalbe-cutanje-lista/zalbe-cutanje.component';
import { ZalbaCutanjeComponent } from './components/zalba-cutanje/zalba-cutanje.component';
import { ZalbeComponent } from './components/zalbe/zalbe.component';
import { PodnosenjeZalbeCutanjeComponent } from './components/podnosenje-zalbe-cutanje/podnosenje-zalbe-cutanje.component';
import { ZalbaNaOdlukuComponent } from './components/zalba-na-odluku/zalba-na-odluku.component';
import { ResenjeComponent } from './components/resenje/resenje.component';
import { ResenjaComponent } from './components/resenja/resenja.component';
import { DatePipe } from '@angular/common'
import { PretragaComponent } from './components/pretraga/pretraga.component';
import { PodnosenjeZalbeNaOdlukuComponent } from './components/podnosenje-zalbe-na-odluku/podnosenje-zalbe-na-odluku.component';
import { DodajResenjeComponent } from './components/dodaj-resenje/dodaj-resenje.component';
import { NaprednaPretragaComponent } from './components/napredna-pretraga/napredna-pretraga.component';
import { IzvestajiComponent } from './components/izvestaji/izvestaji.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    HomepageComponent,
    NavigationBarComponent,
    ZalbeCutanjeComponent,
    ZalbaCutanjeComponent,
    ZalbeComponent,
    PodnosenjeZalbeCutanjeComponent,
    ZalbaNaOdlukuComponent,
    ResenjeComponent,
    ResenjaComponent,
    PodnosenjeZalbeCutanjeComponent,
    PretragaComponent,
    PodnosenjeZalbeNaOdlukuComponent,
    DodajResenjeComponent,
    NaprednaPretragaComponent,
    IzvestajiComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSelectModule,
    MatGridListModule,
    MatSlideToggleModule,
    MatButtonModule,
    MatDialogModule,
    CommonModule,
    MatMenuModule,
    MatListModule,
    MatExpansionModule,
    MatCardModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
