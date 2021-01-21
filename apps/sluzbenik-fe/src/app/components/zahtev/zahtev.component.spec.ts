import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZahtevComponent } from './zahtev.component';

describe('ZahtevComponent', () => {
  let component: ZahtevComponent;
  let fixture: ComponentFixture<ZahtevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ZahtevComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
