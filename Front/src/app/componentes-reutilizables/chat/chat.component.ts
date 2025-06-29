
import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChatService, ChatMessage } from '../../Servicios/chat.service';
import { Subscription } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  @Input() idSala!: string;

  remitente = '';
  contenido = '';
  mensajes: ChatMessage[] = [];
  chatAbierto = false;
  nombreUsuario: string = '';

  private sub: Subscription = new Subscription();

  constructor(private chatService: ChatService, private http: HttpClient) {}

  ngOnInit() {
    if (!this.idSala) {
      console.error('Error: idSala es requerido para el chat');
      return;
    }

    this.http.get<ChatMessage[]>(`http://localhost:8080/historial/${this.idSala}`).subscribe(historial => {
      this.mensajes = historial;

      this.nombreUsuario = localStorage.getItem('nombreUsuario') || 'Anónimo';
      this.remitente = this.nombreUsuario;
      this.chatService.subscribeToSala(this.idSala);

      this.sub = this.chatService.messages$.subscribe((mensaje) => {
        if (mensaje) {
          this.mensajes.push(mensaje);
          setTimeout(() => this.scrollToBottom(), 0);
        }
      });
    });
  }

  enviarMensaje() {
    if (!this.contenido || !this.remitente) return;

    const mensaje: ChatMessage = {
      remitente: this.remitente,
      contenido: this.contenido,
      timestamp: new Date().toISOString()
    };
    this.chatService.sendMessage(this.idSala, mensaje);
    this.contenido = '';
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  toggleChat() {
    this.chatAbierto = !this.chatAbierto;
    if (this.chatAbierto) {
      setTimeout(() => this.scrollToBottom(), 0);
    }
  }

  private scrollToBottom() {
    const mensajesDiv = document.querySelector('.mensajes');
    if (mensajesDiv) {
      mensajesDiv.scrollTop = mensajesDiv.scrollHeight;
    }
  }
}
