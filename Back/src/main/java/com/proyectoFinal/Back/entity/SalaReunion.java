package com.proyectoFinal.Back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
public class SalaReunion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombreSala;
    private String descripcion;
    private String ubicacion;
    private Double latitud;   // Para Google Maps
    private Double longitud;  // Para Google Maps
    private LocalDateTime horario;
    private String deporte;
    private int limiteIntegrantes;

    public SalaReunion() {
    }

    public SalaReunion(Long id, String nombreSala, String descripcion, String ubicacion, Double latitud, Double longitud, LocalDateTime horario, String deporte, Integer limiteIntegrantes) {
        this.id = id;
        this.nombreSala = nombreSala;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario = horario;
        this.deporte = deporte;
        this.limiteIntegrantes = limiteIntegrantes;


    }
}






