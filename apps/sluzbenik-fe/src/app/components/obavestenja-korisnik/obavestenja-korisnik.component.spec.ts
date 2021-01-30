import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaKorisnikComponent } from './obavestenja-korisnik.component';

describe('ObavestenjaKorisnikComponent', () => {
  let component: ObavestenjaKorisnikComponent;
  let fixture: ComponentFixture<ObavestenjaKorisnikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObavestenjaKorisnikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObavestenjaKorisnikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
