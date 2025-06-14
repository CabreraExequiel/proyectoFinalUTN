import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatComponent } from "../chat/chat.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-salas',
  standalone: true,
  imports: [CommonModule, ChatComponent],
  templateUrl: './salas.component.html',
  styleUrls: ['./salas.component.css']
})
export class SalasComponent implements OnInit {
  salaSeleccionada: any = null;
  mapaUrl: SafeResourceUrl | null = null;
  salaAnterior: any = null;


  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      console.log('ID:', id);

      if (id) {
        this.http.get(`http://localhost:8080/sala/deporte/${id}`, {
          headers: {
            'Authorization': `${localStorage.getItem('token')}`
          },
          withCredentials: true
        }).subscribe({
          next: (data: any) => {
        if (this.salaSeleccionada) {
          this.salaAnterior = this.salaSeleccionada;
        }
        this.salaSeleccionada = data;
        if (data.latitud && data.longitud) {
          this.setMapaUrl(data.latitud, data.longitud);
        } else {
          this.mapaUrl = null;
        }
      },

          error: (err) => {
            console.error('Error al obtener la sala:', err);
            this.salaSeleccionada = { error: 'Sala no encontrada o error al cargar.' };
          }
        });
      }
    });
  }
  

  setMapaUrl(lat: number, lng: number) {
    const url = `https://www.google.com/maps?q=${lat},${lng}&output=embed`;
    this.mapaUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}
