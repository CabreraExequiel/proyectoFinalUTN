import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
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
  selector: 'app-calistenia',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './calistenia.component.html',
  styleUrl: './calistenia.component.css'
})
export class CalisteniaComponent implements OnInit {
  salas: Sala[] = [];
  isLoading = true;
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  async ngOnInit() {
    try {
      const response = await lastValueFrom(
        this.http.get<Sala[]>('http://localhost:8080/sala/deporte/mostrar',
          {headers: {'Authorization': `${localStorage.getItem('token')}`},withCredentials: true})
      );
      
      // Filtrar solo salas de calistenia
      this.salas = response.filter(sala => sala.deporte.toLowerCase() === 'calistenia');
      this.isLoading = false;
    } catch (err) {
      console.error('Error al obtener salas:', err);
      this.error = 'Error al cargar las salas. Intenta nuevamente más tarde.';
      this.isLoading = false;
    }
  }

  async unirseASala(event: Event, idSala: number) {
    event.preventDefault();
    event.stopPropagation();

    const token = localStorage.getItem('token');
    if (!token) {
      alert('Debes iniciar sesión para unirte a una sala');
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
      console.error('Error al unirse a la sala:', err);
    }
  }
}