package com.proyectoFinal.Back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class SalaDeporte {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id_sala;
        private String nombre_sala;
        private String deporte;
        private String descripcion;
        private int cantidad_integrantes;
        private int limite_integrantes;


        private SalaDeporte(){}

        private SalaDeporte(Long id_sala, String nombre_sala, String deporte, String descripcion, int cantidad_integrantes, int limite_integrantes){
            this.id_sala = id_sala;
            this.nombre_sala = nombre_sala;
            this.deporte = deporte;
            this.descripcion = descripcion;
            this.cantidad_integrantes = cantidad_integrantes;
            this.limite_integrantes = limite_integrantes;
        }
}
