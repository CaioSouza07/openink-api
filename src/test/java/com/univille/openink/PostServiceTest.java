package com.univille.openink;

import com.univille.openink.domain.content.ContentService;
import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.post.PostRepository;
import com.univille.openink.domain.post.PostService;
import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.post.dto.PostResponse;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private ContentService contentService;

    @InjectMocks
    private PostService postService;

    @Test
    void save_shouldSavePostAndCreateContent() {
        var request = new CreatePostRequest(
                "Título de exemplo",
                "Descrição de exemplo maior que quinze",
                5,
                "Texto com mais de noventa caracteres para simular o markdown do post........................................"
        );

        var user = new User();
        user.setId(10L);
        user.setName("usuario");

        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> {
            Post p = invocation.getArgument(0);
            p.setId(1L);
            return p;
        });

        PostResponse response = postService.save(request, user);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals(request.title(), response.title());
        assertEquals(request.description(), response.description());
        assertEquals(user.getId(), response.userId());

        verify(postRepository, times(1)).save(any(Post.class));
        verify(contentService, times(1)).criar(eq(request.text()), any(Post.class));
    }

    @Test
    void getAll_shouldReturnPagedResponses() {
        var user = new User();
        user.setId(2L);
        user.setName("u");

        Post p1 = new Post();
        p1.setId(1L);
        p1.setTitle("Titulo 12345");
        p1.setDescription("Descricao longa o suficiente");
        p1.setUser(user);
        p1.setReadTime(3);

        Post p2 = new Post();
        p2.setId(2L);
        p2.setTitle("Titulo 67890");
        p2.setDescription("Outra descricao longa o suficiente");
        p2.setUser(user);
        p2.setReadTime(4);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> page = new PageImpl<>(List.of(p1, p2));

        when(postRepository.findAll(pageable)).thenReturn(page);

        Page<PostResponse> result = postService.getAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(p1.getTitle(), result.getContent().get(0).title());

        verify(postRepository, times(1)).findAll(pageable);
    }

    @Test
    void getById_whenFound_returnsResponse() {
        var user = new User();
        user.setId(5L);
        user.setName("u5");

        Post p = new Post();
        p.setId(7L);
        p.setTitle("Um titulo");
        p.setDescription("Descricao suficiente");
        p.setUser(user);
        p.setReadTime(2);

        when(postRepository.findById(7L)).thenReturn(Optional.of(p));

        PostResponse resp = postService.getById(7L);

        assertEquals(7L, resp.id());
        assertEquals("Um titulo", resp.title());
    }

    @Test
    void getById_whenNotFound_throwsNotFoundException() {
        when(postRepository.findById(99L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> postService.getById(99L));
        assertTrue(ex.getMessage().contains("não encontrado"));
    }
}
