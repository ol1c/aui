import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-item-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './item-edit.html',
  styleUrl: './item-edit.scss',
})
export class ItemEditComponent implements OnInit {
  loading = true;
  submitting = false;
  error?: string;
  categoryId!: string;
  itemId!: string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private itemService: ItemService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      price: [0, [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('categoryId')!;
    this.itemId = this.route.snapshot.paramMap.get('itemId')!;

    if (!this.categoryId || !this.itemId) {
      this.error = 'Category ID or Item ID is missing.';
      this.loading = false;
      this.cdr.markForCheck();
      return;
    }

    this.itemService.getItem(this.itemId).subscribe({
      next: (item) => {
        this.form.patchValue({
          name: item.name ?? '',
          price: item.price ?? 0,
        });
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        console.error('load item error', err);
        this.error = 'Failed to load item.';
        this.loading = false;
        this.cdr.markForCheck();
      },
    });
  }

  get name() {
    return this.form.get('name');
  }

  get price() {
    return this.form.get('price');
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitting = true;
    this.error = undefined;

    const payload = {
      name: this.form.value.name!,
      price: Number(this.form.value.price!),
    };

    this.itemService.updateItem(this.itemId, payload).subscribe({
      next: () => {
        this.router.navigate(['/categories', this.categoryId]);
      },
      error: (err) => {
        console.error('update item error', err);
        this.error = 'Failed to update item.';
        this.submitting = false;
      },
    });
  }
}
