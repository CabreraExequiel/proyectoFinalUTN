package com.proyectoFinal.Back.service;

import java.util.List;

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

        boolean existeEmail = userRepo.findByEmail(user.getCorreo()); //Verifica si el email ya existe
            if (existeEmail) {  
                return false; 
            }

        try {
            Argon2 argon2 = Argon2Factory.create();
            String hash = argon2.hash(1, 1024, 1, user.getPassword()); //Acá creo que hasheo de la contraseña
            user.setPassword(hash); //Actualizo el objeto usuario con la contraseña hasheada
            userRepo.save(user); //Guardo el usuario en la base de datos
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean iniciarSesion(Usuario user) 
    {
        if(userRepo.findByEmail(user.getCorreo())) { //Verifico si el email existe
            Usuario usuario = userRepo.SelectObject(user.getCorreo()); //Obtengo el objeto usuario (Toda la info del usuario)
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = usuario.getPassword(); //Obtengo el hash de la contraseña (Osea la contraseña encriptada)
            if(argon2.verify(hash, user.getPassword())) return false; //Verifico si la contraseña coincide con el hash
            return true;
        } else {
            return false; //En caso de que el email no exista, directamente retorno false
        }
    }
}
