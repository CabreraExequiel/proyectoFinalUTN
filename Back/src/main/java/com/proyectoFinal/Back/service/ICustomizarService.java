package com.proyectoFinal.Back.service;
import java.util.List;

import com.proyectoFinal.Back.EntidadesPersonalizadas.DataPerfil;
import com.proyectoFinal.Back.entity.SalaDeporte;

public interface ICustomizarService {
List<SalaDeporte> verSalasDeporte(Long id_usuario);
public DataPerfil verUsuario(Long id_usuario);
public DataPerfil editarUsuario(Long id_usuario, DataPerfil dataPerfil);
public String subirFoto(Long id_usuario, String rutaFoto, String tipoFoto);
public String verFotoPerfil(Long id_usuario);
public String verFotoPortada(Long id_usuario);
}
