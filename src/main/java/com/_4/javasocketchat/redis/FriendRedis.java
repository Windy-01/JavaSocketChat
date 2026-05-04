package com._4.javasocketchat.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FriendRedis {
    private final RedisTemplate<String, String> template;

    public FriendRedis(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void addFriendship(int UID_a, int UID_b) {
        template.opsForSet().add("chat:friendList:" + UID_a, String.valueOf(UID_b));
        template.opsForSet().add("chat:friendList:" + UID_b, String.valueOf(UID_a));
    }

    public void deleteFriendship(int UID_a, int UID_b) {
        template.opsForSet().remove("chat:friendList:" + UID_a, String.valueOf(UID_b));
        template.opsForSet().remove("chat:friendList:" + UID_b, String.valueOf(UID_a));
    }
}
