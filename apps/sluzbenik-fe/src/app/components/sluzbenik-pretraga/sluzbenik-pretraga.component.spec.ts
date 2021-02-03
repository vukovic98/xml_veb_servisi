import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbenikPretragaComponent } from './sluzbenik-pretraga.component';

describe('SluzbenikPretragaComponent', () => {
  let component: SluzbenikPretragaComponent;
  let fixture: ComponentFixture<SluzbenikPretragaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SluzbenikPretragaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SluzbenikPretragaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
