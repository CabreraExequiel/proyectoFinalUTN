package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.Salas;
import com.proyectoFinal.Back.service.IUsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SalaControlador {

    @Autowired
    private IUsuarioService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @GetMapping("/sala/obtener_sala")
    public ResponseEntity<List<Salas>> obtenerSalas()
    {

        return null;
    }

    @GetMapping("/sala/crear_sala")
    public ResponseEntity<List<Salas>> crearSala()
    {
        return null;
    }

    @GetMapping("/sala/eliminar_sala")
    public ResponseEntity<List<Salas>> eliminarSala()
    {
        
        return null;
    }


    

}
