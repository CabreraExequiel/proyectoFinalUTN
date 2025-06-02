package com.proyectoFinal.Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.ICustomizarService;

import jakarta.servlet.http.HttpSession;


@RestController
public class CustomizarController {

    @Autowired
    private ICustomizarService customizarService;
    @Autowired
    private JwtUtil jwtUtil;


    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @GetMapping("/custom/deporte/salas")
    public ResponseEntity<List<SalaDeporte>> customizar(@RequestHeader("Authorization") String token,HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(401).build(); 
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        return ResponseEntity.ok(customizarService.verSalasDeporte(id_usuario));
    }


    
    
}
