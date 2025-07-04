package com.proyectoFinal.Back.service;

import java.util.List;

import com.proyectoFinal.Back.EntidadesPersonalizadas.DataSala;
import com.proyectoFinal.Back.entity.SalaDeporte;

public interface ISalaDeporteService {
    public List<SalaDeporte> verSalasDeporte(Long id);
    public List<SalaDeporte> crearSalaDeporte(SalaDeporte salaDeporte, Long id_usuario);
    public void eliminarSalaDeporte(Long id);
    public void unirseSalaDeporte(Long idSala, Long idUsuario);
    public void salirSalaDeporte(Long idSala, Long idUsuario);
    public List<SalaDeporte> verSalasIntegradas(Long id);
    public DataSala buscarSalaPorId(Long id, Long idUsuario);
}
