package com.proyectoFinal.Back.service;

import java.util.List;

import com.proyectoFinal.Back.entity.SalaDeporte;

public interface ISalaDeporteService {
    public List<SalaDeporte> verSalasDeporte();
    public List<SalaDeporte> crearSalaDeporte(SalaDeporte salaDeporte);
    public void eliminarSalaDeporte(Long id);
}
