import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZalbeCutanjeComponent } from './zalbe-cutanje.component';

describe('ZalbeCutanjeComponent', () => {
  let component: ZalbeCutanjeComponent;
  let fixture: ComponentFixture<ZalbeCutanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZalbeCutanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZalbeCutanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
