package com.univille.openink.domain.like.dto;

public record LikeResponse(
        boolean likedByUser,
        long totalLikes
) {
}