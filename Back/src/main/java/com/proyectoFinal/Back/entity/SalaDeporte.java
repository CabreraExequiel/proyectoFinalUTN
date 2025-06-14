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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Double latitud;
    private Double longitud;
    
    @ElementCollection
    @CollectionTable(
        name = "sala_integrantes",
        joinColumns = @JoinColumn(name = "sala_id")
    )
    @Column(name = "usuario_id")
    private List<Long> id_integrantes = new ArrayList<>();


}