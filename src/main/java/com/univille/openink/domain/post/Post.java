package com.univille.openink.domain.post;

import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 254)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, name = "read_time")
    private Integer readTime;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    public Post(CreatePostRequest request, User user){
        this.title = request.title();
        this.description = request.description();
        this.user = user;
        this.readTime = request.readTime();
        this.createdAt = LocalDateTime.now();
    }
}
