import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResenjaComponent } from './resenja.component';

describe('ResenjaComponent', () => {
  let component: ResenjaComponent;
  let fixture: ComponentFixture<ResenjaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResenjaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResenjaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
