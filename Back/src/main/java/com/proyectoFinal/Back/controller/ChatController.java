package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.entity.Chat;
import com.proyectoFinal.Back.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatServ;

    // --- WebSocket endpoint ---
    @MessageMapping("/chat")           // recibe en /app/chat
    @SendTo("/topic/mensajes")        // reenvía a /topic/mensajes
    public Chat recibirMensaje(Chat mensaje) {
        mensaje.setTimestamp(LocalDateTime.now());
        return chatServ.guardarMensaje(mensaje);
    }



    // --- REST endpoint ---
    @GetMapping("/historial")
    public List<Chat> obtenerHistorial() {
        return chatServ.obtenerMensajes();
    }
}