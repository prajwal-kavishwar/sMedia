package com.project.sMedia.service;


import com.project.sMedia.dto.request.CommentRequest;
import com.project.sMedia.entity.Comment;
import com.project.sMedia.entity.Post;
import com.project.sMedia.repository.CommentRepository;
import com.project.sMedia.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final RedisService redisService;
    private final ViralityService viralityService;
    private final NotificationService notificationService;

    public Comment addComment(Long postId, CommentRequest request){
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (request.getDepthLevel() > 20) {
            throw new RuntimeException("Max depth exceeded");
        }
        boolean isBot = "BOT".equalsIgnoreCase(request.getAuthorType());
        if(isBot){
            String botKey = "post:" + postId + ":bot_count";
            Long count = redisService.increment(botKey, 1);

            if (count > 100) {
                redisService.increment(botKey, -1);
                throw new RuntimeException("Too many bot replies");
            }

            Long authorId = post.getAuthorId();

            String cooldownKey = "cooldown:bot_"
                    + request.getAuthorId()
                    + ":human_"
                    + authorId;

            if (redisService.exists(cooldownKey)) {
                redisService.increment(botKey, -1);
                throw new RuntimeException("Cooldown active");
            }

            redisService.setWithTTL(cooldownKey, 10, TimeUnit.MINUTES);
        }
        Comment comment= Comment.builder()
                .postId(postId)
                .authorId(request.getAuthorId())
                .content(request.getContent())
                .depthLevel(request.getDepthLevel())
                .createdAt(LocalDateTime.now())
                .build();
        Comment saved=commentRepository.save(comment);
        if (isBot) {
            notificationService.handleNotification(
                    post.getAuthorId(),
                    "Bot replied to your post"
            );
        }

        if (isBot) {
            viralityService.updateScore(postId, "BOT_REPLY");
        } else {
            viralityService.updateScore(postId, "COMMENT");
        }
        return saved;
    }

}
