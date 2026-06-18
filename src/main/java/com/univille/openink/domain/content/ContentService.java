package com.univille.openink.domain.content;

import com.univille.openink.domain.content.dto.ContentResponse;
import com.univille.openink.domain.post.Post;
import com.univille.openink.infra.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ContentService {

    private final ContentRepository contentRepository;

    public ContentResponse buscarPorId(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));
        return new ContentResponse(content);
    }

    public ContentResponse buscarPorIdPost(Long idPost) {
        Content content = contentRepository.findByPostId(idPost)
                .orElseThrow(() -> new NotFoundException("Conteúdo não encontrado"));
        return new ContentResponse(content);
    }

    public ContentResponse criar(String text, Post post) {
        Content content = new Content();
        content.setText(text);
        content.setPost(post);
        contentRepository.save(content);
        return new ContentResponse(content);
    }

    public void deletar(Long id) {
        contentRepository.deleteById(id);
    }

}