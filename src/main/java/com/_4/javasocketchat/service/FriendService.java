package com._4.javasocketchat.service;

import org.springframework.stereotype.Service;

import com._4.javasocketchat.component.UserUtilityCompent;
import com._4.javasocketchat.mapper.FriendMapper;

@Service
public class FriendService {
    private final FriendMapper friendMapper;
    private final UserUtilityCompent userUtility;

    public FriendService(FriendMapper friendMapper, UserUtilityCompent userUtility) {
        this.friendMapper = friendMapper;
        this.userUtility = userUtility;
    }
    // 私有方法
    private void addFriendship(int UID_a, int UID_b) throws Exception {
        friendMapper.addFriendship(UID_a, UID_b);
        friendMapper.addFriendship(UID_b, UID_a);
    }

    public void addFriend(int friendId) throws Exception {
        int uid = userUtility.getUID();
        addFriendship(uid, friendId);
    }

    public void deleteFriend(int friendId) throws Exception {
        int uid = userUtility.getUID();
        friendMapper.deleteFriendship(uid, friendId);
    }
}
