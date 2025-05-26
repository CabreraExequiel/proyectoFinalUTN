import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-voley',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './voley.component.html',
  styleUrl: './voley.component.css'
})
export class VoleyComponent {
  cards = [
  {
    titulo: 'FUTBOL 5 A LAS 19:00 HS',
    descripcion: 'faltan 6 para un futbol 5 en la cancha "el zurdo", callefalsa 123',
    vacantes: '1/6',
    imagen: 'assets/voley-fondo.jpg'
  },
  {
    titulo: 'FUTBOL 7 A LAS 20:00 HS',
    descripcion: 'faltan 8 para un futbol 7 en "La Cancha", calle real 456',
    vacantes: '2/7',
    imagen: 'assets/voley-fondo.jpg'
  },
   {
    titulo: 'FUTBOL 5 A LAS 19:00 HS',
    descripcion: 'faltan 6 para un futbol 5 en la cancha "el zurdo", callefalsa 123',
    vacantes: '1/6',
    imagen: 'assets/voley-fondo.jpg'
  },
  {
    titulo: 'FUTBOL 7 A LAS 20:00 HS',
    descripcion: 'faltan 8 para un futbol 7 en "La Cancha", calle real 456',
    vacantes: '2/7',
    imagen: 'assets/voley-fondo.jpg'
  },
   {
    titulo: 'FUTBOL 5 A LAS 19:00 HS',
    descripcion: 'faltan 6 para un futbol 5 en la cancha "el zurdo", callefalsa 123',
    vacantes: '1/6',
    imagen: 'assets/voley-fondo.jpg'
  },
  {
    titulo: 'FUTBOL 7 A LAS 20:00 HS',
    descripcion: 'faltan 8 para un futbol 7 en "La Cancha", calle real 456',
    vacantes: '2/7',
    imagen: 'assets/voley-fondo.jpg'
  },
];


}
