package com.proyectoFinal.Back.service;

import java.util.List;
import java.util.Optional;

import com.proyectoFinal.Back.entity.Usuario;

public interface IUsuarioService {
    //public void agregarUsuario(Usuario user);
     List<Usuario> verUsuario();
     boolean registrarUsuario(Usuario user);
     boolean iniciarSesion(Usuario user);
    Optional<Usuario> buscarPorCorreo(String correo);

}
