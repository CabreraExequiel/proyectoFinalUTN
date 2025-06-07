package com.proyectoFinal.Back.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class SalaDeporte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_sala;
    private Long id_creador;
    private String nombre_sala;
    private String deporte;
    private String descripcion;
    private int cantidad_integrantes;
    private int limite_integrantes;
    private String ubicacion;
    
    @ElementCollection
    @CollectionTable(
        name = "sala_integrantes",
        joinColumns = @JoinColumn(name = "sala_id")
    )
    @Column(name = "usuario_id")
    private List<Long> id_integrantes = new ArrayList<>();

    // Tus constructores aquí...
    private SalaDeporte(){}

    public SalaDeporte(Long id_sala, String nombre_sala, String deporte, String descripcion, 
                     int cantidad_integrantes, int limite_integrantes, String ubicacion,
                     List<Long> id_integrantes, Long id_creador) {
        this.id_sala = id_sala;
        this.nombre_sala = nombre_sala;
        this.deporte = deporte;
        this.descripcion = descripcion;
        this.cantidad_integrantes = cantidad_integrantes;
        this.limite_integrantes = limite_integrantes;
        this.ubicacion = ubicacion;
        this.id_integrantes = id_integrantes != null ? id_integrantes : new ArrayList<>();
        this.id_creador = id_creador;
    }
}