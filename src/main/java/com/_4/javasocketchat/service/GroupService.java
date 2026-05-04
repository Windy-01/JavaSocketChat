package com._4.javasocketchat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com._4.javasocketchat.component.UserUtilityCompent;
import com._4.javasocketchat.entity.*;
import com._4.javasocketchat.mapper.*;
import com._4.javasocketchat.repository.GroupRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final UserUtilityCompent userUtility;

    // 以下很多部分都需要加入权限校验，暂时先不做了，后续再完善
    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, UserUtilityCompent userUtility) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.userUtility = userUtility;
    }

    public void CreatGroup(String groupName) throws Exception {         // 总觉得有点怪
        int ownerId = userUtility.getUID();
        GroupsEntity group = new GroupsEntity();
        group.setName(groupName);
        group.setOwner_id(ownerId);
        groupMapper.addGroupInfo(group);
        GroupMembersEntity groupMembers = new GroupMembersEntity();
        groupMembers.setGroup_id(group.getGroup_id());
        groupMembers.setMember_id(ownerId);
        groupMapper.addGroupOwnerToMembership(groupMembers);
    }

    public void JoinGroup(int groupId) throws Exception {
        int memberId = userUtility.getUID();
        GroupMembersEntity groupMember = new GroupMembersEntity();
        groupMember.setGroup_id(groupId);
        groupMember.setMember_id(memberId);
        groupMapper.addGroupMembership(groupMember);
    }

    public void SetGroupAdmin(int groupId, int memberId) throws Exception {
        groupMapper.setGroupAdmin(groupId, memberId);
    }

    public void SetGroupMember(int groupId, int memberId) throws Exception {
        groupMapper.setGroupMember(groupId, memberId);
    }

    public void RemoveGroupMember(int groupId, int memberId) throws Exception {
        groupMapper.removeGroupMember(groupId, memberId);
    }

    public void RemoveGroupMember(int groupId) throws Exception {
        int memberId = userUtility.getUID();
        groupMapper.removeGroupMember(groupId, memberId);
    }

    public GroupsEntity GetGroupInfo(int groupId) throws Exception {
        return groupMapper.getGroupInfo(groupId);
    }

    public List<GroupMembersEntity> GetGroupMemberList(int groupId) throws Exception {
        return groupMapper.getGroupMemberList(groupId);
    }

    public void DisbandGroup(int groupId) throws Exception {
        groupMapper.disbandGroup(groupId);
    }


}
