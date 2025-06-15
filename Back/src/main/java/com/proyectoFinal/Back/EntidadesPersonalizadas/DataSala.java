package com.proyectoFinal.Back.EntidadesPersonalizadas;
import com.proyectoFinal.Back.entity.SalaDeporte;

import lombok.Data;


@Data
public class DataSala {
    SalaDeporte salaDeporte;
    String[] integrantes;
    boolean esCreador;
}
