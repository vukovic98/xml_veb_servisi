import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjeStavkaSluzbenikComponent } from './obavestenje-stavka-sluzbenik.component';

describe('ObavestenjeStavkaSluzbenikComponent', () => {
  let component: ObavestenjeStavkaSluzbenikComponent;
  let fixture: ComponentFixture<ObavestenjeStavkaSluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObavestenjeStavkaSluzbenikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObavestenjeStavkaSluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
