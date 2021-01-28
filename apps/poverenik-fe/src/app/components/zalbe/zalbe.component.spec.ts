import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZalbeComponent } from './zalbe.component';

describe('ZalbeComponent', () => {
  let component: ZalbeComponent;
  let fixture: ComponentFixture<ZalbeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZalbeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZalbeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
