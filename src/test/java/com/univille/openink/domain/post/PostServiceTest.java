package com.univille.openink.domain.post;

import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.post.dto.PostResponse;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostRepository postRepository;
    private PostService postService;

    @BeforeEach
    void setUp(){
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
    }

    @Test
    @DisplayName("Deve salvar post corretamente")
    void savePost(){
        User user = new User(1L, "teste");
        CreatePostRequest req = new CreatePostRequest("titulo", "descricao", 5);

        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> {
            Post p = invocation.getArgument(0);
            p.setId(1L);
            // ensure createdAt is set to mimic entity behavior
            if (p.getCreatedAt() == null) p.setCreatedAt(LocalDateTime.now());
            return p;
        });

        PostResponse response = postService.save(req, user);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("titulo", response.title());
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    @DisplayName("Deve retornar página de posts")
    void getAll(){
        User user = new User(1L, "teste");
        Post post = new Post(1L, "t","d",user,5, LocalDateTime.now());
        Pageable pageable = PageRequest.of(0,10);
        when(postRepository.findAll(pageable)).thenReturn(new PageImpl<>(List.of(post)));

        Page<PostResponse> page = postService.getAll(pageable);

        assertNotNull(page);
        assertEquals(1, page.getTotalElements());
        assertEquals("t", page.getContent().get(0).title());
    }

    @Test
    @DisplayName("Deve retornar post por id quando existir")
    void getByIdFound(){
        User user = new User(1L, "teste");
        Post post = new Post(1L, "t","d",user,5, LocalDateTime.now());
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        PostResponse resp = postService.getById(1L);

        assertEquals(1L, resp.id());
        assertEquals("t", resp.title());
    }

    @Test
    @DisplayName("Deve lançar NotFoundException quando não encontrar post")
    void getByIdNotFound(){
        when(postRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.getById(99L));
    }

}
