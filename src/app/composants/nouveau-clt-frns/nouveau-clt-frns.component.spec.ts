import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauCltFrnsComponent } from './nouveau-clt-frns.component';

describe('NouveauCltFrnsComponent', () => {
  let component: NouveauCltFrnsComponent;
  let fixture: ComponentFixture<NouveauCltFrnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NouveauCltFrnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NouveauCltFrnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
