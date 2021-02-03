import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahteviZaIzjasnjenjeOdlukaComponent } from './zahtevi-za-izjasnjenje-odluka.component';

describe('ZahteviZaIzjasnjenjeOdlukaComponent', () => {
  let component: ZahteviZaIzjasnjenjeOdlukaComponent;
  let fixture: ComponentFixture<ZahteviZaIzjasnjenjeOdlukaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahteviZaIzjasnjenjeOdlukaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahteviZaIzjasnjenjeOdlukaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
