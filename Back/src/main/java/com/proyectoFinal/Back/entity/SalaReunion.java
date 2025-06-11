package com.proyectoFinal.Back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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


}






