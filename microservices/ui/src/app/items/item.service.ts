import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Item } from '../models/item';
import { Category } from '../models/category';

@Injectable({ providedIn: 'root' })
export class ItemService {
  private readonly baseUrl = `${environment.apiBase}`;

  constructor(private http: HttpClient) {}

  listItems(): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.baseUrl}/items`);
  }

  getItem(id: string | number): Observable<Item> {
    return this.http.get<Item>(`${this.baseUrl}/items/${id}`);
  }

  getItemsByCategory(categoryId: string): Observable<Item[]> {
      return this.http.get<Item[]>(`${this.baseUrl}/items?categoryId=${categoryId}`);
  }

  createItem(payload: { name: string, price: string | number, categoryId: string | number }): Observable<Item> {
      return this.http.post<Item>(`${this.baseUrl}/items`, payload);
  }

  updateItem(id: string, payload: { name: string, price: string | number, categoryId: string | number }): Observable<Item> {
      return this.http.put<Item>(`${this.baseUrl}/items/${id}`, payload);
  }

  deleteItem(id: string | number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/items/${id}`);
  }
}
