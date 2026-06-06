package com.univille.openink.controller;

import com.univille.openink.domain.like.LikeService;
import com.univille.openink.domain.like.dto.LikeResponse;
import com.univille.openink.domain.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }
    @PostMapping
    public ResponseEntity<LikeResponse> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal User loggedUser
    ) {
        LikeResponse response = likeService.toggleLike(loggedUser.getId(), postId);
        return ResponseEntity.ok(response);
    }
}