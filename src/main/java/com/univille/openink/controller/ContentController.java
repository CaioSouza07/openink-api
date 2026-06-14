package com.univille.openink.controller;

import com.univille.openink.domain.content.Content;
import com.univille.openink.domain.content.ContentService;
import com.univille.openink.domain.content.dto.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public ResponseEntity<List<ContentResponse>> listarTodos() {
        List<ContentResponse> contents = contentService.listarTodos();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> buscarPorId(@PathVariable Long id) {
        ContentResponse content = contentService.buscarPorId(id);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<ContentResponse>> buscarPorIdPost(@PathVariable Long idPost) {
        List<ContentResponse> contents = contentService.buscarPorIdPost(idPost);
        return ResponseEntity.ok(contents);
    }

    @PostMapping
    public ResponseEntity<ContentResponse> criar(@RequestBody Content content) {
        ContentResponse novoContent = contentService.criar(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContentResponse> atualizar(
            @PathVariable Long id,
            @RequestBody Content content) {
        ContentResponse atualizado = contentService.atualizar(id, content);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contentService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}