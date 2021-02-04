import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SviDokumentiPretrageComponent } from './svi-dokumenti-pretrage.component';

describe('SviDokumentiPretrageComponent', () => {
  let component: SviDokumentiPretrageComponent;
  let fixture: ComponentFixture<SviDokumentiPretrageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SviDokumentiPretrageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SviDokumentiPretrageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
