import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, ActivatedRoute } from '@angular/router';
import { ItemService } from '../item.service';
import { Item } from '../../models/item';

@Component({
  selector: 'app-item-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './item-details.html',
  styleUrl: './item-details.scss',
})
export class ItemDetailsComponent implements OnInit {
  item?: Item;
  loading = false;
  error?: string;
  categoryId!: string;
  itemId!: string;

  constructor(
    private route: ActivatedRoute,
    private itemService: ItemService
  ) {}

  ngOnInit(): void {
    this.categoryId = this.route.snapshot.paramMap.get('categoryId')!;
    this.itemId = this.route.snapshot.paramMap.get('itemId')!;
    
    if (!this.categoryId || !this.itemId) {
      this.error = 'Category ID or Item ID is missing.';
      return;
    }
    this.fetchItemDetails();
  }

  fetchItemDetails(): void {
    this.loading = true;
    this.error = undefined;

    this.itemService.getItem(this.itemId).subscribe({
      next: (item) => {
        this.item = item;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to fetch item details.';
        console.error(err);
        this.loading = false;
      }
    });
  }
}
