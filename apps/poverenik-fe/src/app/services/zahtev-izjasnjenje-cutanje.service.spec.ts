import { TestBed } from '@angular/core/testing';

import { ZahtevIzjasnjenjeCutanjeService } from './zahtev-izjasnjenje-cutanje.service';

describe('ZahtevIzjasnjenjeCutanjeService', () => {
  let service: ZahtevIzjasnjenjeCutanjeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZahtevIzjasnjenjeCutanjeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
