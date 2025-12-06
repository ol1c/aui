import { Routes } from '@angular/router';
import { CategoryListComponent } from './categories/category-list/category-list.component';
import { CategoryAddComponent } from './categories/category-add/category-add.component';
// import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
// import { CategoryDetailsComponent } from './categories/category-details/category-details.component';
// import { ItemAddComponent } from './items/item-add/item-add.component';
// import { ItemEditComponent } from './items/item-edit/item-edit.component';
// import { ItemDetailsComponent } from './items/item-details/item-details.component';

export const routes: Routes = [
  { path: '', redirectTo: '/categories', pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'categories/add', component: CategoryAddComponent }
  ];
