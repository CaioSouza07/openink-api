package com.univille.openink.domain.post.dto;

import com.univille.openink.domain.post.Post;

public record PostResponse(

        Long id,
        String title,
        String description,
        Long userId,
        Integer readTime
) {
    public PostResponse(Post post){
        this(post.getId(), post.getTitle(), post.getDescription(), post.getUser().getId(), post.getReadTime());
    }
}
