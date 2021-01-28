import { TestBed } from '@angular/core/testing';

import { ZalbeCutanjeService } from './zalbe-cutanje.service';

describe('ZalbeCutanjeService', () => {
  let service: ZalbeCutanjeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ZalbeCutanjeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
