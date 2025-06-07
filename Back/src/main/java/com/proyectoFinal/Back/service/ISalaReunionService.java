package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.SalaReunion;

import java.util.List;
import java.util.Optional;

public interface ISalaReunionService {
    List<SalaReunion> findAll();
    Optional<SalaReunion> findById(Long id);
    SalaReunion save(SalaReunion sala);
    void deleteById(Long id);
}
