package com.proyectoFinal.Back.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.repoeitory.SalaDeporteRepository;

@Service
public class SalaDeporteService implements ISalaDeporteService {

    @Autowired
    private SalaDeporteRepository salaDeporteRepository;

    @Override
    public List<SalaDeporte> verSalasDeporte() {
        return salaDeporteRepository.findAll();
    }

    @Override
    public List<SalaDeporte> crearSalaDeporte(SalaDeporte salaDeporte) {
        salaDeporteRepository.save(salaDeporte);
        return salaDeporteRepository.findAll();
    }

    @Override
    public void eliminarSalaDeporte(Long id) {
        salaDeporteRepository.deleteById(id);
    }
    
}
