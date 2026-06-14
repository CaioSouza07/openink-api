package com.univille.openink.domain.content;

import com.univille.openink.domain.content.dto.ContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    public List<ContentResponse> listarTodos() {
        return contentRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public ContentResponse buscarPorId(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));
        return converterParaResponse(content);
    }

    public List<ContentResponse> buscarPorIdPost(Long idPost) {
        return contentRepository.findByIdPost(idPost)
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    public ContentResponse criar(Content content) {
        Content salvo = contentRepository.save(content);
        return converterParaResponse(salvo);
    }

    public ContentResponse atualizar(Long id, Content contentAtualizado) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        content.setTexto(contentAtualizado.getTexto());
        Content salvo = contentRepository.save(content);
        return converterParaResponse(salvo);
    }

    public void deletar(Long id) {
        contentRepository.deleteById(id);
    }

    private ContentResponse converterParaResponse(Content content) {
        return new ContentResponse(
                content.getId(),
                content.getIdPost(),
                content.getTexto(),
                content.getCreatedAt()
        );
    }
}