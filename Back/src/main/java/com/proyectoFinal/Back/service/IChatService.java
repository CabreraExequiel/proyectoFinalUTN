package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.Chat;

import java.util.List;

public interface IChatService {
    Chat guardarMensaje(Chat chat);
    List<Chat> obtenerMensajes();

}