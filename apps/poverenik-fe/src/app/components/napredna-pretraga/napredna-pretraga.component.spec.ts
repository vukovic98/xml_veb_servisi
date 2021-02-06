import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NaprednaPretragaComponent } from './napredna-pretraga.component';

describe('NaprednaPretragaComponent', () => {
  let component: NaprednaPretragaComponent;
  let fixture: ComponentFixture<NaprednaPretragaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NaprednaPretragaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NaprednaPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
