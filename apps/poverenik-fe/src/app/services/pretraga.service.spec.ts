import { TestBed } from '@angular/core/testing';

import { PretragaService } from './pretraga.service';

describe('PretragaService', () => {
  let service: PretragaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PretragaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
