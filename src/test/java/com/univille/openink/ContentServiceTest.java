package com.univille.openink;

import com.univille.openink.domain.content.Content;
import com.univille.openink.domain.content.ContentRepository;
import com.univille.openink.domain.content.ContentService;
import com.univille.openink.domain.post.Post;
import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.content.dto.ContentResponse;
import com.univille.openink.infra.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContentServiceTest {

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private ContentService contentService;

    @Test
    void buscarPorId_shouldReturnContentResponse_whenFound() {
        Post post = new Post();
        post.setId(11L);

        Content content = new Content();
        content.setId(5L);
        content.setPost(post);
        content.setText("Algum texto");

        when(contentRepository.findById(5L)).thenReturn(Optional.of(content));

        ContentResponse resp = contentService.buscarPorId(5L);

        assertNotNull(resp);
        assertEquals(5L, resp.id());
        assertEquals(11L, resp.idPost());
    }

    @Test
    void buscarPorIdPost_whenNotFound_throwsNotFoundException() {
        when(contentRepository.findByPostId(99L)).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> contentService.buscarPorIdPost(99L));
        assertTrue(ex.getMessage().contains("Conteúdo não encontrado"));
    }

    @Test
    void criar_shouldSaveAndReturnResponse() {
        Post post = new Post();
        post.setId(20L);

        Content toSave = new Content();
        toSave.setPost(post);
        toSave.setText("Texto para salvar");

        when(contentRepository.save(any(Content.class))).thenAnswer(invocation -> {
            Content c = invocation.getArgument(0);
            c.setId(33L);
            return c;
        });

        ContentResponse resp = contentService.criar("Texto para salvar", post);

        assertNotNull(resp);
        assertEquals(33L, resp.id());
        assertEquals(20L, resp.idPost());
        assertEquals("Texto para salvar", resp.text());

        verify(contentRepository, times(1)).save(any(Content.class));
    }

    @Test
    void deletar_shouldCallRepositoryDelete() {
        doNothing().when(contentRepository).deleteById(7L);

        contentService.deletar(7L);

        verify(contentRepository, times(1)).deleteById(7L);
    }
}
