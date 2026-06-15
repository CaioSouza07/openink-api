package com.univille.openink.domain.post;

import com.univille.openink.domain.content.ContentService;
import com.univille.openink.domain.post.dto.CreatePostRequest;
import com.univille.openink.domain.post.dto.PostResponse;
import com.univille.openink.domain.user.User;
import com.univille.openink.infra.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ContentService contentService;

    @Transactional
    public PostResponse save(CreatePostRequest request, User user){

        var post = new Post(request, user);
        postRepository.save(post);

        contentService.criar(request.text(), post);

        return new PostResponse(post);
    }

    public Page<PostResponse> getAll(Pageable pageable){
        return postRepository.findAll(pageable).map(PostResponse::new);
    }

    public PostResponse getById(Long id){
        var post = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post com ID informado não encontrado"));
        return new PostResponse(post);
    }
}
