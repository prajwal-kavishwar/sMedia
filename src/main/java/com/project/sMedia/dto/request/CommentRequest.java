package com.project.sMedia.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
    private Long authorId;
    private String content;
    private String authorType;
    private int depthLevel;
}
