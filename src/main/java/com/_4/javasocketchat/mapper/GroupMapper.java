package com._4.javasocketchat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com._4.javasocketchat.entity.*;

@Mapper
public interface GroupMapper {
    // 创建群聊
    @Insert("INSERT INTO groups(group_id, name, owner_id) VALUES(#{group_id}, #{name}, #{owner_id})")
    @Options(useGeneratedKeys = true, keyProperty = "group_id")
    int addGroupInfo(GroupsEntity groups);

    // 将群主加入群聊成员表(初始化群)
    @Insert("INSERT INTO group_members(group_id, member_id, role) VALUES(#{group_id}, #{owner_id}, 'owner')")
    int addGroupOwnerToMembership(GroupMembersEntity groupMembers);

    // 加入群聊
    @Insert("INSERT INTO group_members(group_id, member_id) VALUES(#{group_id}, #{member_id})")
    int addGroupMembership(GroupMembersEntity groupMembers);

    // 将用户权限更改为为群管理员
    @Update("UPDATE group_members SET role = 'admin' WHERE group_id = #{group_id} AND member_id = #{member_id}")
    int setGroupAdmin(@Param("group_id") int group_id, @Param("member_id") int member_id);
    
    // 将用户权限更改为为群成员
    @Update("UPDATE group_members SET role = 'member' WHERE group_id = #{group_id} AND member_id = #{member_id}")
    int setGroupMember(@Param("group_id") int group_id, @Param("member_id") int member_id);

    // 将用户移出群聊
    @Delete("DELETE FROM group_members WHERE group_id = #{group_id} AND member_id = #{member_id}")
    int removeGroupMember(@Param("group_id") int group_id, @Param("member_id") int member_id);

    // 获取群聊信息
    @Select("SELECT * FROM groups WHERE group_id = #{group_id}")
    GroupsEntity getGroupInfo(@Param("group_id") int group_id);

    // 获取群聊成员列表
    @Select("SELECT u.user_id, u.username, gm.role FROM users u JOIN group_members gm ON u.user_id = gm.member_id WHERE gm.group_id = #{group_id}")
    List<GroupMembersEntity> getGroupMemberList(@Param("group_id") int group_id);

    // 解散群聊
    @Delete("DELETE FROM groups WHERE group_id = #{group_id}")
    int disbandGroup(@Param("group_id") int group_id);

    // 获得群消息历史记录
}
