package com.proyectoFinal.Back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.repoeitory.UsuarioRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

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

    @Override
    public boolean registrarUsuario(Usuario user) {
        Optional<Usuario> existeEmail = userRepo.findByCorreo(user.getCorreo()); // Verifica si el email ya existe
        if (existeEmail.isPresent()) {
            return false; // El usuario ya está registrado
        }

        try {
            Argon2 argon2 = Argon2Factory.create();
            String hash = argon2.hash(1, 1024, 1, user.getPassword()); // Hasheo de la contraseña
            user.setPassword(hash);
            userRepo.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean iniciarSesion(Usuario user) {
        Optional<Usuario> usuarioOptional = userRepo.findByCorreo(user.getCorreo()); // Verifica si el email existe

        if (usuarioOptional.isEmpty()) {
            return false; // El usuario no existe
        }

        Usuario usuario = usuarioOptional.get(); // Obtiene el objeto usuario
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = usuario.getPassword(); // Contraseña hasheada

        if (argon2.verify(hash, user.getPassword())) {
            return true; // Contraseña correcta
        } else {
            return false; // Contraseña incorrecta
        }
    }
}
