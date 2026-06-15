package com.univille.openink.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(

        @Size(min = 10, message = "Tamanho mínimo para título é de 10 caracteres")
        String title,

        @Size(min = 15, message = "Tamanho mínimo para descrição é de 15 caracteres")
        String description,

        @NotNull
        Integer readTime,

        @Size(min = 90, message = "Tamanho mínimo para o markdown é de 90 caracteres")
        String text
) {
}
