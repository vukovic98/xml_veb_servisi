import { TestBed } from '@angular/core/testing';

import { ZalbaNaOdlukuService } from './zalba-na-odluku.service';

describe('ZalbaNaOdlukuService', () => {
  let service: ZalbaNaOdlukuService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZalbaNaOdlukuService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
