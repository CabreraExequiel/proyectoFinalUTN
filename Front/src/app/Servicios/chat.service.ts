import { Injectable } from '@angular/core';
import { Client, Message } from '@stomp/stompjs';
import SockJS from 'sockjs-client'; // ✅ Importación correcta

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private stompClient!: Client; // ✅ Corregido

  private readonly endpoint = 'http://localhost:8080/chat';

  

  sendMessage(room: string, sender: string, content: string) {
    const chatMessage = { sender, content, room };
    this.stompClient.publish({
      destination: '/app/chat.sendMessage',
      body: JSON.stringify(chatMessage),
    });
  }

  disconnect() {
    if (this.stompClient) {
      this.stompClient.deactivate();
    }
  }
}
