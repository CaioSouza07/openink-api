package com.univille.openink.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(

        @NotBlank
        String name
) {
}
