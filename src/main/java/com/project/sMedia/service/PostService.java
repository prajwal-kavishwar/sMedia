package com.project.sMedia.service;


import com.project.sMedia.dto.request.PostRequest;
import com.project.sMedia.entity.Post;
import com.project.sMedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostRequest request){
        Post post = Post.builder()
                .authorId(request.getAuthorId())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        return postRepository.save(post);

    }
}
