package com.univille.openink.domain.like;


import com.univille.openink.domain.like.dto.LikeResponse;
import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.post.PostRepository;
import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void toggleLike(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        if (likeRepository.existsByUserAndPost(user, post)){
            likeRepository.deleteByUserAndPost(user, post);
        }else {
            Like newLike = new Like();
            newLike.setUser(user);
            newLike.setPost(post);
            likeRepository.save(newLike);
        }
    }
}
