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
  selector: 'app-running',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './running.component.html',
  styleUrl: './running.component.css'
})
export class RunningComponent implements OnInit {
  salas: Sala[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  async ngOnInit() {
    await this.loadRunningRooms();
  }

  private async loadRunningRooms() {
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
          withCredentials: true
        })
      );
      
      this.salas = response.filter(sala => 
        sala.deporte.toLowerCase() === 'running' || 
        sala.deporte.toLowerCase() === 'correr'
      );
      
    } catch (err) {
      console.error('Error al obtener salas de running:', err);
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

  async unirseASala(event: Event, idSala: number) {
    event.preventDefault();
    event.stopPropagation();

    const token = localStorage.getItem('token');
    if (!token) {
      alert('Debes iniciar sesión para unirte a un grupo');
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
      console.error('Error al unirse al grupo:', err);
    }
  }
}