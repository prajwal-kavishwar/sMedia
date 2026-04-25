package com.project.sMedia.service;


import com.project.sMedia.dto.request.CommentRequest;
import com.project.sMedia.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final StringRedisTemplate redisTemplate;

    public Long increment(String key, long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
//defining time to live
    public void setWithTTL(String key, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, "1", timeout, unit);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // push notification
    public void pushToList(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    // get all notifications
    public List<String> getAllFromList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // delete key
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }



}
