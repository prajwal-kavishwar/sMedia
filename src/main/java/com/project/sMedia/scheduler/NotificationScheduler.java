package com.project.sMedia.scheduler;

import com.project.sMedia.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final RedisService redisService;

    @Scheduled(fixedRate = 300000) // every 5 min
    public void processNotifications() {

        Set<String> keys = redisService.getKeys("user:*:pending_notifs");

        if (keys == null || keys.isEmpty()) return;

        for (String key : keys) {

            List<String> messages = redisService.getAllFromList(key);

            if (messages != null && !messages.isEmpty()) {

                System.out.println(
                        "Summarized Push Notification: " +
                                messages.get(0) +
                                " and " + (messages.size() - 1) +
                                " others interacted with your posts."
                );

                redisService.delete(key);
            }
        }
    }
}