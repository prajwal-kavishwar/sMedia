package com.project.sMedia.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final RedisService redisService;

    public void handleNotification(Long userId, String message) {

        String cooldownKey = "user:" + userId + ":notif_cooldown";
        String listKey = "user:" + userId + ":pending_notifs";

        if (redisService.exists(cooldownKey)) {
            // store for batching
            redisService.pushToList(listKey, message);
        } else {
            // send immediately
            System.out.println("Push Notification Sent to User " + userId);

            redisService.setWithTTL(cooldownKey, 15, TimeUnit.MINUTES);
        }
    }
}