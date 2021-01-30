import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevSluzbenikComponent } from './zahtev-sluzbenik.component';

describe('ZahtevSluzbenikComponent', () => {
  let component: ZahtevSluzbenikComponent;
  let fixture: ComponentFixture<ZahtevSluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahtevSluzbenikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahtevSluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
