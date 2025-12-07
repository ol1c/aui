import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { CategoryService } from '../category.service';
import { Category } from '../../models/category';

@Component({
  selector: 'app-category-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.scss'],
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];
  loading = false;
  error?: string;

  constructor(private categoryService: CategoryService, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.fetchCategories();
  }

  fetchCategories(): void {
    this.loading = true;
    this.error = undefined;
    this.categoryService.listCategories().subscribe({
      next: (cats) => {
        this.categories = cats;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.error = 'Failed to fetch categories.';
        console.error(err);
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  removeCategory(category: Category): void {
    const confirmed = window.confirm(
      `Are you sure you want to delete the category "${category.name}"?`
    );
    if (!confirmed) return;

    this.categoryService.deleteCategory(category.id).subscribe({
      next: () => {
        this.categories = this.categories.filter((c) => c.id !== category.id);
      },
      error: (err) => {
        this.error = 'Failed to delete category.';
        console.error(err);
      },
    });
  }
}
