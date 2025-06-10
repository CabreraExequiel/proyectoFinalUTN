import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

interface Sala {
  id_sala: number;
  nombre_sala: string;
  descripcion: string;
  ubicacion: string;
  horario?: string;
  deporte: string;
  cantidad_integrantes: number;
  limite_integrantes: number;
}

@Component({
  selector: 'app-football',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './football.component.html',
  styleUrls: ['./football.component.css']
})
export class FootballComponent implements OnInit {
  salas: Sala[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(private http: HttpClient) {}

  async ngOnInit() {
    const token = localStorage.getItem('token');
    if (!token) {
      this.error = 'Debes iniciar sesión para ver las salas de fútbol';
      this.isLoading = false;
      return;
    }

    try {
      const response = await lastValueFrom(
        this.http.get<Sala[]>('http://localhost:8080/sala/deporte/mostrar', {
          headers: {
            'Authorization': `${localStorage.getItem('token')}`
          },
          withCredentials: true
        })
      );

      this.salas = response.filter(sala =>
        sala.deporte.toLowerCase() === 'football' ||
        sala.deporte.toLowerCase() === 'fútbol'
      );
      console.log('Salas filtradas:', this.salas);
      this.isLoading = false;
    } catch (err) {
      console.error('Error al obtener salas de fútbol:', err);
      this.error = 'Error al cargar las salas de fútbol. Intenta nuevamente más tarde.';
      this.isLoading = false;
    }
  }

  formatHorario(horario: string | undefined): string {
    if (!horario) return '';
    const [hours, minutes] = horario.split(':');
    return `A LAS ${hours}:${minutes} HS`;
  }

  async unirseASala(event: Event, idSala: number) {
    event.preventDefault(); // Prevenir navegación por routerLink
    event.stopPropagation(); // Detener propagación del evento

    const token = localStorage.getItem('token');
    if (!token) {
      alert('Debes iniciar sesión para unirte a una sala');
      return;
    }

    try {
      await lastValueFrom(
        this.http.post(
          `http://localhost:8080/sala/deporte/unirse?idSala=${idSala}`,
          {}, // cuerpo vacío
          {
            headers: {
              'Authorization': token
            },
            withCredentials: true
          }
        )
      );
      // No necesitas manejar la respuesta según lo indicado
    } catch (err) {
      console.error('Error al unirse a la sala:', err);
      // El manejo de errores se hará en la API según lo mencionado
    }
  }
}