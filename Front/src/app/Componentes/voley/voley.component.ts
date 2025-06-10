import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';
import { Router, RouterModule } from '@angular/router';

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
  selector: 'app-voley',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './voley.component.html',
  styleUrl: './voley.component.css'
})
export class VoleyComponent implements OnInit {
  salas: Sala[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  async ngOnInit() {
    await this.loadVolleyballRooms();
  }

  private async loadVolleyballRooms() {
    try {
      const token = localStorage.getItem('token');
      
      if (!token) {
        throw new Error('No hay token de autenticación');
      }

      const response = await lastValueFrom(
        this.http.get<Sala[]>('http://localhost:8080/sala/deporte/mostrar', {
          headers: {
            'Authorization': token
          },
          withCredentials: true,
        })
      );
      
      // Filtrar solo salas de volleyball
      this.salas = response.filter(sala => 
        sala.deporte.toLowerCase() === 'volleyball' || 
        sala.deporte.toLowerCase() === 'vóley' ||
        sala.deporte.toLowerCase() === 'voleibol'
      );
      
    } catch (err) {
      console.error('Error al obtener salas de volleyball:', err);
      this.error = 'Error al cargar las salas. Intenta nuevamente más tarde.';
      
      if (err instanceof Error && err.message.includes('token')) {
        this.router.navigate(['/login']);
      }
    } finally {
      this.isLoading = false;
    }
  }

  formatHorario(horario: string | undefined): string {
    if (!horario) return '';
    const [hours, minutes] = horario.split(':');
    return `A LAS ${hours}:${minutes} HS`;
  }

  // Propiedad computada para compatibilidad con el template actual
  get cards() {
    return this.salas.map(sala => ({
      id_sala: sala.id_sala,
      imagen: 'assets/voley-card.jpg',
      titulo: `${sala.nombre_sala.toUpperCase()} ${this.formatHorario(sala.horario)}`,
      descripcion: `${sala.descripcion} - ${sala.ubicacion}`,
      vacantes: `${sala.cantidad_integrantes}/${sala.limite_integrantes}`
    }));
  }

  async unirseASala(event: Event, idSala: number) {
    event.preventDefault();
    event.stopPropagation();

    const token = localStorage.getItem('token');
    if (!token) {
      alert('Debes iniciar sesión para unirte a un equipo');
      this.router.navigate(['/login']);
      return;
    }

    try {
      await lastValueFrom(
        this.http.post(
          `http://localhost:8080/sala/deporte/unirse?idSala=${idSala}`,
          {},
          {
            headers: {
              'Authorization': token
            },
            withCredentials: true
          }
        )
      );
    } catch (err) {
      console.error('Error al unirse al equipo:', err);
    }
  }
}