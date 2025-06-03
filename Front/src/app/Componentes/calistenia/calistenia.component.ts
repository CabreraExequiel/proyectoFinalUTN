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

  constructor(private http: HttpClient) {}

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
}