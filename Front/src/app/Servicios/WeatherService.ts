import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WeatherService {
  private apiKey = '60d8442bb3a1d31a502801f2abae0015';
  private baseUrl = 'https://api.openweathermap.org/data/2.5/weather';
  private forecastUrl = 'https://api.openweathermap.org/data/2.5/forecast';

  constructor(private http: HttpClient) {}

  getWeather(city: string) {
    const url = `${this.baseUrl}?q=${city}&appid=${this.apiKey}&units=metric&lang=es`;
    return this.http.get(url);
  }
   getForecast(city: string): Observable<any> {
    return this.http.get(`${this.forecastUrl}?q=${city}&units=metric&lang=es&appid=${this.apiKey}`);
  }
}
