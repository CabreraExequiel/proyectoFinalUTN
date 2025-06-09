package com.proyectoFinal.Back.service;
import java.util.List;

import com.proyectoFinal.Back.entity.SalaDeporte;
import com.proyectoFinal.Back.entity.Usuario;

public interface ICustomizarService {
List<SalaDeporte> verSalasDeporte(Long id_usuario);
Usuario verUsuario(Long id_usuario);
}
