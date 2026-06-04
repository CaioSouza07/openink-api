package com.univille.openink.controller;

import com.univille.openink.domain.post.PostService;
import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.post.dto.PostResponse;
import com.univille.openink.domain.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> save(
            @RequestBody @Valid CreatePostRequest request,
            Authentication authentication,
            UriComponentsBuilder uriBuilder){

        var post = postService.save(request, (User) authentication.getPrincipal());
        var uri = uriBuilder.path("/post/{id}").buildAndExpand(post.id()).toUri();

        return ResponseEntity.created(uri).body(post);
    }

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAll(@PageableDefault(size = 20, sort = {"createdAt"}) Pageable pageable){
        var posts = postService.getAll(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id){
        var post = postService.getById(id);
        return ResponseEntity.ok(post);
    }
}
