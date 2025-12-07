import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { CategoryService } from '../category.service';
import { ItemService } from '../../items/item.service';
import { Category } from '../../models/category';
import { Item } from '../../models/item';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-details.html',
  styleUrl: './category-details.scss',
})
export class CategoryDetailsComponent implements OnInit {
  category?: Category;
  items: Item[] = [];
  loading = false;
  error?: string;
  categoryId!: string;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private itemService: ItemService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id')!;
    if (!this.categoryId) {
      this.error = 'Category ID is missing.';
      return;
    }
    this.fetchCategoryDetails();
  }

  fetchCategoryDetails(): void {
    this.loading = true;
    this.error = undefined;

    this.categoryService.getCategory(this.categoryId).subscribe({
      next: (category) => {
        this.category = category;
        this.fetchItems();
      },
      error: (err) => {
        this.error = 'Failed to fetch category details.';
        console.error(err);
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  fetchItems(): void {
    this.itemService.listItemsByCategory(this.categoryId).subscribe({
      next: (items) => {
        this.items = items;
        this.loading = false;
        this.cdr.markForCheck();
      },
      error: (err) => {
        this.error = 'Failed to fetch items.';
        console.error(err);
        this.loading = false;
        this.cdr.markForCheck();
      }
    });
  }

  removeItem(item: Item): void {
    const confirmed = window.confirm(
      `Are you sure you want to delete the item "${item.name}"?`
    );
    if (!confirmed) return;

    this.itemService.deleteItem(item.id).subscribe({
      next: () => {
        this.items = this.items.filter((i) => i.id !== item.id);
      },
      error: (err) => {
        this.error = 'Failed to delete item.';
        console.error(err);
      }
    });
  }
}
