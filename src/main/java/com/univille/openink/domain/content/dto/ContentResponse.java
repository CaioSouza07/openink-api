package com.univille.openink.domain.content.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponse {
    private Long id;
    private Long idPost;
    private String texto;
    private LocalDateTime createdAt;
}