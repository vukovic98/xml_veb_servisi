import { TestBed } from '@angular/core/testing';

import { NaprednaPretragaService } from './napredna-pretraga.service';

describe('NaprednaPretragaService', () => {
  let service: NaprednaPretragaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NaprednaPretragaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
