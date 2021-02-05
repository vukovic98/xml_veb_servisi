import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbenikNaprednaPretragaComponent } from './sluzbenik-napredna-pretraga.component';

describe('SluzbenikNaprednaPretragaComponent', () => {
  let component: SluzbenikNaprednaPretragaComponent;
  let fixture: ComponentFixture<SluzbenikNaprednaPretragaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SluzbenikNaprednaPretragaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SluzbenikNaprednaPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
