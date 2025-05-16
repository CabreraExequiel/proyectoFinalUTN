package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public void agregarUsuario(Usuario user);
    public List<Usuario> verUsuario();
}
