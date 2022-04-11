import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCmdFrnsComponent } from './page-cmd-frns.component';

describe('PageCmdFrnsComponent', () => {
  let component: PageCmdFrnsComponent;
  let fixture: ComponentFixture<PageCmdFrnsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageCmdFrnsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCmdFrnsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
