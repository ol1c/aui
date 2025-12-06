import { Routes } from '@angular/router';
import { CategoryListComponent } from './categories/category-list/category-list.component';
import { CategoryAddComponent } from './categories/category-add/category-add.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
import { CategoryDetailsComponent } from './categories/category-details/category-details';
import { ItemAddComponent } from './items/item-add/item-add';
import { ItemEditComponent } from './items/item-edit/item-edit';
import { ItemDetailsComponent } from './items/item-details/item-details';

export const routes: Routes = [
  { path: '', redirectTo: '/categories', pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'categories/add', component: CategoryAddComponent },
  { path: 'categories/:id/edit', component: CategoryEditComponent },
  { path: 'categories/:id', component: CategoryDetailsComponent },
  { path: 'categories/:categoryId/items/add', component: ItemAddComponent },
  { path: 'categories/:categoryId/items/:itemId/edit', component: ItemEditComponent },
  { path: 'categories/:categoryId/items/:itemId', component: ItemDetailsComponent }
];
