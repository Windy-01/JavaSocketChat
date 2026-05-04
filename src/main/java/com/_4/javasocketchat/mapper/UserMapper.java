package com._4.javasocketchat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com._4.javasocketchat.entity.*;

@Mapper
public interface UserMapper {
    // 以下两条为注册新账号
    @Insert("INSERT INTO user_info(id, username, email, status) VALUES(#{id}, #{username}, #{email}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int addUserinfo(UserInfoEntity userinfo);

    @Insert("INSERT INTO user_credential(id, password_hash) VALUES(#{id}, #{password_hash})")
    int addUsercredential(UserCredentialEntity usercredential);

    // 获取密码哈希值
    @Select("SELECT password_hash FROM user_credential WHERE id = #{id}")
    String getHashPassword(@Param("id") int id);

    // 检查用户是否存在
    @Select("SELECT 1 FROM user_credential WHERE id = #{id} LIMIT 1")
    int isExist(@Param("id") int id);
    
    // 获取用户ID
    @Select("SELECT id FROM user_info WHERE username = #{username}")
    int getID(@Param("username") String username);

    // 获取用户信息
    @Select("SELECT * FROM user_info WHERE id = #{id}")
    UserInfoEntity getUserInfoByID(@Param("id") int id);

    // 查找用户好友列表
    // @Select("SELECT * FROM friendships WHERE id_a = #{userId}")
    @Select("SELECT u.* FROM user_info u JOIN friendships f ON (u.id = f.id_b) WHERE f.id_a = #{userId}")
    List<UserInfoEntity> getFriendList(@Param("userId") int userId);

    // 查找用户群聊列表
    // @Select("SELECT * FROM group_members WHERE member_id = #{userId}")
    @Select("SELECT g.* FROM `groups` g JOIN group_members gm ON (g.group_id = gm.group_id) WHERE gm.member_id = #{userId}")
    List<GroupsEntity> getGroupList(@Param("userId")int userId);
}
