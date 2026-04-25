package com.project.sMedia.controller;

import com.project.sMedia.dto.request.CommentRequest;
import com.project.sMedia.entity.Comment;
import com.project.sMedia.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor

public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long postId,
                                              @RequestBody CommentRequest request) {
        Comment comment = commentService.addComment(postId, request);
        return ResponseEntity.ok(comment);
    }


}