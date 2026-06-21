package com.univille.openink;

import com.univille.openink.domain.like.Like;
import com.univille.openink.domain.like.LikeRepository;
import com.univille.openink.domain.like.LikeService;
import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.post.PostRepository;
import com.univille.openink.domain.user.User;
import com.univille.openink.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LikeService likeService;

    @Test
    void toggleLike_whenAlreadyExists_deletesLike() {
        User u = new User();
        u.setId(1L);
        Post p = new Post();
        p.setId(2L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(u));
        when(postRepository.findById(2L)).thenReturn(Optional.of(p));
        when(likeRepository.existsByUserAndPost(u, p)).thenReturn(true);

        likeService.toggleLike(1L, 2L);

        verify(likeRepository, times(1)).deleteByUserAndPost(u, p);
        verify(likeRepository, never()).save(any(Like.class));
    }

    @Test
    void toggleLike_whenNotExists_savesLike() {
        User u = new User();
        u.setId(3L);
        Post p = new Post();
        p.setId(4L);

        when(userRepository.findById(3L)).thenReturn(Optional.of(u));
        when(postRepository.findById(4L)).thenReturn(Optional.of(p));
        when(likeRepository.existsByUserAndPost(u, p)).thenReturn(false);

        likeService.toggleLike(3L, 4L);

        verify(likeRepository, times(1)).save(any(Like.class));
        verify(likeRepository, never()).deleteByUserAndPost(u, p);
    }

    @Test
    void getNumberLikes_returnsCount() {
        Post p = new Post();
        p.setId(10L);

        when(postRepository.findById(10L)).thenReturn(Optional.of(p));
        when(likeRepository.countByPost(p)).thenReturn(7L);

        var resp = likeService.getNumberLikes(10L);

        assertNotNull(resp);
        assertEquals(7L, resp.numberLikes());
    }
}
