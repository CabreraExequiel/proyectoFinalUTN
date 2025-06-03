package com.proyectoFinal.Back.service;
import java.util.List;

import com.proyectoFinal.Back.entity.SalaDeporte;

public interface ICustomizarService {
List<SalaDeporte> verSalasDeporte(Long id_usuario);
}
