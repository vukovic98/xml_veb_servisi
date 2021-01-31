import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjeStavkaKorisnikComponent } from './obavestenje-stavka-korisnik.component';

describe('ObavestenjeStavkaKorisnikComponent', () => {
  let component: ObavestenjeStavkaKorisnikComponent;
  let fixture: ComponentFixture<ObavestenjeStavkaKorisnikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObavestenjeStavkaKorisnikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObavestenjeStavkaKorisnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
