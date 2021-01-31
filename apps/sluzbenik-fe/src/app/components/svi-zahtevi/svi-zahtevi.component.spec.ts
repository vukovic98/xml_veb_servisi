import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SviZahteviComponent } from './svi-zahtevi.component';

describe('SviZahteviComponent', () => {
  let component: SviZahteviComponent;
  let fixture: ComponentFixture<SviZahteviComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SviZahteviComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SviZahteviComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
