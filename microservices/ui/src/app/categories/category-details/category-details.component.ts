import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ActivatedRoute, Router } from '@angular/router';
import { finalize } from 'rxjs/operators';
import { CategoryService } from '../category.service';
import { ItemService } from '../../items/item.service';
import { Category } from '../../models/category';
import { Item } from '../../models/item';

@Component({
  selector: 'app-category-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './category-details.component.html',
  styleUrl: './category-details.component.scss'
})
export class CategoryDetailsComponent implements OnInit {
  category: Category | null = null;
  items: Item[] = [];
  loading = false;
  loadingItems = false;
  error?: string;
  categoryId: string = "";

  constructor(
    private categoryService: CategoryService,
    private itemService: ItemService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('id') || '';
    this.loadCategory();
    this.loadItems();
  }

  loadCategory(): void {
    this.loading = true;
    this.categoryService.getCategory(this.categoryId).subscribe({
      next: (cat) => {
        this.category = cat;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load category';
        this.loading = false;
        console.error(err);
      }
    });
  }

  loadItems(): void {
    this.loadingItems = true;
    this.itemService.getItemsByCategory(this.categoryId).subscribe({
      next: (items) => {
        this.items = items || [];
        this.loadingItems = false;
      },
      error: (err) => {
        if (err.status !== 204) {
          console.error(err);
        }
        this.items = [];
        this.loadingItems = false;
      }
    });
  }

  deleteItem(id: string | number, name: string): void {
    if (confirm(`Are you sure you want to delete "${name}"?`)) {
      this.itemService.deleteItem(id).subscribe({
        next: () => {
          this.loadItems();
        },
        error: (err) => {
          this.error = 'Failed to delete item';
          console.error(err);
        }
      });
    }
  }
}
