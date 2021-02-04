import { TestBed } from '@angular/core/testing';

import { GeneratorFajlovaService } from './generator-fajlova.service';

describe('GeneratorFajlovaService', () => {
  let service: GeneratorFajlovaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneratorFajlovaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
