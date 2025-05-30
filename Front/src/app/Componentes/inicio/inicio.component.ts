import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { WeatherService } from '../../Servicios/WeatherService';
import { NoticiasService } from '../../Servicios/noticias.service';
import { ChatComponent } from "../../componentes-reutilizables/chat/chat.component";

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterModule, CommonModule, HttpClientModule],
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  forecast: any[] = [];
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

  // ✅ AGREGAR ESTA PARTE
  this.noticiasService.getNoticias().subscribe({
    next: (data) => {
      this.noticias = data.articles; // Asegurate que sea "articles"
    },
    error: (err) => {
      console.error('Error al obtener las noticias:', err);
    }
  });
}

}
