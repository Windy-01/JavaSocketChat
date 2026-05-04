package com._4.javasocketchat.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com._4.javasocketchat.entity.*;
import com._4.javasocketchat.mapper.UserMapper;
import com._4.javasocketchat.redis.UserRedis;

@Repository
public class UserRepository {
    private final UserMapper mapper;
    private final UserRedis redis;

    public UserRepository(UserMapper mapper, UserRedis redis) {
        this.mapper = mapper;
        this.redis = redis;
    }
    
    // 增
    public void addUsercredential(UserCredentialEntity credential) {
        if( isExist(credential.getId()) ) {
            return;
        }
        mapper.addUsercredential(credential);
        redis.addUsercredential(credential);
    }
    
    public void addUserinfo(UserInfoEntity info) {
        mapper.addUserinfo(info);
    }

    // 删

    // 改

    // 查
    @Cacheable(value = "password", key = "'user:password:' + #id")
    public String getHashPassword(int id) {
        String password = mapper.getHashPassword(id);
        return password;
    }
    // 应该查redis的
    public boolean isExist(int id) {
        return mapper.isExist(id) == 1;
    }
    // 这里后期要改成依据邮箱，登陆相关全部都要改为是依据邮箱的，用户名只是展示用的
    @Cacheable(value = "userID", key = "'user:id:' + #username")
    public int getID(String username) {
        return mapper.getID(username);
    }

    public UserInfoEntity getUserInfoByID(int id) {
        return mapper.getUserInfoByID(id);
    }

    public List<UserInfoEntity> getFriendList(int userId) {
        return mapper.getFriendList(userId);
    }

    public List<GroupsEntity> getGroupList(int userId) {
        return mapper.getGroupList(userId);
    }
}
