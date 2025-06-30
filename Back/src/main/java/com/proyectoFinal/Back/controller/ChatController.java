
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


    @MessageMapping("/chat/{idSala}")
    @SendTo("/topic/mensajes/{idSala}")
    public Chat recibirMensaje(@DestinationVariable String idSala, Chat mensaje) {
        mensaje.setTimestamp(LocalDateTime.now());
        mensaje.setIdSala(idSala);
        return chatServ.guardarMensaje(mensaje);
    }


    @GetMapping("/historial/{idSala}")
    public List<Chat> obtenerHistorialPorSala(@PathVariable String idSala) {
        return chatServ.obtenerMensajesPorSala(idSala);
    }
}
