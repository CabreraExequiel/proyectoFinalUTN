package com.proyectoFinal.Back.controller;


import com.proyectoFinal.Back.entity.Post;
import com.proyectoFinal.Back.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/foro")
public class PostController {

    @Autowired
    private PostService postService;

    // Obtener todos los posts
    @GetMapping
    public List<Post> obtenerPosts() {
        return postService.obtenerTodos();
    }

    // Crear un nuevo post
    @PostMapping
    public Post guardar(@RequestBody Post post) {
        post.setFechaPublicacion(LocalDateTime.now());
        return postService.guardar(post);
    }
}
