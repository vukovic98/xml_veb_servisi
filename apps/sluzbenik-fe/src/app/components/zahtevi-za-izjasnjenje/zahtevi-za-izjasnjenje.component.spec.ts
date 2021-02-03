import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahteviZaIzjasnjenjeComponent } from './zahtevi-za-izjasnjenje.component';

describe('ZahteviZaIzjasnjenjeComponent', () => {
  let component: ZahteviZaIzjasnjenjeComponent;
  let fixture: ComponentFixture<ZahteviZaIzjasnjenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahteviZaIzjasnjenjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahteviZaIzjasnjenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
