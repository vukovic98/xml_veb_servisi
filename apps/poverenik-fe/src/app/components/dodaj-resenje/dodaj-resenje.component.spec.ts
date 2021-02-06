import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DodajResenjeComponent } from './dodaj-resenje.component';

describe('DodajResenjeComponent', () => {
  let component: DodajResenjeComponent;
  let fixture: ComponentFixture<DodajResenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DodajResenjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DodajResenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
