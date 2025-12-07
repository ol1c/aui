import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from '../models/item';

@Injectable({ providedIn: 'root' })
export class ItemService {
  private readonly baseUrl = `/api/items`;

  constructor(private http: HttpClient) {}

  listItems(): Observable<Item[]> {
    return this.http.get<Item[]>(this.baseUrl);
  }

  listItemsByCategory(categoryId: string | number): Observable<Item[]> {
    return this.http.get<Item[]>(`${this.baseUrl}?categoryId=${categoryId}`);
  }

  getItem(itemId: string | number): Observable<Item> {
    return this.http.get<Item>(`${this.baseUrl}/${itemId}`);
  }

  createItem(payload: { name: string; price: number; categoryId: string | number }): Observable<Item> {
    return this.http.post<Item>(this.baseUrl, payload);
  }

  updateItem(itemId: string | number, payload: { name: string; price: number; categoryId?: string | number }): Observable<Item> {
    return this.http.put<Item>(`${this.baseUrl}/${itemId}`, payload);
  }

  deleteItem(itemId: string | number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${itemId}`);
  }
}
