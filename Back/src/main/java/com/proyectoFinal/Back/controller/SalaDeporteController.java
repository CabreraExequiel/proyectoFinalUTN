package com.proyectoFinal.Back.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/sala/perfil/mostrar")
    public ResponseEntity<List<SalaDeporte>> mostrarSalasQueSoyIntegrante(
        @RequestHeader("Authorization") String token, 
        HttpSession session) {
    
    if (!validarToken(token)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna 401 si el token es inválido
    }

    Long id = ((Usuario) session.getAttribute("usuario")).getId(); 

    List<SalaDeporte> salasIntegradas = salaDeporteService.verSalasIntegradas(id);

    System.out.println("📋 Salas de deporte donde el usuario con ID " + id + " es integrante:");
    for (SalaDeporte sala : salasIntegradas) {
        System.out.println("🔹 ID: " + sala.getId_sala() + 
                           ", Nombre: " + sala.getNombre_sala() + 
                           ", Descripción: " + sala.getDescripcion() + 
                           ", Deporte: " + sala.getDeporte());
    }

    return ResponseEntity.ok(salasIntegradas); // Retorna la lista al frontend
}

    @GetMapping("/sala/deporte/{id}")
    public ResponseEntity<SalaDeporte> getSalaDeportePorId(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SalaDeporte sala = salaDeporteService.buscarSalaPorId(id);
        if (sala == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(sala);
    }

    @GetMapping("/sala/deporte/mostrar")
    public ResponseEntity<List<SalaDeporte>> registerUser(@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Esta función valida el token y retorna un error 401 si no es válido (Se va a repetir en todos los métodos que requieran autenticación)
        }
        Long id = ((Usuario) session.getAttribute("usuario")).getId(); 
        return ResponseEntity.ok(salaDeporteService.verSalasDeporte(id));  // Si el token es válido, retorna la lista de salas de deporte
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
    public void unirseSalaDeporte(@RequestParam("idSala") Long idSala,@RequestHeader("Authorization") String token, HttpSession session) {
        if (!validarToken(token)) {
            throw new RuntimeException("Token inválido"); // Lanza una excepción si el token no es válido
        }
        Long idUsuario = ((Usuario) session.getAttribute("usuario")).getId(); 
        salaDeporteService.unirseSalaDeporte(idSala, idUsuario); // Llama al servicio para unirse a la sala de deporte
    }

}