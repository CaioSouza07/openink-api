package com.univille.openink.controller;

import com.univille.openink.domain.like.LikeService;
import com.univille.openink.domain.like.dto.LikeResponse;
import com.univille.openink.domain.like.dto.NumberLikesResponse;
import com.univille.openink.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Void> toggleLike(
            @PathVariable Long postId,
            @AuthenticationPrincipal User loggedUser
    ) {
        likeService.toggleLike(loggedUser.getId(), postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<NumberLikesResponse> getLikesByPost(@PathVariable Long postId) {
        var totalLikes = likeService.getNumberLikes(postId);
        return  ResponseEntity.ok(totalLikes);
    }
}