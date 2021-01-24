import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZalbaCutanjePregledComponent } from './zalba-cutanje-pregled.component';

describe('ZalbaCutanjePregledComponent', () => {
  let component: ZalbaCutanjePregledComponent;
  let fixture: ComponentFixture<ZalbaCutanjePregledComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZalbaCutanjePregledComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZalbaCutanjePregledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
