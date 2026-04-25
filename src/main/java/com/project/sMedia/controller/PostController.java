package com.project.sMedia.controller;

import com.project.sMedia.dto.request.PostRequest;

import com.project.sMedia.entity.Post;
import com.project.sMedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        return ResponseEntity.ok(post);
    }
}