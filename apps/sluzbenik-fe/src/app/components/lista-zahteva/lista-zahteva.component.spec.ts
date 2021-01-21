import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaZahtevaComponent } from './lista-zahteva.component';

describe('ListaZahtevaComponent', () => {
  let component: ListaZahtevaComponent;
  let fixture: ComponentFixture<ListaZahtevaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaZahtevaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaZahtevaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
