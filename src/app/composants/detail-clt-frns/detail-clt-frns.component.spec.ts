import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCltFrnsComponent } from './detail-clt-frns.component';

describe('DetailCltFrnsComponent', () => {
  let component: DetailCltFrnsComponent;
  let fixture: ComponentFixture<DetailCltFrnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailCltFrnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCltFrnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
