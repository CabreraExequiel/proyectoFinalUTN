package com.proyectoFinal.Back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String remitente;
    private String contenido;
    private LocalDateTime timestamp;


    public  Chat(){};

    public Chat(Long id, String remitente, String contenido, LocalDateTime timestamp){
        this.id = id;
        this.remitente = remitente;
        this.contenido = contenido;
        this.timestamp = timestamp;
    }
}