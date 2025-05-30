package com.blog.blogapi.service;


import com.blog.blogapi.model.Post;
import com.blog.blogapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Crear nueva publicación
    public Post crearPost(Post post) {
        post.setFechaCreacion(LocalDateTime.now());
        return postRepository.save(post);
    }

    // Listar todas las publicaciones
    public List<Post> listarPosts() {
        return postRepository.findAll();
    }

    // Obtener una publicación por ID
    public Optional<Post> obtenerPostPorId(Long id) {
        return postRepository.findById(id);
    }

    // Actualizar una publicación existente
    public Optional<Post> actualizarPost(Long id, Post postActualizado) {
        return postRepository.findById(id).map(postExistente -> {
            postExistente.setTitulo(postActualizado.getTitulo());
            postExistente.setContenido(postActualizado.getContenido());
            return postRepository.save(postExistente);
        });
    }

    // Eliminar publicación
    public void eliminarPost(Long id) {
        postRepository.deleteById(id);
    }
}
