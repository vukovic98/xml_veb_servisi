import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PodnosenjeZalbeNaOdlukuComponent } from './podnosenje-zalbe-na-odluku.component';

describe('PodnosenjeZalbeNaOdlukuComponent', () => {
  let component: PodnosenjeZalbeNaOdlukuComponent;
  let fixture: ComponentFixture<PodnosenjeZalbeNaOdlukuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PodnosenjeZalbeNaOdlukuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PodnosenjeZalbeNaOdlukuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
