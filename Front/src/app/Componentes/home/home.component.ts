import { Component } from '@angular/core';
import { UsuarioComponent } from "../usuario/usuario.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [UsuarioComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
