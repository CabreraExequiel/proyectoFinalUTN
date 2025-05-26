import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NoticiasService {
  private apiKey = '256e6d03700740b19524bf8f58251a98'; 
private url = 'https://newsapi.org/v2/top-headlines?country=us';


  constructor(private http: HttpClient) {}

  getNoticias(): Observable<any> {
    return this.http.get(`${this.url}&apiKey=${this.apiKey}`);
  }
}
