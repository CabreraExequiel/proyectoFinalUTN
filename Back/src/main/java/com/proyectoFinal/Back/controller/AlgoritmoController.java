package com.proyectoFinal.Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.IAlgoritmoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/alg")
public class AlgoritmoController {

    @Autowired
    private IAlgoritmoService algoritmoService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }


    @GetMapping("/listas_recomendadas")
    public List<SalaDeporte> listasRecomendadas(@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return null;
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId();
        return algoritmoService.listasRecomendadas(id_usuario);
    }
    
}
