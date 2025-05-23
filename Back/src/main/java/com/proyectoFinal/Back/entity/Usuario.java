package com.proyectoFinal.Back.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String nombre;
        private String apellido;
        private  String correo;
        private String password;


        private Usuario(){}

        private Usuario(Long id, String nombre, String apellido, String correo, String password){
            this.id = id;
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
            this.password = password;
        }
        
    }





