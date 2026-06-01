package com.univille.openink.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostRequest(

        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Long userId,

        @NotNull
        Integer readTime
) {
}
