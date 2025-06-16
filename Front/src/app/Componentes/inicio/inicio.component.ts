import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { WeatherService } from '../../Servicios/WeatherService';
import { NoticiasService } from '../../Servicios/noticias.service';
import { ForoComponent } from "../foro/foro.component";
import { Router } from "@angular/router";

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterModule, CommonModule, HttpClientModule, ForoComponent],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  forecast: any[] = [];
  noticias: any[] = [];
  clima: any = null;
  ciudad: string = '';
  salas: any[] = []; // 👈 Añadido para almacenar las salas recomendadas

  @ViewChild('carousel', { static: false }) carousel!: ElementRef;

  constructor(
    private weatherService: WeatherService,
    private noticiasService: NoticiasService,
    private http: HttpClient, // 👈 Necesario para el fetch
    private router: Router
  ) {}

  scrollNoticias(direction: number) {
    const el = this.carousel.nativeElement;
    const scrollAmount = 320;
    el.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    // Obtener clima actual
    this.weatherService.getWeather('Concordia,AR').subscribe({
      next: (data) => {
        this.clima = data;
      },
      error: (err) => {
        console.error('Error al obtener el clima:', err);
      }
    });

    // Obtener pronóstico
    this.weatherService.getForecast('Concordia,AR').subscribe({
      next: (data) => {
        this.forecast = data.list.filter((item: any) =>
          item.dt_txt.includes('12:00:00')
        );
      },
      error: (err) => {
        console.error('Error al obtener el pronóstico:', err);
      }
    });

    // Obtener noticias
    this.noticiasService.getNoticias().subscribe({
      next: (data) => {
        this.noticias = data.articles;
      },
      error: (err) => {
        console.error('Error al obtener las noticias:', err);
      }
    });

    // ✅ Obtener salas recomendadas con token (sin Bearer) y credenciales
    const headers = new HttpHeaders().set('Authorization', token);
    this.http.get<any[]>('http://localhost:8080/alg/listas_recomendadas', {
      headers: headers,
      withCredentials: true
    }).subscribe({
      next: (data) => {
        this.salas = data;
      },
      error: (err) => {
        console.error('Error al obtener las salas recomendadas:', err);
      }
    });
  }

    unirseASala(idSala: number) {
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': token
    });

    const body = new URLSearchParams();
    body.set('idSala', idSala.toString());

    this.http.post('http://localhost:8080/sala/deporte/unirse', body.toString(), {
      headers: headers,
      withCredentials: true
    }).subscribe({
      next: () => {
        this.router.navigate(['/sala', idSala]);
      },
      error: (err) => {
        console.error('Error al unirse a la sala:', err);
        alert('No se pudo unir a la sala. Verifique su sesión.');
      }
    });
  }
}