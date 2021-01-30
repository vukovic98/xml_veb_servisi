import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PodnosenjeZalbeCutanjeComponent } from './podnosenje-zalbe-cutanje.component';

describe('PodnosenjeZalbeCutanjeComponent', () => {
  let component: PodnosenjeZalbeCutanjeComponent;
  let fixture: ComponentFixture<PodnosenjeZalbeCutanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PodnosenjeZalbeCutanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PodnosenjeZalbeCutanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
