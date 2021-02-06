import { TestBed } from '@angular/core/testing';

import { ZahtevizjasnjenjeOdlukaService } from './zahtevizjasnjenje-odluka.service';

describe('ZahtevizjasnjenjeOdlukaService', () => {
  let service: ZahtevizjasnjenjeOdlukaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZahtevizjasnjenjeOdlukaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
