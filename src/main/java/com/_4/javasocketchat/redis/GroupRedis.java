package com._4.javasocketchat.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com._4.javasocketchat.entity.GroupMembersEntity;

@Repository
public class GroupRedis {
    private final RedisTemplate<String, Object> redis;

    public GroupRedis(RedisTemplate<String, Object> redis) {
        this.redis = redis;
    }

    public void setGroupInfo(int groupId, Object groupInfo) {
        redis.opsForHash().put("group_info", String.valueOf(groupId), groupInfo);
    }

    public Object getGroupInfo(int groupId) {
        return redis.opsForHash().get("group_info", String.valueOf(groupId));
    }

    public void setGroupMember(int groupId, int memberId, GroupMembersEntity memberInfo) {
        // redis.opsForHash().put("group_members:" + groupId, String.valueOf(memberId), memberInfo);
    }
}
