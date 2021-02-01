import { TestBed } from '@angular/core/testing';

import { ResenjaService } from './resenja.service';

describe('ResenjaService', () => {
  let service: ResenjaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResenjaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
