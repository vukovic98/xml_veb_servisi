import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajZahtevComponent } from './dodaj-zahtev.component';

describe('DodajZahtevComponent', () => {
  let component: DodajZahtevComponent;
  let fixture: ComponentFixture<DodajZahtevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DodajZahtevComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
