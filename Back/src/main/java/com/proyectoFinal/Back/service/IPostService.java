package com.proyectoFinal.Back.service;

import com.proyectoFinal.Back.entity.Post;

import java.util.List;

public interface IPostService {
    List<Post> obtenerTodos();
    Post guardar(Post post);
}
