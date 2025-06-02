package com.proyectoFinal.Back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Salas {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id_salas;
        private String nombre;
        private String descripcion;
        private  String tipo_deporte;
        private String cantidad_integrantes;
        private String limite_de_integrantes;


        private Salas(){}

        private Salas(Long id_salas, 
                        String nombre, 
                        String tipo_deporte, 
                        String cantidad_integrantes, 
                        String limite_de_integrantes){
            this.id_salas = id_salas;
            this.nombre = nombre;
            this.tipo_deporte = tipo_deporte;
            this.cantidad_integrantes = cantidad_integrantes;
            this.limite_de_integrantes = limite_de_integrantes;
        }
        
    }






