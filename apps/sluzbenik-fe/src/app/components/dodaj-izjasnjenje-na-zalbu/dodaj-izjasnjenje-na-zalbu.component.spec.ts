import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajIzjasnjenjeNaZalbuComponent } from './dodaj-izjasnjenje-na-zalbu.component';

describe('DodajIzjasnjenjeNaZalbuComponent', () => {
  let component: DodajIzjasnjenjeNaZalbuComponent;
  let fixture: ComponentFixture<DodajIzjasnjenjeNaZalbuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DodajIzjasnjenjeNaZalbuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajIzjasnjenjeNaZalbuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
