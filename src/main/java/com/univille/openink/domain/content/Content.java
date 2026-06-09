package com.univille.openink.domain.content;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contents")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_post", nullable = false)
    private Long postId;

    @Column(name = "texto", nullable = false)
    private String texto;
}
