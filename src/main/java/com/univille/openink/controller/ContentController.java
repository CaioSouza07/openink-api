package com.univille.openink.controller;

import com.univille.openink.domain.content.Content;
import com.univille.openink.domain.content.ContentService;
import com.univille.openink.domain.content.dto.ContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/{id}")
    public ResponseEntity<ContentResponse> buscarPorId(@PathVariable Long id) {
        ContentResponse content = contentService.buscarPorId(id);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/post/{idPost}")
    public ResponseEntity<ContentResponse> buscarPorIdPost(@PathVariable Long idPost) {
        ContentResponse contents = contentService.buscarPorIdPost(idPost);
        return ResponseEntity.ok(contents);
    }

}