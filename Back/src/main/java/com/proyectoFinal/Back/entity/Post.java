package com.proyectoFinal.Back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StringContent;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String autor;
    private String contenido;
    private LocalDateTime fechaPublicacion;

    public Post(){}

    public Post(Long id, String autor, String contenido, LocalDateTime fechaPublicacion){
        this.id=id;
        this.autor=autor;
        this.contenido=contenido;
        this.fechaPublicacion=fechaPublicacion;
    }
}
