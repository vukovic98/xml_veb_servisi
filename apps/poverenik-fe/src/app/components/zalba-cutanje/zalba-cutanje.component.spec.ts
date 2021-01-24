import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZalbaCutanjeComponent } from './zalba-cutanje.component';

describe('ZalbaCutanjeComponent', () => {
  let component: ZalbaCutanjeComponent;
  let fixture: ComponentFixture<ZalbaCutanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZalbaCutanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZalbaCutanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
