package com.univille.openink.domain.post.dto;

import com.univille.openink.domain.post.Post;

import java.time.LocalDateTime;

public record PostResponse(

        Long id,
        String title,
        String description,
        Long userId,
        Integer readTime,
        LocalDateTime createdAt
) {
    public PostResponse(Post post){
        this(post.getId(), post.getTitle(), post.getDescription(), post.getUser().getId(), post.getReadTime(), post.getCreatedAt());
    }
}
