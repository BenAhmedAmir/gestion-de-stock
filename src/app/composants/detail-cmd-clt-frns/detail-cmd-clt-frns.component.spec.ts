import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailCmdCltFrnsComponent } from './detail-cmd-clt-frns.component';

describe('DetailCmdCltFrnsComponent', () => {
  let component: DetailCmdCltFrnsComponent;
  let fixture: ComponentFixture<DetailCmdCltFrnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailCmdCltFrnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailCmdCltFrnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
