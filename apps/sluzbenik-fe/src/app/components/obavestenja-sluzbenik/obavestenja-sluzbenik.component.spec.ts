import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObavestenjaSluzbenikComponent } from './obavestenja-sluzbenik.component';

describe('ObavestenjaSluzbenikComponent', () => {
  let component: ObavestenjaSluzbenikComponent;
  let fixture: ComponentFixture<ObavestenjaSluzbenikComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ObavestenjaSluzbenikComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ObavestenjaSluzbenikComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
