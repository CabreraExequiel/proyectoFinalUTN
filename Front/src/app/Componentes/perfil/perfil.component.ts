import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {
  nombre = 'Juan Pérez';
  biografia = 'Desarrollador web, amante de la tecnología y el café ☕';
  publicaciones = [
    { contenido: '¡Hoy fue un gran día!', fecha: '2025-05-29' },
    { contenido: 'Trabajando en un nuevo proyecto Angular 🚀', fecha: '2025-05-28' },
  ];
  
}
