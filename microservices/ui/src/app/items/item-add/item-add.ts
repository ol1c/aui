import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-item-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './item-add.html',
  styleUrl: './item-add.scss',
})
export class ItemAddComponent implements OnInit {
  submitting = false;
  error?: string;
  categoryId!: string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private itemService: ItemService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      price: [0, [Validators.required, Validators.min(0)]],
    });
  }

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('categoryId')!;
    if (!this.categoryId) {
      this.error = 'Category ID is missing.';
    }
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
      categoryId: this.categoryId,
    };

    this.itemService.createItem(payload).subscribe({
      next: () => {
        this.router.navigate(['/categories', this.categoryId]);
      },
      error: (err) => {
        console.error('create item error', err);
        this.error = 'Failed to create item.';
        this.submitting = false;
      },
    });
  }
}
