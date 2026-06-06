package com.univille.openink.domain.like;

import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Boolean existsByUserAndPost(User user, Post post);

    Boolean deleteByUserAndPost(User user, Post post);

}
