package com.proyectoFinal.Back.controller;

import com.proyectoFinal.Back.entity.SalaReunion;
import com.proyectoFinal.Back.service.ISalaReunionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SalaReunionController {

    @Autowired
    private ISalaReunionService salaService;

    // Obtener todas las salas de reunión
    @GetMapping("/all")
    public ResponseEntity<List<SalaReunion>> getAllSalas() {
        List<SalaReunion> salas = salaService.findAll();
        return ResponseEntity.ok(salas);
    }

    // Obtener sala por ID
    @GetMapping("/sala_reunion/{id}")
    public ResponseEntity<SalaReunion> getSalaById(@PathVariable Long id) {
        return salaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva sala
    @PostMapping("/create")
    public ResponseEntity<SalaReunion> createSala(@RequestBody SalaReunion sala) {
        SalaReunion nuevaSala = salaService.save(sala);
        return ResponseEntity.ok(nuevaSala);
    }

    // Actualizar sala existente
    @PutMapping("/update/{id}")
    public ResponseEntity<SalaReunion> updateSala(@PathVariable Long id, @RequestBody SalaReunion salaActualizada) {
        return salaService.findById(id)
                .map(sala -> {
                    sala.setNombreSala(salaActualizada.getNombreSala());
                    sala.setDescripcion(salaActualizada.getDescripcion());
                    sala.setUbicacion(salaActualizada.getUbicacion());
                    sala.setLatitud(salaActualizada.getLatitud());
                    sala.setLongitud(salaActualizada.getLongitud());
                    sala.setHorario(salaActualizada.getHorario());
                    sala.setDeporte(salaActualizada.getDeporte());
                    sala.setLimiteIntegrantes(salaActualizada.getLimiteIntegrantes());
                    // Si querés actualizar usuariosIngresados o creador, agregá acá también.
                    SalaReunion salaGuardada = salaService.save(sala);
                    return ResponseEntity.ok(salaGuardada);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar sala por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) {
        if (salaService.findById(id).isPresent()) {
            salaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
