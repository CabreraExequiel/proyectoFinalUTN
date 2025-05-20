import { Component } from '@angular/core';
import { UsuarioComponent } from "../usuario/usuario.component";
import { FootballComponent } from '../football/football.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [UsuarioComponent,FootballComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
