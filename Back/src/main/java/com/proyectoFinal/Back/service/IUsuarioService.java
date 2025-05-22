package com.proyectoFinal.Back.service;

import java.util.List;

import com.proyectoFinal.Back.entity.Usuario;

public interface IUsuarioService {
    public void agregarUsuario(Usuario user);
    public List<Usuario> verUsuario();
    public boolean registrarUsuario(Usuario user);
    public boolean iniciarSesion(Usuario user);
}
