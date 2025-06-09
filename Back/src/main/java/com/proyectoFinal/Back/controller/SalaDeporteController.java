package com.proyectoFinal.Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoFinal.Back.Token.JwtUtil;
import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;
import com.proyectoFinal.Back.service.ISalaDeporteService;

import jakarta.servlet.http.HttpSession;


@RestController
public class SalaDeporteController {

    @Autowired
    private ISalaDeporteService salaDeporteService;

    @Autowired
    private JwtUtil jwtUtil;

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }



    @GetMapping("/sala/deporte/mostrar")
    public ResponseEntity<List<SalaDeporte>> registerUser(@RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Esta función valida el token y retorna un error 401 si no es válido (Se va a repetir en todos los métodos que requieran autenticación)
        }
        return ResponseEntity.ok(salaDeporteService.verSalasDeporte());  // Si el token es válido, retorna la lista de salas de deporte
    }

    @PostMapping("/sala/deporte/crear")
    public ResponseEntity<List<SalaDeporte>> createSalaDeporte(@RequestBody SalaDeporte salaDeporte, @RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Valida el token y retorna un error 401 si no es válido
        }
        Long id_usuario = ((Usuario) session.getAttribute("usuario")).getId(); // Obtiene el ID del usuario de la sesión
        List<SalaDeporte> nuevaSala = salaDeporteService.crearSalaDeporte(salaDeporte,id_usuario); // Crea una nueva sala de deporte y obtiene la lista actualizada
        return ResponseEntity.ok(nuevaSala);   // Retorna la lista de salas de deporte actualizada después de crear una nueva sala
    }

    @DeleteMapping("/sala/deporte/eliminar")
    public ResponseEntity<String> deleteSalaDeporte(@RequestParam Long id,@RequestHeader("Authorization") String token) { // El id se debe pasar como parámetro en la URL

        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        salaDeporteService.eliminarSalaDeporte(id);
        return ResponseEntity.ok("Sala de deporte eliminada correctamente");
    }

    @PostMapping("/sala/deporte/unirse")
    public ResponseEntity<String> unirseSalaDeporte(@RequestParam("id_sala") Long idSala, @RequestParam("id_usuario") Long idUsuario) {

        salaDeporteService.unirseSalaDeporte(idSala, idUsuario); // Llama al servicio para unirse a la sala de deporte
        return ResponseEntity.ok("Unido a la sala de deporte correctamente");
    }

}