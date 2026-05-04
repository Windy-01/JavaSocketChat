package com._4.javasocketchat.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com._4.javasocketchat.entity.GroupMembersEntity;
import com._4.javasocketchat.entity.GroupsEntity;
import com._4.javasocketchat.mapper.GroupMapper;
import com._4.javasocketchat.redis.GroupRedis;

// @Repository
public class GroupRepository {
    private final GroupRedis redis;
    private final GroupMapper mapper;

    public GroupRepository(GroupRedis redis, GroupMapper mapper){
        this.redis = redis;
        this.mapper = mapper;
    }

    // 获得群员列表，暂未定稿
    // @Cacheable(value = "name", key = "#groupId+'5645'+'groupMemberList'")
    // public int getGroupMemberList(int groupId) {
    //     // List<GroupMembersEntity> groupInfo = mapper.getGroupMemberList(groupId);
    //     // return groupInfo;
    //     return 0;
    // }
    
    // 创建群聊，暂未定稿，暂时由addGroupInfo和addGroupOwnerToMembership代替
    // public void createGroup(int groupId, GroupsEntity group, int ownerId) {
    //     mapper.addGroupInfo(group);
    //     redis.setGroupInfo(groupId, group);
    //     GroupMembersEntity groupMembers = new GroupMembersEntity();
    //     groupMembers.setGroup_id(group.getGroup_id());
    //     groupMembers.setMember_id(ownerId);
    //     mapper.addGroupOwnerToMembership(groupMembers);
    //     redis.setGroupMember(groupId, ownerId, groupMembers);
    // }

    // 增
    public int addGroupInfo(GroupsEntity groups){
        return mapper.addGroupInfo(groups);
    }

    public int addGroupOwnerToMembership(GroupMembersEntity groupMembers){
        return mapper.addGroupOwnerToMembership(groupMembers);
    }

    public int addGroupMembership(GroupMembersEntity groupMembers){
        return mapper.addGroupMembership(groupMembers);
    }

    // 删
    public int removeGroupMember(int group_id, int member_id){
        return mapper.removeGroupMember(group_id, member_id);
    }
    
    public int disbandGroup(int group_id){
        return mapper.disbandGroup(group_id);
    }

    // 改
    public int setGroupAdmin(int group_id, int member_id){
        return mapper.setGroupAdmin(group_id, member_id);
    }

    public int setGroupMember(int group_id, int member_id){
        return mapper.setGroupMember(group_id, member_id);
    }
    // 查
    public GroupsEntity getGroupInfo(int group_id){
        return mapper.getGroupInfo(group_id);
    }

    public List<GroupMembersEntity> getGroupMemberList(int group_id){
        return mapper.getGroupMemberList(group_id);
    }

    public boolean isGroupExit(int group_id, int member_id){
        return true;
    }
}
