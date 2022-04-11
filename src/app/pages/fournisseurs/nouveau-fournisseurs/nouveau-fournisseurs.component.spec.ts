import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NouveauFournisseursComponent } from './nouveau-fournisseurs.component';

describe('NouveauFournisseursComponent', () => {
  let component: NouveauFournisseursComponent;
  let fixture: ComponentFixture<NouveauFournisseursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NouveauFournisseursComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NouveauFournisseursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
