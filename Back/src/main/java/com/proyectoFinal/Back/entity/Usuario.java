package com.proyectoFinal.Back.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String nombre;
        private String apellido;
        private  String correo;
        private String descripcion_usuario;
        private String imagen_perfil;
        private String imagen_portada;
        private String password;
        
    }





