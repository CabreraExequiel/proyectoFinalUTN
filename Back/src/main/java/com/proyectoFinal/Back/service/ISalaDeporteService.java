package com.proyectoFinal.Back.service;

import java.util.List;

import com.proyectoFinal.Back.entity.SalaDeporte;

public interface ISalaDeporteService {
    public List<SalaDeporte> verSalasDeporte();
    public List<SalaDeporte> crearSalaDeporte(SalaDeporte salaDeporte, Long id_usuario);
    public void eliminarSalaDeporte(Long id);
    public void unirseSalaDeporte(Long idSala, Long idUsuario);
    public void salirSalaDeporte(Long idSala, Long idUsuario);
}
