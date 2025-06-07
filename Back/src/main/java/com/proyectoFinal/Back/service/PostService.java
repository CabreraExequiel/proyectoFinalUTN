package com.proyectoFinal.Back.service;


import com.proyectoFinal.Back.entity.Post;
import com.proyectoFinal.Back.repoeitory.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Post> obtenerTodos() {
        return postRepo.findAllByOrderByFechaPublicacionDesc();
    }

    @Override
    public Post guardar(Post post) {
        post.setFechaPublicacion(LocalDateTime.now());
        return postRepo.save(post);
    }
}
