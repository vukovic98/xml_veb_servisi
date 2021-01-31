import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResenjeComponent } from './resenje.component';

describe('ResenjeComponent', () => {
  let component: ResenjeComponent;
  let fixture: ComponentFixture<ResenjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResenjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResenjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
