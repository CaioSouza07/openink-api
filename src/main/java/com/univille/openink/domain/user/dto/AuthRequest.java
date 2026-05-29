package com.univille.openink.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(

        @NotBlank
        String name
) {
}
