package com.project.sMedia.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViralityService {

    private final RedisService redisService;

    public void updateScore(Long postId,String type){
        String key = "post:" + postId + ":virality_score";
        switch (type) {//added score according to feedback type
            case "BOT_REPLY":
                redisService.increment(key, 1);
                break;
            case "LIKE":
                redisService.increment(key, 20);
                break;
            case "COMMENT":
                redisService.increment(key, 50);
                break;
        }

    }

}
