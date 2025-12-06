import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Category } from '../models/category';

@Injectable({ providedIn: 'root' })
export class CategoryService {
  private readonly baseUrl = `${environment.apiBase}/categories`;

  constructor(private http: HttpClient) {}

  listCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl);
  }

  createCategory(payload: { name: string }): Observable<Category> {
    return this.http.post<Category>(this.baseUrl, payload);
  }

  deleteCategory(id: string | number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
