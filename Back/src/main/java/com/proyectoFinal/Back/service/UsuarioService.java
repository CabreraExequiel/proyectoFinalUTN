package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.repoeitory.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    public UsuarioRepository userRepo;

    @Override
    public void agregarUsuario(Usuario user) {
        userRepo.save(user);
    }

    @Override
    public List<Usuario> verUsuario() {
        return userRepo.findAll();
    }
}
