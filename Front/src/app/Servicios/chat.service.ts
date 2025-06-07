// src/app/chat.service.ts
import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { BehaviorSubject, Observable } from 'rxjs';

export interface ChatMessage {
  remitente: string;
  contenido: string;
  timestamp?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private client: Client;
  private messagesSubject = new BehaviorSubject<ChatMessage | null>(null);
  public messages$: Observable<ChatMessage | null> = this.messagesSubject.asObservable();

  private subscription?: any;

  constructor() {
    this.client = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws-chat'),
      reconnectDelay: 5000,
      debug: (str) => console.log('[STOMP]', str),
    });

    this.client.onStompError = (frame) => {
      console.error('STOMP Error:', frame);
    };

    this.client.onConnect = () => {
      console.log('[STOMP] Conectado correctamente al servidor.');
    };

    this.client.activate();
  }

  subscribeToSala() {  // sin parámetros
  if (this.subscription) {
    this.subscription.unsubscribe();
    this.subscription = undefined;
  }

  const subscribeFunc = () => {
    this.subscription = this.client.subscribe('/topic/mensajes', (message: IMessage) => {
      const msgBody = JSON.parse(message.body) as ChatMessage;
      this.messagesSubject.next(msgBody);
    });
  };

  if (this.client.connected) {
    subscribeFunc();
  } else {
    this.client.onConnect = () => {
      subscribeFunc();
    };
  }
}

  sendMessage(salaId: string, mensaje: ChatMessage) {
  if (this.client.connected) {
    this.client.publish({
      destination: `/app/chat`,  // quitar `/general`
      body: JSON.stringify(mensaje),
    });
  } else {
    console.warn('[STOMP] Cliente no conectado. No se puede enviar el mensaje.');
  }
}

}