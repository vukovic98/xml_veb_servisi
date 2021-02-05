import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IzvestajiComponent } from './izvestaji.component';

describe('IzvestajiComponent', () => {
  let component: IzvestajiComponent;
  let fixture: ComponentFixture<IzvestajiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IzvestajiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IzvestajiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
