import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoryService } from '../category.service';

@Component({
  selector: 'app-category-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './category-edit.component.html',
  styleUrls: ['./category-edit.component.scss'],
})
export class CategoryEditComponent {
  loading = true;
  submitting = false;
  error?: string;
  id!: string;

  form: FormGroup;

  constructor(private fb: FormBuilder, private service: CategoryService, private router: Router, private route: ActivatedRoute) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
    });
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id')!;
    if (!this.id) {
      this.error = 'Category id is missing.';
      this.loading = false;
      return;
    }

    this.service.getCategory(this.id).subscribe({
      next: (cat) => {
        this.form.patchValue({ name: cat.name ?? '' });
        this.loading = false;
      },
      error: (err) => {
        console.error('load category error', err);
        this.error = 'Failed to load category.';
        this.loading = false;
      },
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

    this.service.updateCategory(this.id, { name: this.form.value.name! }).subscribe({
      next: () => {
        this.router.navigate(['/categories']);
      },
      error: (err) => {
        console.error('update category error', err);
        this.error = 'Failed to update category.';
        this.submitting = false;
      },
    });
  }
}
