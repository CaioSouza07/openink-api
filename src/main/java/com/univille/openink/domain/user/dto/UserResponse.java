package com.univille.openink.domain.user.dto;

import com.univille.openink.domain.user.User;

public record UserResponse(
        Long id,
        String name
) {

    public UserResponse(User user){
        this(user.getId(), user.getName());
    }
}
