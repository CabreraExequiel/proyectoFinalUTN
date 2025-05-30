import { Component, OnDestroy, OnInit } from '@angular/core';
import { ChatService } from '../../Servicios/chat.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  messages: any[] = [];
  messageText = '';
  username = 'Usuario' + Math.floor(Math.random() * 1000);
  room = 'sala1'; // puedes cambiarlo dinámicamente

  constructor(private chatService: ChatService) {}

  ngOnInit() {
  }

  send() {
    if (this.messageText.trim()) {
      this.chatService.sendMessage(this.room, this.username, this.messageText);
      this.messageText = '';
    }
  }

  ngOnDestroy() {
    this.chatService.disconnect();
  }
}