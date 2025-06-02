import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';
import { Router, RouterModule } from '@angular/router';

interface Sala {
  id_sala: number;
  nombre_sala: string;
  deporte: string;
  descripcion: string;
  cantidad_integrantes: number;
  limite_integrantes: number;
  ubicacion: string;
  horario?: string;
}

@Component({
  selector: 'app-basketball',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './basketball.component.html',
  styleUrl: './basketball.component.css'
})
export class BasketballComponent implements OnInit {
  salas: Sala[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  async ngOnInit() {
    await this.loadBasketballRooms();
  }

  private async loadBasketballRooms() {
    try {
      const token = localStorage.getItem('token');
      
      if (!token) {
        throw new Error('No hay token de autenticación');
      }

      const response = await lastValueFrom(
        this.http.get<Sala[]>('http://localhost:8080/sala/deporte/mostrar', {
          headers: {
            'Authorization': token // Sin 'Bearer' como prefieres
          },
          withCredentials: true
        })
      );
      
      // Filtrar solo salas de basketball
      this.salas = response.filter(sala => 
        sala.deporte.toLowerCase() === 'basketball' || 
        sala.deporte.toLowerCase() === 'baloncesto'
      );
      
    } catch (err) {
      console.error('Error al obtener salas de basketball:', err);
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
}