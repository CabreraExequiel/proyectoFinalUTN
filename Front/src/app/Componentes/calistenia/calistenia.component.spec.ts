import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CalisteniaComponent } from './calistenia.component';

describe('CalisteniaComponent', () => {
  let component: CalisteniaComponent;
  let fixture: ComponentFixture<CalisteniaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CalisteniaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CalisteniaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
