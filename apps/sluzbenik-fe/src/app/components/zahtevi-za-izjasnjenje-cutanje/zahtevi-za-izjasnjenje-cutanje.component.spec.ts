import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahteviZaIzjasnjenjeCutanjeComponent } from './zahtevi-za-izjasnjenje-cutanje.component';

describe('ZahteviZaIzjasnjenjeCutanjeComponent', () => {
  let component: ZahteviZaIzjasnjenjeCutanjeComponent;
  let fixture: ComponentFixture<ZahteviZaIzjasnjenjeCutanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahteviZaIzjasnjenjeCutanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahteviZaIzjasnjenjeCutanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
