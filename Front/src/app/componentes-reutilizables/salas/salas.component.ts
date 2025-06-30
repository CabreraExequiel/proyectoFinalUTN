import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatComponent } from "../chat/chat.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-salas',
  standalone: true,
  imports: [CommonModule, ChatComponent],
  templateUrl: './salas.component.html',
  styleUrls: ['./salas.component.css']
})
export class SalasComponent implements OnInit {
  salaDeporte: any = null;
  integrantes: string[] = [];
  esCreador: boolean = false;
  mapaUrl: SafeResourceUrl | null = null;
  salaAnterior: any = null;

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private sanitizer: DomSanitizer,
    private router: Router
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');

      if (id) {
        this.http.get(`http://localhost:8080/sala/deporte/${id}`, {
          headers: {
            'Authorization': `${localStorage.getItem('token')}`
          },
          withCredentials: true
        }).subscribe({
          next: (data: any) => {
            if (this.salaDeporte) {
              this.salaAnterior = this.salaDeporte;
            }

            this.salaDeporte = data.salaDeporte;
            this.integrantes = data.integrantes;
            this.esCreador = data.esCreador;

            if (this.salaDeporte.latitud && this.salaDeporte.longitud) {
              this.setMapaUrl(this.salaDeporte.latitud, this.salaDeporte.longitud);
            } else {
              this.mapaUrl = null;
            }
          },
          error: (err) => {
            console.error('Error al obtener la sala:', err);
            this.salaDeporte = { error: 'Sala no encontrada o error al cargar.' };
          }
        });
      }
    });
  }

  setMapaUrl(lat: number, lng: number) {
    console.log('Coordenadas recibidas para el mapa:', lat, lng); // 👈
    const url = `https://www.google.com/maps?q=${lat},${lng}&output=embed`;
    this.mapaUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  eliminarSala(id: string | null) {
    if (!id) return;
    this.http.delete(`http://localhost:8080/sala/deporte/eliminar?id=${id}`, {
      headers: {
        'Authorization': `${localStorage.getItem('token')}`
      },
      withCredentials: true,
      responseType: 'text'
    }).subscribe({
      next: () => {
        alert("Sala eliminada correctamente.");
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error("Error al eliminar la sala", err);
        alert("No se pudo eliminar la sala.");
      }
    });
  }

  salirDelGrupo(id: string | null) {
    if (!id) return;
    this.http.post(`http://localhost:8080/sala/deporte/salir_grupo?id=${id}`, {}, {
      headers: {
        'Authorization': `${localStorage.getItem('token')}`
      },
      withCredentials: true,
      responseType: 'text'
    }).subscribe({
      next: () => {
        alert("Has salido del grupo.");
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error("Error al salir del grupo", err);
        alert("No se pudo salir del grupo.");
      }
    });
  }
}


