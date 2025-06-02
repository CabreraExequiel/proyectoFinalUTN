import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';

interface Sala {
  id_sala: number;
  nombre_sala: string;
  deporte: string;
  descripcion: string;
  cantidad_integrantes: number;
  limite_integrantes: number;
  ubicacion: string;
  horario?: string; // Agregado para mostrar el horario
}

@Component({
  selector: 'app-football',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './football.component.html',
  styleUrl: './football.component.css'
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
      
      // Filtrar solo salas de football (fútbol)
      this.salas = response.filter(sala => 
        sala.deporte.toLowerCase() === 'football' || 
        sala.deporte.toLowerCase() === 'fútbol'
      );
      this.isLoading = false;
    } catch (err) {
      console.error('Error al obtener salas de fútbol:', err);
      this.error = 'Error al cargar las salas de fútbol. Intenta nuevamente más tarde.';
      this.isLoading = false;
    }
  }

  // Función para formatear el horario (si está disponible)
  formatHorario(horario: string | undefined): string {
    if (!horario) return '';
    const [hours, minutes] = horario.split(':');
    return `A LAS ${hours}:${minutes} HS`;
  }
}