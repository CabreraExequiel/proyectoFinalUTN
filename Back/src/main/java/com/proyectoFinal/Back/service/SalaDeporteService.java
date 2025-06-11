package com.proyectoFinal.Back.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.repoeitory.SalaDeporteRepository;

import jakarta.transaction.Transactional;

@Service
public class SalaDeporteService implements ISalaDeporteService {

    @Autowired
    private SalaDeporteRepository salaDeporteRepository;

    @Override
    public List<SalaDeporte> verSalasDeporte(Long id) {
        return salaDeporteRepository.findSalasWhereUsuarioNotIntegrante(id);
    }


    @Override
    public List<SalaDeporte> crearSalaDeporte(SalaDeporte salaDeporte, Long id_usuario) {
        //***Configuramos al creador como primer integrante******
        List<Long> idIntegrantes = salaDeporte.getId_integrantes();
        idIntegrantes.add(id_usuario); // Por defecto, el usuario que crea la sala se agrega como integrante
        salaDeporte.setId_integrantes(idIntegrantes);
        salaDeporte.setId_creador(id_usuario); // Establece el creador de la sala
        salaDeporte.setCantidad_integrantes(1); // Inicializamos la cantidad de integrantes a 1 (el creador)
        //********************************************************

        //*************Guardamos y retornamos*********************
        salaDeporteRepository.save(salaDeporte);
        return salaDeporteRepository.findAll();
        //********************************************************
    }


    @Override
    public void eliminarSalaDeporte(Long id) {
        salaDeporteRepository.deleteById(id);
    }

    @Override
    @Transactional  // Asegura que toda la operación sea atómica (todo o nada)
    public void unirseSalaDeporte(Long idSala, Long idUsuario) {
        System.out.println("Unirse a sala de deporte: " + idSala + " por usuario: " + idUsuario);
        // 1. Buscar la sala (o lanzar excepción si no existe)
        SalaDeporte sala = salaDeporteRepository.findById(idSala)
            .orElseThrow(() -> new RuntimeException("Sala no encontrada")); 

        // 2. Inicializar la lista de integrantes si es null (evita NullPointerException)
        if (sala.getId_integrantes() == null) {
            sala.setId_integrantes(new ArrayList<>());
        }

        // 3. Añadir usuario solo si no existe ya (evita duplicados)
        if (!sala.getId_integrantes().contains(idUsuario)) {
            sala.getId_integrantes().add(idUsuario);
            sala.setCantidad_integrantes(sala.getCantidad_integrantes() + 1);
            
            // 4. Guardar explícitamente los cambios en la base de datos
            salaDeporteRepository.save(sala); 
        }
        // Si el usuario ya estaba, no se ejecuta save()
    }

    @Override
    @Transactional  
    public void salirSalaDeporte(Long idSala, Long idUsuario) {
        SalaDeporte salaDeporte = salaDeporteRepository.findById(idSala)
            .orElseThrow(() -> new RuntimeException("Sala no encontrada"));

        List<Long> idIntegrantes = salaDeporte.getId_integrantes();
        if (idIntegrantes != null && idIntegrantes.remove(idUsuario)) { // Elimina solo si el usuario estaba
            salaDeporte.setCantidad_integrantes(
                Math.max(0, salaDeporte.getCantidad_integrantes() - 1) // Evita números negativos
            );
        }
    }


    @Override
    public List<SalaDeporte> verSalasIntegradas(Long id) {
        List<SalaDeporte> salasIntegradas = salaDeporteRepository.findSalasWhereUsuarioIntegrante(id);
        if (salasIntegradas == null) {
            return new ArrayList<>(); // Retorna una lista vacía si no hay salas integradas
        }
        return salasIntegradas; // Retorna la lista de salas donde el usuario es integrante
    }
    
}
