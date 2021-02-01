import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZalbaNaOdlukuComponent } from './zalba-na-odluku.component';

describe('ZalbaNaOdlukuComponent', () => {
  let component: ZalbaNaOdlukuComponent;
  let fixture: ComponentFixture<ZalbaNaOdlukuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZalbaNaOdlukuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZalbaNaOdlukuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
