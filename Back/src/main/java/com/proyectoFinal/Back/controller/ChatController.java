
package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.entity.Chat;
import com.proyectoFinal.Back.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private IChatService chatServ;

    // WebSocket endpoint: recibe mensajes para sala específica
    @MessageMapping("/chat/{idSala}")           // recibe en /app/chat/{idSala}
    @SendTo("/topic/mensajes/{idSala}")         // reenvía a /topic/mensajes/{idSala}
    public Chat recibirMensaje(@DestinationVariable String idSala, Chat mensaje) {
        mensaje.setTimestamp(LocalDateTime.now());
        mensaje.setIdSala(idSala); // Asignamos la sala al mensaje
        return chatServ.guardarMensaje(mensaje);
    }

    // REST endpoint para obtener historial de mensajes de una sala
    @GetMapping("/historial/{idSala}")
    public List<Chat> obtenerHistorialPorSala(@PathVariable String idSala) {
        return chatServ.obtenerMensajesPorSala(idSala);
    }
}
