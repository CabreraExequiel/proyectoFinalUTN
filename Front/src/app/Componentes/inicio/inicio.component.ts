import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { WeatherService } from '../../Servicios/WeatherService';
import { NoticiasService } from '../../Servicios/noticias.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterModule, CommonModule, HttpClientModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  noticias: any[] = [];
  clima: any = null;
  ciudad: string = '';

  // Declaración correcta de la propiedad con ViewChild para acceder al carrusel en el template
  @ViewChild('carousel', { static: false }) carousel!: ElementRef;

  constructor(
    private weatherService: WeatherService, 
    private noticiasService: NoticiasService
  ) {}

  scrollNoticias(direction: number) {
    const el = this.carousel.nativeElement;
    const scrollAmount = 320;
    el.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
  }

  ngOnInit(): void {
    // Obtener clima
    this.weatherService.getWeather('Concordia,AR').subscribe({
      next: (data) => {
        this.clima = data;
      },
      error: (err) => {
        console.error('Error al obtener el clima:', err);
      }
    });

    // Obtener noticias
    this.noticiasService.getNoticias().subscribe({
      next: (data) => {
        this.noticias = data.articles;
      },
      error: (err) => {
        console.error('Error al obtener noticias', err);
      }
    });
  }
}
