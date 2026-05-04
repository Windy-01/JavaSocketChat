package com._4.javasocketchat.repository;

import org.springframework.stereotype.Repository;

import com._4.javasocketchat.mapper.FriendMapper;
import com._4.javasocketchat.redis.FriendRedis;

@Repository
public class FriendRepository {
    private final FriendRedis redis;
    private final FriendMapper mapper;

    public FriendRepository(FriendRedis redis, FriendMapper mapper) {
        this.redis = redis;
        this.mapper = mapper;
    }
    // 增
    public void addFriendship(int UID_a, int UID_b) {
        mapper.addFriendship(UID_a, UID_b);
    }
    // 删
    public void deleteFriendship(int UID_a, int UID_b) {
        mapper.deleteFriendship(UID_a, UID_b);
    }
    // 改
    // 查
}
