package com._4.javasocketchat.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com._4.javasocketchat.entity.UserCredentialEntity;

@Repository
public class UserRedis {
    private final RedisTemplate<String, String> template;

    public UserRedis(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void addUsercredential(UserCredentialEntity usercredential) {
        template.opsForValue().set("user:password:" + usercredential.getId(), usercredential.getPassword_hash());
    }
}
