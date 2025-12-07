import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-category-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './category-add.component.html',
  styleUrls: ['./category-add.component.scss'],
})
export class CategoryAddComponent {
  submitting = false;
  error?: string;

  form: FormGroup;

  constructor(private fb: FormBuilder, private service: CategoryService, private router: Router) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
    });
  }

  get name() {
    return this.form.get('name');
  }

  submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.submitting = true;
    this.error = undefined;

    this.service.createCategory({ name: this.form.value.name! }).subscribe({
      next: () => {
        // Navigate back to categories list
        this.router.navigate(['/categories']);
      },
      error: (err) => {
        console.error('create category error', err);
        this.error = 'Failed to create category.';
        this.submitting = false;
      },
    });
  }
}
