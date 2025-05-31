import { Component } from '@angular/core';
import { ChatComponent } from "../chat/chat.component";

@Component({
  selector: 'app-salas',
  standalone: true,
  imports: [ChatComponent],
  templateUrl: './salas.component.html',
  styleUrl: './salas.component.css'
})
export class SalasComponent {
salaSeleccionada: any;

}
