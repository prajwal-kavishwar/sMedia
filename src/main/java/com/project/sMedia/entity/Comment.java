package com.project.sMedia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;
    private String authorType;
    private Long authorId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int depthLevel;

    private LocalDateTime createdAt;
}