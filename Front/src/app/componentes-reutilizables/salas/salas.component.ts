import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatComponent } from "../chat/chat.component";
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-salas',
  standalone: true,
  imports: [CommonModule, ChatComponent],
  templateUrl: './salas.component.html',
  styleUrls: ['./salas.component.css']
})
export class SalasComponent implements OnInit {
  salaSeleccionada: any = null;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.http.get(`http://localhost:8080/sala/deporte/mostrar`, {
        headers: {
          'Authorization': `${localStorage.getItem('token')}`
        },
        withCredentials: true
      }).subscribe({
        next: (data) => this.salaSeleccionada = data,
        error: () => this.salaSeleccionada = { error: 'Sala no encontrada o error al cargar.' }
      });
    }
  }
}
