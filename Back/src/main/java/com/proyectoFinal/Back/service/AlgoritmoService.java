package com.proyectoFinal.Back.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.repoeitory.SalaDeporteRepository;

@Service
public class AlgoritmoService implements IAlgoritmoService {

@Autowired
    private SalaDeporteRepository salaDeporteRepository;

    @Override
public List<SalaDeporte> listasRecomendadas(Long id_usuario) {
    // Obtener salas en las que el usuario participa
    List<SalaDeporte> salasUsuario = salaDeporteRepository.findSalasWhereUsuarioIntegrante(id_usuario);
    
    // Contar frecuencia de cada deporte
    Map<String, Long> frecuenciaDeportes = salasUsuario.stream()
        .collect(Collectors.groupingBy(SalaDeporte::getDeporte, Collectors.counting()));

    if (frecuenciaDeportes.isEmpty()) return new ArrayList<>();

    // Obtener la frecuencia máxima
    long maxFrecuencia = Collections.max(frecuenciaDeportes.values());

    // Filtrar los deportes más frecuentes
    Set<String> deportesPreferidos = frecuenciaDeportes.entrySet().stream()
        .filter(entry -> entry.getValue() == maxFrecuencia)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());

    // Obtener salas donde el usuario no participa
    List<SalaDeporte> salasNoPertenece = salaDeporteRepository.findSalasWhereUsuarioNotIntegrante(id_usuario);

    // Filtrar solo las salas del deporte preferido
    return salasNoPertenece.stream()
        .filter(sala -> deportesPreferidos.contains(sala.getDeporte()))
        .collect(Collectors.toList());
}


    
}
