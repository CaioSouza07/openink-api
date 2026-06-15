package com.univille.openink.domain.content.dto;

import com.univille.openink.domain.content.Content;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

public record ContentResponse(
        Long id,
        Long idPost,
        String text,
        LocalDateTime createdAt
) {
    public ContentResponse(Content data){
        this(data.getId(), data.getPost().getId(), data.getText(), data.getCreatedAt());
    }
}