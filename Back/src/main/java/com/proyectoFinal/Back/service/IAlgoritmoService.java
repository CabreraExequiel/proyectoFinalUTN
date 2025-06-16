package com.proyectoFinal.Back.service;

import java.util.List;

import com.proyectoFinal.Back.entity.SalaDeporte;

/**
 *
 * @author lucas
 */
public interface IAlgoritmoService {
    List<SalaDeporte> listasRecomendadas(Long id_usuario);
}
