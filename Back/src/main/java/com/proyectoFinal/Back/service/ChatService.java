package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.Chat;
import com.proyectoFinal.Back.repoeitory.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService {
    @Autowired
    private ChatRepository chatRepo;

    @Override
    public Chat guardarMensaje(Chat chat) {
        return chatRepo.save(chat);
    }

    @Override
    public List<Chat> obtenerMensajes() {
        return chatRepo.findAll();
    }
}