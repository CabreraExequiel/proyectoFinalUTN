import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-football',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './football.component.html',
  styleUrl: './football.component.css'
})
export class FootballComponent {
  cards = [
    {
      title: 'FUTBOL 5 A LAS 19:00 HS',
      description: 'faltan 6 para un futbol 5 en la cancha "el zurdo", callefalsa 123',
      image: 'assets/futbol-card.jpg',
      vacantes: '1/6'
    },
    ]
    agregarCard() {
  // Por ejemplo, agregamos una card con datos genéricos
  this.cards.push({
    title: 'NUEVO FUTBOL 5',
    description: 'Nueva cancha agregada, ¡sumate!',
    image: 'assets/futbol-card.jpg',
    vacantes: '6/6'
  });
}


}
