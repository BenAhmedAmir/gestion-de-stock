import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCmdCltComponent } from './page-cmd-clt.component';

describe('PageCmdCltComponent', () => {
  let component: PageCmdCltComponent;
  let fixture: ComponentFixture<PageCmdCltComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PageCmdCltComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCmdCltComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
