package com.blog.blogapi.controller;

import com.blog.blogapi.model.Post;
import com.blog.blogapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Crear nuevo post
    @PostMapping
    public ResponseEntity<Post> crearPost(@Valid @RequestBody Post post) {
        Post nuevoPost = postService.crearPost(post);
        return ResponseEntity.ok(nuevoPost);
    }

    // Listar todos los posts
    @GetMapping
    public ResponseEntity<List<Post>> listarPosts() {
        return ResponseEntity.ok(postService.listarPosts());
    }

    // Obtener post por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> obtenerPostPorId(@PathVariable Long id) {
        Optional<Post> post = postService.obtenerPostPorId(id);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar post existente
    @PutMapping("/{id}")
    public ResponseEntity<Post> actualizarPost(@PathVariable Long id, @Valid @RequestBody Post postActualizado) {
        Optional<Post> post = postService.actualizarPost(id, postActualizado);
        return post.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long id) {
        postService.eliminarPost(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/posts")
    public List<Post> obtenerTodosLosPosts() {
        return postService.listarPosts();
    }

}



