package com.proyectoFinal.Back.EntidadesPersonalizadas;

import lombok.Data;
/**
 *
 * @author lucas
 */
@Data
public class DataPerfil {
    private String nombre;
    private String apellido;
    private String correo;
    private String descripcion_usuario;
    private String imagen_perfil;
    private String imagen_portada;

    public DataPerfil(String nombre, String apellido, String correo, String descripcion_usuario, String imagen_perfil, String imagen_portada) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.descripcion_usuario = descripcion_usuario;
        this.imagen_perfil = imagen_perfil;
        this.imagen_portada = imagen_portada;
    }
}
