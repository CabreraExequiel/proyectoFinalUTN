package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    public IUsuarioService userService;


    @PostMapping("/nuevo/usuario")
    public void agregarUsuario(@RequestBody Usuario user){
        userService.agregarUsuario(user);
    };

    @GetMapping("/ver/usuario")
    @ResponseBody
    public List<Usuario> verUsuario(){
        return userService.verUsuario();
    };

 
}
